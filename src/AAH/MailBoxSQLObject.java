package AAH;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 * Created by AmierNaji on 11/29/14.
 */
public class MailBoxSQLObject {

    public static ArrayList<String[]> getUnreadMessages(String user) {

        String messageStatement = "SELECT * FROM REMINDER REM JOIN RESIDENT RES WHERE RES.Username = '"+user+"' AND Status = 'Unread';";
        ArrayList<String[]> out = new ArrayList<String[]>();

        try {

            ResultSet rs = SQLConnector.runQuery(messageStatement);

            int i = 0;
            while (rs.next()) {

                out.add(new String[] { i+"", rs.getString("Message"), rs.getString("Date_Sent") });
                i++;
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(75);
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }

    public static void setMessagesRead(String user, int apt_num, String message) {

        String setReadStatement = "UPDATE REMINDER SET Status = 'Read' WHERE Apt_Number = '"+apt_num+"' AND Message = '"+message+"';";

        try {

            SQLConnector.runUpdate(setReadStatement);
        }
        catch (Exception e) {

            ErrorCode.setCode(76);
            System.out.println(ErrorCode.errorMessage());
        }
    }
}