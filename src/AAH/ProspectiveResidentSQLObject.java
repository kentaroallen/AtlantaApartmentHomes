package AAH;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.sql.ResultSet;
/**
 * Created by AmierNaji on 11/17/14.
 */
public class ProspectiveResidentSQLObject {

    public static void main(String[] args) throws Exception {

    }

    public static ArrayList<String> getAvailableApartmentCategories() {

        ArrayList<String> out = new ArrayList<String>();
        String categoryStatement = "SELECT DISTINCT A.Category FROM APARTMENT A WHERE A.Available_On >= CURDATE()";

        try {
            ResultSet rs = SQLConnector.runQuery(categoryStatement);

            while (rs.next()) {

                out.add(rs.getString("Category"));
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(8);
            System.out.println(ErrorCode.errorMessage());
            return null;
        }

        return out;
    }

    public static ArrayList<String> getAvailableApartmentLeaseTerms() throws Exception {

        ArrayList<String> out = new ArrayList<String>();
        String categoryStatement = "SELECT DISTINCT A.Lease_Term FROM APARTMENT A WHERE A.Available_On >= CURDATE()";

        try {
            ResultSet rs = SQLConnector.runQuery(categoryStatement);

            while (rs.next()) {

                out.add(rs.getString("Lease_Term"));
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(9);
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }


    public static void insertProspectiveResident(String username, Date dob, String name, String gender, Date moveIn, int leaseTerm,  int monthlyIncome, String category, String prevAddress, int minRent, int maxRent) throws Exception {

        //assume we pass in valid information

        if (!validDate(new Date())) {// check other validations here.

            ErrorCode.setCode(12);
            System.out.println(ErrorCode.errorMessage());
            return;
        }

        boolean accept = availableApartmentExists(monthlyIncome, moveIn, category);

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

    public static boolean availableApartmentExists(int rent, Date preferredMoveIn, String category) {

        java.sql.Date sqlDate = new java.sql.Date(preferredMoveIn.getTime());
        String checkAvailabilityStatement = "SELECT * FROM APARTMENT A WHERE A.Rent < "+(3*rent)+" AND A.Available_On  <= '"+sqlDate.toString()+"' AND A.Category = '"+category+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(checkAvailabilityStatement);

            return (rs.next());
        }
        catch (Exception e) {

            ErrorCode.setCode(14);
            System.out.println(ErrorCode.errorMessage());
            return true;
        }
    }

}
