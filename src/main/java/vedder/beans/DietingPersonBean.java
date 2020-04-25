package vedder.beans;

import vedder.models.DietingPerson;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@ManagedBean(name = "userBean")
@SessionScoped
public class DietingPersonBean {
    private DietingPerson user;
    private DietingPersonEJB userEJB = new DietingPersonEJB();

    private String login;
    private String password;

    public DietingPerson getUser() {
        return user;
    }

    public void setUser(DietingPerson user) {
        this.user = user;
    }

    public DietingPersonEJB getUserEJB() {
        return userEJB;
    }

    public void setUserEJB(DietingPersonEJB userEJB) {
        this.userEJB = userEJB;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DietingPersonBean() {
    }

    public String validateUserLogin() throws SQLException, ClassNotFoundException {
        boolean valid = this.userEJB.validateUserLogin(login, password);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            // TODO change to "user"
            session.setAttribute("login", login);
            return "result";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect login and password",
                            "Please enter correct login and password"));
            return "index";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }
}
