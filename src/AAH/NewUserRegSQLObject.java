package AAH;

import java.util.Calendar;
import java.util.Date;
import java.sql.ResultSet;

/**
 * Created by AmierNaji on 11/17/14.
 */
public class NewUserRegSQLObject {

    public static void main(String[] args) throws Exception {

    }

    public static boolean userExists(String user) {

        String existenceStatement = "SELECT COUNT(*) FROM USER WHERE Username = '"+user+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(existenceStatement);//run our statement and return if something janky happens
            while(rs.next()) {

                return (rs.getInt("COUNT(*)") == 0) ? false : true;
            }
        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(3);
            System.out.println(ErrorCode.errorMessage());
            return true;
        }

        return false;
    }

    public static void insertUser(String user, String pass) throws Exception {

        String registerStatement = "INSERT INTO USER VALUES ('"+user+"', '"+pass+"') ";
        //build our SQL statement
        try {

            SQLConnector.runUpdate(registerStatement);//run our statement and return if something janky happens
            ErrorCode.setCode(0);
        }
        catch (Exception e) {
            ErrorCode.setCode(4);
            System.out.println(ErrorCode.errorMessage());
        }

        return;
    }

}
