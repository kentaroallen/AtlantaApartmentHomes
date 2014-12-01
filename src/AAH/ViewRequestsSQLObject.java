package AAH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AmierNaji on 11/24/14.
 */
public class ViewRequestsSQLObject {

    public static void main(String[] args) {

        for (String[] s : getUnresolvedRequests()) {

            for (String x : s)
                System.out.print(x+" - ");
            System.out.println();
        }
    }

    public static void resolveRequest(int apt_number, Date request, String issue_type) {

        java.sql.Date now = new java.sql.Date((new Date()).getTime());
        java.sql.Date request_sql = new java.sql.Date(request.getTime());

        String resolveStatement = "UPDATE MAINTENANCE_REQUEST SET Date_Resolved = '"+now.toString()+"' WHERE Apt_Number = '"+apt_number+"' AND Date_Request = '"+request_sql.toString()+"' AND Issue_Type = '"+issue_type+"';";

        try {

            SQLConnector.runUpdate(resolveStatement);
        }
        catch (Exception e) {

            ErrorCode.setCode(40);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static ArrayList<String[]> getResolvedRequests() {

        ArrayList<String[]> out = new ArrayList<String[]>();
        String resolvedStatement = "SELECT * FROM MAINTENANCE_REQUEST WHERE Date_Resolved IS NOT NULL";
        ResultSet rs = null;

        try {
            rs = SQLConnector.runQuery(resolvedStatement);

            String[] temp;
            while (rs.next()) {

                temp = new String[] {rs.getString("Apt_Number"), rs.getString("Date_Request"), rs.getString("Issue_Type")};
                out.add(temp);
            }
        }
        catch (SQLException e) {

            ErrorCode.setCode(38);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }

    public static ArrayList<String[]> getUnresolvedRequests() {


        ArrayList<String[]> out = new ArrayList<String[]>();
        String unresolvedStatement = "SELECT * FROM MAINTENANCE_REQUEST WHERE Date_Resolved IS NULL";
        ResultSet rs = null;

        try {
            rs = SQLConnector.runQuery(unresolvedStatement);

            String[] temp;
            while (rs.next()) {

                //NOTE: We get an error when dates return '0000-00-00' make sure WE TAKE THIS INTO ACCOUNT SOON
                temp = new String[] {rs.getString("Apt_Number"), rs.getString("Date_Request"), rs.getString("Issue_Type")};
                out.add(temp);
            }
        }
        catch (SQLException e) {

            ErrorCode.setCode(39);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }
}
