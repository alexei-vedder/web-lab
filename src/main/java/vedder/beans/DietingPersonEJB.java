package vedder.beans;

import vedder.models.DietingPerson;
import vedder.models.Dish;
import vedder.models.Ration;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class DietingPersonEJB {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("UNIT").createEntityManager();

    private HttpSession session;

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public DietingPersonEJB() {
        this.updateSession();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
    }

    public HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getResponse();
    }

    public DietingPerson getUser() {
        if (session != null) {
            return (DietingPerson) session.getAttribute("user");
        } else return null;
    }

    public void setUser(DietingPerson user) {
        session.setAttribute("user", user);
    }

    public DietingPerson validateUserLogin(String login, String password) {
        entityManager.getTransaction().begin();
        TypedQuery<DietingPerson> query = entityManager.createNamedQuery("DietingPerson.getUser", DietingPerson.class)
                .setParameter("login", login)
                .setParameter("password", password);
        DietingPerson user = query.getSingleResult();
        entityManager.getTransaction().commit();
        setUser(user);
        return user;
    }

    public List<Ration> getUsersRations() {
        DietingPerson user = (DietingPerson) session.getAttribute("user");
        List<Ration> rations = null;
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Date> rationIdsQuery = entityManager.createNamedQuery("Ration.getUsersRationIds", Date.class)
                    .setParameter("dieting_person_id", user.getId());
            List<Date> usersRationIds = rationIdsQuery.getResultList();
            rations = new LinkedList<>();
            for (Date rationId : usersRationIds) {
                TypedQuery<Dish> dishListQuery = entityManager.createNamedQuery("Dish.getDishesByRationId", Dish.class)
                        .setParameter("ration_id", rationId);
                List<Dish> dishList = dishListQuery.getResultList();
                Ration ration = new Ration(dishList, (Timestamp) rationId);
                rations.add(ration);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        entityManager.getTransaction().commit();
        return rations;
    }

    public void addDish(Dish dish, Timestamp rationId) {
        entityManager.getTransaction().begin();
        try {
            entityManager.createNamedQuery("Dish.insertNewDish").setParameter("name", dish.getName())
                    .setParameter("calorie_per_100g", dish.getCaloriePer100g())
                    .setParameter("mass_in_g", dish.getMassInG())
                    .executeUpdate();
            entityManager.createNativeQuery("INSERT INTO web_lab_schema.ration_dish (ration_id, dish_name) VALUES (:ration_id, :dish_name)")
                    .setParameter("ration_id", rationId)
                    .setParameter("dish_name", dish.getName())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void updateSession() {
        session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);
    }

    public void invalidateSession() {
        this.updateSession();
        this.getSession().invalidate();
    }

    public void setRationId(Timestamp ration) {
        session.setAttribute("rationId", ration);
    }

    public Timestamp getRationId() {
        if (session != null) {
            return (Timestamp) session.getAttribute("rationId");
        } else return null;
    }

    public String getUserDataXMLRepresentation() {
        DietingPerson user = getUser();
        try {
            user.setRations(getUsersRations());
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(DietingPerson.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(getUser(), writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }
    }
}
