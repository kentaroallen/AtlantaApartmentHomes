package AAH;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AmierNaji on 11/17/14.
 */
public class LoginObject {

    public static void main(String[] args) throws Exception {

        //String out = (validateLogin("back", "group_34"))? "SUCCESS!" : "FAILURE";
        //System.out.println(out);
        System.out.println(userType("New001"));

    }

    public static int userType(String user) throws Exception {//return 0 if prospective resident, 1 if resident, 2 if management

        SQLConnector sqc = SQLConnector.getInstance();
        String typeStatement = "SELECT COUNT(*) FROM USER JOIN MANAGEMENT ON USER.Username = MANAGEMENT.Username";

        ResultSet rs = sqc.runQuery(typeStatement);

        while (rs.next()) {

            if (rs.getInt("COUNT(*)") == 1) {

                return 2;
            }
        }

        String typeStatement2 = "SELECT COUNT(*) FROM USER JOIN RESIDENT ON USER.Username = RESIDENT.Username";

        rs = sqc.runQuery(typeStatement2);

        while (rs.next()) {

            if (rs.getInt("COUNT(*)") == 1) {

                return 1;
            }
        }

        return 0;
    }

    public static boolean validateLogin(String user, String pass) throws Exception {

        SQLConnector sqc = SQLConnector.getInstance();//get our connector
        String loginStatement = "SELECT * FROM USER U WHERE U.Username = '"+user+"';";
        System.out.println(loginStatement);
        //build our SQL statement

        try {

            ResultSet rs = sqc.runQuery(loginStatement);//run our statement and return if something janky happens

            while (rs.next()) {

                if (rs.getString("Password").equals(pass)) { return true; }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}
