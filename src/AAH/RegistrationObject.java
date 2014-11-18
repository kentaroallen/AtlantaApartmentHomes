package AAH;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by AmierNaji on 11/17/14.
 */
public class RegistrationObject {

    public static void main(String[] args) throws Exception {

        String out = (insertUser("back", "group_34"))? "SUCCESS!" : "FAILURE";
        System.out.println(out);
    }

    public static boolean insertUser(String user, String pass) throws Exception {

        SQLConnector sqc = SQLConnector.getInstance();//get our connector
        String registerStatement = "INSERT INTO USER VALUES ('"+user+"', '"+pass+"') ";
        //build our SQL statement

        try {

            sqc.runUpdate(registerStatement);//run our statement and return if something janky happens
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
