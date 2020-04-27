package vedder.beans;

import vedder.models.DietingPerson;
import vedder.models.Ration;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

@ManagedBean(name = "userBean")
@SessionScoped
public class DietingPersonBean {
    private DietingPerson user;
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
        user = new DietingPerson();
        userEJB = new DietingPersonEJB();
    }

    public String goToResultPage() throws SQLException, ClassNotFoundException {
        DietingPerson userFromDB = userEJB.validateUserLogin(user.getLogin(), user.getPassword());
        if (userFromDB != null) {
            this.user = userFromDB;
            return "result";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Некорректные данные"));
            return "index";
        }
    }

    public String seeXmlView() throws SQLException, ClassNotFoundException {
        return this.goToResultPage().equals("index") ? "index" : "resultXML";
    }

    public List<Ration> getUsersRations() throws SQLException, ClassNotFoundException {
        return userEJB.getUsersRations();
    }

    public String logout() {
        userEJB.invalidateSession();
        return "index";
    }
}
