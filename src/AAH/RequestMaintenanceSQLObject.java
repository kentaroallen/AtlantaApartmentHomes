package AAH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AmierNaji on 11/26/14.
 */
public class RequestMaintenanceSQLObject {

    public static void main(String[] args) {

        insertMaintenanceRequest(307, "Roaches Dude");
    }

    public static ArrayList<String[]> getIssues() {

        ArrayList<String[]> out = new ArrayList<String[]>();
        String matchingApartmentsStatement = "SELECT * FROM ISSUE";
        ResultSet rs = null;

        try {
            rs = SQLConnector.runQuery(matchingApartmentsStatement);

            String[] temp;
            while (rs.next()) {

                //NOTE: We get an error when dates return '0000-00-00' make sure WE TAKE THIS INTO ACCOUNT SOON
                temp = new String[] {rs.getString("Issue_Type")};
                out.add(temp);
            }
        }
        catch (SQLException e) {

            ErrorCode.setCode(50);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());

        }

        return out;
    }

    public static void insertMaintenanceRequest(int apt_num, String issueType ) {

        Date now = new Date();
        java.sql.Date expSQL = new java.sql.Date(now.getTime());
        String maintRequestStatement = "INSERT INTO MAINTENANCE_REQUEST (Apt_Number, Date_Request, Issue_Type) VALUES('"+apt_num+"', '"+expSQL.toString()+"', '"+issueType+"') ";
        System.out.println(maintRequestStatement);
        //build our SQL statement
        try {

            SQLConnector.runUpdate(maintRequestStatement);//run our statement and return if something janky happens

        }
        catch (Exception e) {
            ErrorCode.setCode(51);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return;
    }
}
