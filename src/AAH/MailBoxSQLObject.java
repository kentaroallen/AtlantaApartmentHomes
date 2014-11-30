package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class MailBoxSQLObject {

    public static ArrayList<String[]> getMessages(int apt) {

        String getMessageStatement = "SELECT * FROM REMINDER WHERE Apt_Number = '"+apt+"';";
        ArrayList<String[]> out = new ArrayList<String[]>();

        try {

            ResultSet rs = SQLConnector.runQuery(getMessageStatement);
            int i = 1;
            while (rs.next()) {

                out.add(new String[] { i+"", rs.getString("Message"), rs.getString("Date_Sent") });
                i++;
            }
        }
        catch (Exception e) {


        }

        return out;
    }
}
