package AAH;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.sql.ResultSet;
/**
 * Created by AmierNaji on 11/17/14.
 */
public class ProspectiveResidentSQLObject {

    public static void main(String[] args) throws Exception {

        System.out.println(isDateValid(2012, 2, 29));
    }

    public static boolean isDateValid(int year, int month, int day) {
        boolean dateIsValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }

    public static ArrayList<String> getAvailableApartmentCategories() {

        ArrayList<String> out = new ArrayList<String>();
        String categoryStatement = "SELECT DISTINCT A.Category FROM APARTMENT A\n" +
                "WHERE A.Apt_Number NOT IN\n" +
                "(SELECT Apt.Apt_Number FROM APARTMENT Apt NATURAL JOIN RESIDENT);";

        try {
            ResultSet rs = SQLConnector.runQuery(categoryStatement);

            while (rs.next()) {

                out.add(rs.getString("Category"));
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(8);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return null;
        }

        return out;
    }

    public static ArrayList<String> getAvailableApartmentLeaseTerms() throws Exception {

        ArrayList<String> out = new ArrayList<String>();
        String categoryStatement = "SELECT DISTINCT A.Lease_Term FROM APARTMENT A\n" +
                "WHERE A.Apt_Number NOT IN\n" +
                "(SELECT Apt.Apt_Number FROM APARTMENT Apt NATURAL JOIN RESIDENT) ORDER BY A.Lease_Term ASC;";

        try {
            ResultSet rs = SQLConnector.runQuery(categoryStatement);

            while (rs.next()) {

                out.add(rs.getString("Lease_Term"));
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(9);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }


    public static void insertProspectiveResident(String username, Date dob, String name, String gender, Date moveIn, int leaseTerm,  int monthlyIncome, String category, String prevAddress, int minRent, int maxRent) throws Exception {

        //assume we pass in valid information

        if (!validDate(new Date())) {// check other validations here.

            ErrorCode.setCode(12);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return;
        }

        boolean accept = availableApartmentExists(monthlyIncome, moveIn, category, minRent, maxRent, leaseTerm);

        if (ErrorCode.getCurrentError() != 0) {

            return;
        }

        String decision = (accept) ? "Accepted": "Rejected";

        java.sql.Date sqlDOB = new java.sql.Date(dob.getTime());//because JDBC is lame, it wants us to convert our dates to SQL readable ones
        java.sql.Date sqlMoveInDate = new java.sql.Date(moveIn.getTime());
        String applicationStatement = "INSERT INTO PROSPECTIVE_RESIDENT VALUES ('"+username+"', '"+sqlDOB.toString()+"', '"+name+"', '"+gender+"', '"+sqlMoveInDate.toString()+"', '"+leaseTerm+"', '"+monthlyIncome+"', '"+category+"', '"+prevAddress+"', '"+minRent+"', '"+maxRent+"', '"+decision+"') ";
        //build our SQL statement

        try {

            SQLConnector.runUpdate(applicationStatement);//run our statement and return if something janky happens
        }
        catch (Exception e) {


            e.printStackTrace();
            ErrorCode.setCode(11);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return;
        }

    }

    //finish validations here
    public static boolean validDate(Date d) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 2);

        return (c.getTime().after(d) || c.getTime().equals(d));
    }

    public static boolean availableApartmentExists(int rent, Date preferredMoveIn, String category, int min_rent, int max_rent, int lease_term) {

        java.sql.Date sqlDate = new java.sql.Date(preferredMoveIn.getTime());
        String checkAvailabilityStatement = "SELECT * FROM APARTMENT A WHERE 3*A.Rent < "+(rent)+" AND A.Available_On  <= '"+sqlDate.toString()+"' AND A.Category = '"+category+"' AND A.Rent >= "+min_rent+" AND A.Rent <= "+max_rent+" AND A.Lease_Term >= "+lease_term+" ;";

        try {

            ResultSet rs = SQLConnector.runQuery(checkAvailabilityStatement);

            return (rs.next());
        }
        catch (Exception e) {

            ErrorCode.setCode(14);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return true;
        }
    }

}
