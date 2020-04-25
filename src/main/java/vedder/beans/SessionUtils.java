package vedder.beans;

import vedder.models.DietingPerson;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
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
            // TODO change to "user"
            return (DietingPerson) session.getAttribute("login");
        }
        else return null;
    }
}
