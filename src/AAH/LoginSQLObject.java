package AAH;

import org.omg.CORBA.Current;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AmierNaji on 11/17/14.
 */
public class LoginSQLObject {

    public static void main(String[] args) throws Exception {

        System.out.println(userType("New001"));
    }

    public static int userType(String user) throws Exception {//return 0 if prospective resident, 1 if resident, 2 if management


        String typeStatement = "SELECT * FROM (USER NATURAL JOIN MANAGEMENT) WHERE USER.Username = '"+user+"';";

        try {
            ResultSet rs = SQLConnector.runQuery(typeStatement);

            if (rs.next()) {

                return 2;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(18);
            System.out.println(ErrorCode.errorMessage());

        }

        String typeStatement2 = "SELECT * FROM (USER NATURAL JOIN RESIDENT) WHERE USER.Username = '"+user+"';";

        try {
            ResultSet rs2 = SQLConnector.runQuery(typeStatement2);

            if (rs2.next()) {

                return 1;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(18);
            System.out.println(ErrorCode.errorMessage());
        }


        return 0;
    }

    public static boolean validateLogin(String user, String pass) throws Exception {


        String loginStatement = "SELECT * FROM USER U WHERE U.Username = '"+user+"';";
        System.out.println(loginStatement);
        //build our SQL statement

        try {

            ResultSet rs = SQLConnector.runQuery(loginStatement);//run our statement and return if something janky happens

            if (!rs.next()) {// if no user exists with this name

                ErrorCode.setCode(16);
                System.out.println(ErrorCode.errorMessage());
                return false;
            }

            while (rs.next()) {

                if (rs.getString("Password").equals(pass)) {

                    return true;
                }
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(19);
            System.out.println(ErrorCode.errorMessage());
            return false;
        }

        return false;
    }

    public static void setCurrentUser(String user) throws Exception{


        int userType = userType(user);

        if (ErrorCode.currentError != 0) { // we've encountered an error, so abandon ship.

            return;
        }

        if (userType == 2 || userType == 0) {

            CurrentUser.setUserInfo(user, -1 , userType);
            return;
        }
        //we know that this is management, so we go ahead and take care of this case.

        String retrieveUserStatement = "SELECT * FROM RESIDENT WHERE U.Username = '"+user+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(retrieveUserStatement);//run our statement and return if something janky happens

            while (rs.next()) {

                CurrentUser.setUserInfo(user, Integer.parseInt(rs.getString("Apt_Number")), userType);

                //String name, String dob, String gender, String income, String typeApt, String prefdate, String leaseterm, String approval
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(18);
            System.out.println(ErrorCode.errorMessage());

        }

    }

    public static boolean filledOutApplication(String user) {

        String filledApplicationStatement = "SELECT * FROM PROSPECTIVE_RESIDENT PR WHERE PR.Username =  '"+user+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(filledApplicationStatement);//run our statement and return if something janky happens
            return (rs.next()) ? true : false;

        }
        catch (Exception e) {

            ErrorCode.setCode(20);
            System.out.println(ErrorCode.errorMessage());
            return true;
        }
    }

}
