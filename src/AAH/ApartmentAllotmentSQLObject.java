package AAH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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


            ErrorCode.setCode(32);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        String updateApartmentAvailabilityStatement = "UPDATE APARTMENT SET Available_On = '"+getApartmentAvailabilityDate(user, apt_number).toString()+"' WHERE Apt_Number = '"+apt_number+"';";

        System.out.println(updateApartmentAvailabilityStatement);
        try {

            SQLConnector.runUpdate(updateApartmentAvailabilityStatement);
        }
        catch(Exception e) {


            ErrorCode.setCode(32);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static java.sql.Date getApartmentAvailabilityDate(String user, int apt_number) {

        String makeResidentStatement = "SELECT DATE_ADD(Preferred_Move_In_Date, INTERVAL Lease_Term MONTH)  FROM PROSPECTIVE_RESIDENT, APARTMENT WHERE Username = '"+user+"' AND Apt_Number = '"+apt_number+"';";

        System.out.println(makeResidentStatement);
        System.out.println(makeResidentStatement);

        try {

            ResultSet rs= SQLConnector.runQuery(makeResidentStatement);
            while (rs.next()) {

                return rs.getDate(1);
            }
        }
        catch(Exception e) {


            ErrorCode.setCode(32);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return new java.sql.Date(Calendar.getInstance().getTime().getTime());
    }

    public static ArrayList<String[]> getMatchingApartments(String user) {


        ArrayList<String[]> out = new ArrayList<String[]>();

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        String matchingApartmentsStatement = "SELECT * FROM APARTMENT A, PROSPECTIVE_RESIDENT PR WHERE PR.Username = '"+user+"' AND PR.Category = A.Category AND PR.Monthly_Income > 3*A.Rent AND PR.Preferred_Move_In_Date > A.Available_On AND A.Rent > PR.Min_Rent AND A.Rent < PR.Max_Rent AND A.Lease_Term >= PR.Preferred_Lease_Term;";
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
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }
}
