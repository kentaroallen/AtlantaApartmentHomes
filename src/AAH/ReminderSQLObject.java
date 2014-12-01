package AAH;

/**
 * Created by AmierNaji on 11/29/14.
 */

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

public class ReminderSQLObject {

    public static void main(String[] args) {

        defaultedApartments(11,2014);
    }

    public static ArrayList<String> defaultedApartments(int month, int year) {


        //String getDefaultedStatement = "SELECT * FROM (SELECT Apt_Number FROM APARTMENT) MINUS (SELECT A.Apt_Number FROM (SELECT * FROM APARTMENT A JOIN PAYS_RENT PR ON A.Apt_Number = PR.Apartment_Number) WHERE PR.Month = '"+month+"' AND PR.Year = '"+year+"' ) ";
        String getDefaultedStatement = "SELECT Apt_Number FROM APARTMENT WHERE APARTMENT.Apt_Number NOT IN (SELECT X.Apt_Number FROM (SELECT * FROM APARTMENT A JOIN PAYS_RENT PR ON A.Apt_Number = PR.Apartment_Number) X WHERE Month = '"+month+"' AND Year = '"+year+"' )";
        System.out.println(getDefaultedStatement);
        ArrayList<String> out = new ArrayList<String>();

        try {

            ResultSet rs = SQLConnector.runQuery(getDefaultedStatement);

            while (rs.next()) {

                out.add(rs.getString("Apt_Number"));
            }
        }
        catch (Exception e) {

                ErrorCode.setCode(68);
                ErrorCode.errorPopUp();
                System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }

    public static void sendReminder(int apt_num, String message) {

        if (reminderAlreadyExists(apt_num) || ErrorCode.getCurrentError() != 0) {

            return;
        }

        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String sendReminderStatement = "INSERT INTO REMINDER VALUES ( '"+apt_num+"', '"+now.toString()+"' , '"+message+"', 'Unread' );";

        try {

            SQLConnector.runUpdate(sendReminderStatement);
        }

        catch (Exception e) {


            ErrorCode.setCode(69);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static boolean reminderAlreadyExists(int apt_num) {

        java.sql.Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        String sendReminderStatement = "SELECT * FROM REMINDER WHERE Apt_Number = '"+apt_num+"' AND Date_Sent = '"+now.toString()+"';";


        try {

            ResultSet rs = SQLConnector.runQuery(sendReminderStatement);

            if (rs.next()) {

                ErrorCode.setCode(70);
                ErrorCode.errorPopUp();
                System.out.println(ErrorCode.errorMessage());
                return true;
            }

        }

        catch (Exception e) {

            ErrorCode.setCode(69);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return false;
    }
}
