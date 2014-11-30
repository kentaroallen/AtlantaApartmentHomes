package AAH;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/19/14.
 */
public class ApplicationReviewSQLObject {

    public static void main(String[] args) throws Exception {

        residentStatus(true,"Bhavs", 443);
    }

    public static ArrayList<String[]> getApplications() throws Exception {


        ArrayList<String[]> out = new ArrayList<String[]>();

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        String applicationReviewStatement = "SELECT PR.Username, PR.DOB, PR.Gender, PR.Monthly_Income, PR.Category, PR.Preferred_Move_In_Date, PR.Preferred_Lease_Term, PR.App_Status FROM PROSPECTIVE_RESIDENT PR;";
        ResultSet rs = SQLConnector.runQuery(applicationReviewStatement);

        String[] temp;

        while (rs.next()) {

            //NOTE: We get an error when dates return '0000-00-00' make sure WE TAKE THIS INTO ACCOUNT SOON
            temp = new String[] {rs.getString("Username"), rs.getString("DOB"), rs.getString("Gender"), rs.getString("Monthly_Income"), rs.getString("Category"), rs.getString("Preferred_Move_In_Date"), rs.getString("Preferred_Lease_Term"), rs.getString("App_Status")};
            out.add(temp);
        }

        return out;

    }


    public static boolean applicantAllotedAlready(String user) {

        String checkAllotedStatement = "SELECT * FROM RESIDENT R WHERE R.Username = '"+user+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(checkAllotedStatement);

            if (rs.next()) {

                ErrorCode.setCode(25);
                System.out.println(ErrorCode.errorMessage());
                return true;
            }

            return false;

        }
        catch (Exception e) {

            ErrorCode.setCode(26);
            System.out.println(ErrorCode.errorMessage());
            return true;
        }

    }

    public static boolean residentStatus(boolean decision, String user, int apartmentNumber) throws Exception {

        return (decision) ? acceptResident(user, apartmentNumber) : rejectResident(user);
    }

    public static boolean acceptResident(String user, int apartmentNumber) throws Exception{


        String acceptStatement = "UPDATE PROSPECTIVE_RESIDENT SET App_Status = 'Accepted' WHERE Username = '"+user+"';";
        String makeResident = "INSERT INTO RESIDENT VALUES('"+apartmentNumber+"', '"+user+"');";

        try {
            SQLConnector.runUpdate(acceptStatement);
            SQLConnector.runUpdate(makeResident);

        }

        catch (Exception e) {

            return false;
        }

        return true;
    }

    public static boolean rejectResident(String user) throws Exception{

        String rejectStatement = "UPDATE PROSPECTIVE_RESIDENT SET App_Status = 'Rejected' WHERE Username = '"+user+"';";

        try {

            SQLConnector.runUpdate(rejectStatement);
        }

        catch (Exception e) {

            return false;
        }

        return true;
    }

}
