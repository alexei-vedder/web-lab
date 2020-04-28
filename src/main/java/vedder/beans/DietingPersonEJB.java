package vedder.beans;

import vedder.controllers.DAO;
import vedder.models.DietingPerson;
import vedder.models.Dish;
import vedder.models.Ration;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Stateless
public class DietingPersonEJB {
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

    public DietingPerson getUser() {
        if (session != null) {
            return (DietingPerson) session.getAttribute("user");
        }
        else return null;
    }

    public void setUser(DietingPerson user) {
        session.setAttribute("user", user);
    }

    public DietingPerson validateUserLogin(String login, String password) throws SQLException, ClassNotFoundException {
        DietingPerson user = new DAO().getUser(login, password);
        this.setUser(user);
        return user;
    }

    public List<Ration> getUsersRations() throws SQLException, ClassNotFoundException {
        DietingPerson user = (DietingPerson) session.getAttribute("user");
        return new DAO().getUsersRations(user);
    }

    public void addNewDish(Dish dish, Timestamp rationId) {
        new DAO().addNewDish(dish, rationId);
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
        }
        else return null;
    }
}
