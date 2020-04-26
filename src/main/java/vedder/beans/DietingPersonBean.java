package vedder.beans;

import vedder.controllers.DAO;
import vedder.models.DietingPerson;
import vedder.models.Ration;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "userBean")
@SessionScoped
public class DietingPersonBean {
    private DietingPerson user = new DietingPerson();
    private DietingPersonEJB userEJB;

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

    public DietingPersonBean() {
    }

    public String validateUserLogin() throws SQLException, ClassNotFoundException {
        DietingPerson userFromDB = DietingPersonEJB.validateUserLogin(user.getLogin(), user.getPassword());
        if (userFromDB != null) {
            this.user = userFromDB;
            HttpSession session = DietingPersonEJB.getSession();
            session.setAttribute("user", user);
            return "result";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Некорректные данные",
                            "Пожалуйста введите корректные данные"));
            return "index";
        }
    }

    public String seeXmlView() throws SQLException, ClassNotFoundException {
        return this.validateUserLogin().equals("index") ? "index" : "resultXML";
    }

    public List<Ration> getUsersRations() throws SQLException, ClassNotFoundException {
        HttpSession session = DietingPersonEJB.getSession();
        user = (DietingPerson) session.getAttribute("user");
        return new DAO().getUsersRations(user);
    }

    public String logout() {
        HttpSession session = DietingPersonEJB.getSession();
        session.invalidate();
        return "index";
    }
}
