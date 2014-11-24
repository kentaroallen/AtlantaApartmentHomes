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

        for (String[] s : getMatchingApartments("1B-2BA", 200, new Date(2014,12,1) )) {

            for (String x : s)
                System.out.print(x+" - ");
            System.out.println();
        }
    }

    public static ArrayList<String[]> getMatchingApartments(String category, int monthly_income, Date move_in_date ) {


        ArrayList<String[]> out = new ArrayList<String[]>();

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        java.sql.Date move_in_date_sql = new java.sql.Date(move_in_date.getTime());
        String matchingApartmentsStatement = "SELECT * FROM APARTMENT WHERE Category = '"+category+"' AND "+monthly_income+" >= Rent AND '"+move_in_date_sql+"' >  Available_On;";
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
