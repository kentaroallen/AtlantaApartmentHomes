package AAH;

import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;

/**
 * Created by AmierNaji on 11/17/14.
 */
public class NewUserRegSQLObject {

    public static void main(String[] args) {

    }

    public static boolean userExists(String user) {

        String existenceStatement = "SELECT * FROM USER WHERE Username = '"+user+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(existenceStatement);//run our statement and return if something janky happens
            return (rs.next()) ? true : false;

        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(3);
            System.out.println(ErrorCode.errorMessage());
            return true;
        }

    }

    public static void insertUser(String user, String pass) {

        String registerStatement = "INSERT INTO USER VALUES ('"+user+"', '"+pass+"') ";
        //build our SQL statement
        try {

            SQLConnector.runUpdate(registerStatement);//run our statement and return if something janky happens
            CurrentUser.setUserInfo(user, pass, -1, 0, 0);// sets default CU values
        }
        catch (Exception e) {
            ErrorCode.setCode(4);
            System.out.println(ErrorCode.errorMessage());
        }

        return;
    }

}
