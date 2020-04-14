package vedder.jsp;

import vedder.controllers.DBManipulator;

import java.sql.SQLException;

public class JSPHelper {

    public static boolean checkCredentials(String login, String password) throws SQLException, ClassNotFoundException  {
        System.out.println(login + " " + password);
        return new DBManipulator().checkUserExistence(login, password);
    }
}
