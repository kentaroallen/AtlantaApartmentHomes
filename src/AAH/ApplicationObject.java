package AAH;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.sql.ResultSet;
/**
 * Created by AmierNaji on 11/17/14.
 */
public class ApplicationObject {

    public static void main(String[] args) throws Exception {

        String out = (insertProspectiveResident("ye", new Date(), "amier", "m", new Date(), 9, 500, "1b", "north avenue", 10, 30, "open")) ? "SUCCESS!" : "FAILURE";
        System.out.println(out);
    }

    public static ArrayList<String> getAvailableApartmentCategories() throws Exception {

        ArrayList<String> out = new ArrayList<String>();
        SQLConnector sqc = SQLConnector.getInstance();
        String categoryStatement = "SELECT DISTINCT A.Category FROM APARTMENT A";

        ResultSet rs = sqc.runQuery(categoryStatement);

        while (rs.next()) {

            out.add(rs.getString("Category"));
        }

        return out;
    }

    public static ArrayList<String> getAvailableApartmentLeaseTerms() throws Exception {

        ArrayList<String> out = new ArrayList<String>();
        SQLConnector sqc = SQLConnector.getInstance();
        String categoryStatement = "SELECT DISTINCT A.Lease_Term FROM APARTMENT A";

        ResultSet rs = sqc.runQuery(categoryStatement);

        while (rs.next()) {

            out.add(rs.getString("Lease_Term"));
        }

        return out;
    }


    public static boolean insertProspectiveResident(String username, Date dob, String name, String gender, Date moveIn, int leaseTerm,  int monthlyIncome, String category, String prevAddress, int minRent, int maxRent, String apartmentStatus) throws Exception {

        //assume we pass in valid information

        if (!validDate(new Date())) {// check other validations here.

            return false;
        }

        java.sql.Date sqlDOB = new java.sql.Date(dob.getTime());//because JDBC is lame, it wants us to convert our dates to SQL readable ones
        java.sql.Date sqlMoveInDate = new java.sql.Date(moveIn.getTime());

        SQLConnector sqc = SQLConnector.getInstance();//get our connector
        String applicationStatement = "INSERT INTO PROSPECTIVE_RESIDENT VALUES ('"+username+"', '"+sqlDOB.toString()+"', '"+name+"', '"+gender+"', '"+sqlMoveInDate.toString()+"', '"+leaseTerm+"', '"+monthlyIncome+"', '"+category+"', '"+prevAddress+"', '"+minRent+"', '"+maxRent+"', '"+apartmentStatus+"') ";
        //build our SQL statement

        try {

            sqc.runUpdate(applicationStatement);//run our statement and return if something janky happens
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //finish validations here
    public static boolean validDate(Date d) {

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 2);

        return (c.getTime().after(d) || c.getTime().equals(d));
    }

}
