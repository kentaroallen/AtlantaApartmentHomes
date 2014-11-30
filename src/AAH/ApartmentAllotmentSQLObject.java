package AAH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by AmierNaji on 11/24/14.
 */
public class ApartmentAllotmentSQLObject {

    public static void main(String[] args) {

        /*for (String[] s : getMatchingApartments("1B-2BA", 200, new Date(2014,12,1) )) {

            for (String x : s)
                System.out.print(x+" - ");
            System.out.println();
        }*/

        getMatchingApartments("Bhavs");
    }

    public static void allotToResident(String user, int apt_number) {

        String makeResidentStatement = "INSERT INTO RESIDENT VALUES ('"+apt_number+"', '"+user+"');";

        try {

            SQLConnector.runUpdate(makeResidentStatement);
        }
        catch(Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(32);
            System.out.println(ErrorCode.errorMessage());
        }

        String updateApartmentAvailabilityStatement = "UPDATE APARTMENT SET Available_On = DATE_ADD(Available_On, INTERVAL "+getPreferredLeaseTerm(user)+" MONTH) WHERE Apt_Number = '"+apt_number+"';";

        try {

            SQLConnector.runUpdate(updateApartmentAvailabilityStatement);
        }
        catch(Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(32);
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static int getPreferredLeaseTerm(String user) {

        String makeResidentStatement = "SELECT Preferred_Lease_Term FROM PROSPECTIVE_RESIDENT WHERE Username = '"+user+"';";

        System.out.println(makeResidentStatement);

        try {

            ResultSet rs= SQLConnector.runQuery(makeResidentStatement);
            while (rs.next()) {

                return rs.getInt("Preferred_Lease_Term");
            }
        }
        catch(Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(32);
            System.out.println(ErrorCode.errorMessage());
        }

        return 1;
    }

    public static ArrayList<String[]> getMatchingApartments(String user) {


        ArrayList<String[]> out = new ArrayList<String[]>();

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        String matchingApartmentsStatement = "SELECT * FROM APARTMENT A, PROSPECTIVE_RESIDENT PR WHERE PR.Username = '"+user+"' AND PR.Category = A.Category AND PR.Monthly_Income > 3*A.Rent AND PR.Preferred_Move_In_Date > A.Available_On AND A.Rent > PR.Min_Rent AND A.Rent < PR.Max_Rent;";
        System.out.println(matchingApartmentsStatement);
        ResultSet rs = null;

        try {
            rs = SQLConnector.runQuery(matchingApartmentsStatement);

            String[] temp;
            while (rs.next()) {

                //NOTE: We get an error when dates return '0000-00-00' make sure WE TAKE THIS INTO ACCOUNT SOON
                temp = new String[] {rs.getString("Apt_Number"), rs.getString("Available_On"), rs.getString("Square_Feet"), rs.getString("Lease_Term"), rs.getString("Category"), rs.getString("Rent")};
                out.add(temp);
            }
        }
        catch (SQLException e) {

            ErrorCode.setCode(31);
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }
}
