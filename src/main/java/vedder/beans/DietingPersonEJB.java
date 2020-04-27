package vedder.beans;

import vedder.controllers.DAO;
import vedder.models.DietingPerson;
import vedder.models.Ration;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class DietingPersonEJB {

    public DietingPersonEJB() {
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
    }

    public static DietingPerson getUser() {
        HttpSession session = getSession();
        if (session != null) {
            return (DietingPerson) session.getAttribute("user");
        }
        else return null;
    }

    public static void setUser(DietingPerson user) {
        HttpSession session = DietingPersonEJB.getSession();
        session.setAttribute("user", user);
    }

    public static DietingPerson validateUserLogin(String login, String password) throws SQLException, ClassNotFoundException {
        return new DAO().getUser(login, password);
    }

    public static List<Ration> getUsersRations() throws SQLException, ClassNotFoundException {
        HttpSession session = DietingPersonEJB.getSession();
        DietingPerson user = (DietingPerson) session.getAttribute("user");
        return new DAO().getUsersRations(user);
    }
}
