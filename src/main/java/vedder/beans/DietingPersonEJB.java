package vedder.beans;

import vedder.controllers.DAO;

import javax.ejb.Stateless;
import java.sql.SQLException;

@Stateless
public class DietingPersonEJB {

    public DietingPersonEJB() {
    }

    public boolean validateUserLogin(String login, String password) throws SQLException, ClassNotFoundException {
        return new DAO().getUser(login, password) != null;
    }
}
