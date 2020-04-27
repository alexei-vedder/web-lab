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
}
