package AAH;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Arrays;
/**
 * Created by AmierNaji on 11/25/14.
 */
public class PaymentInformationSQLObject {

    public static void main(String[] args)  {


        insertPaymentInfo(594203, 2334, "ABBCMSV", new Date(), "Bhavs");

        for (String[] s : getPaymentInfo("Bhavs")) {

            System.out.println(Arrays.toString(s));
        }

    }

    public static ArrayList<String[]> getPaymentInfo(String user) {

        ArrayList<String[]> out = new ArrayList<String[]>();

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        String getPaymentInfoStatement = "SELECT * FROM PAYMENT_INFO WHERE Username = '"+user+"';";
        ResultSet rs = null;


        try {
            rs = SQLConnector.runQuery(getPaymentInfoStatement);

            String[] temp;

            while (rs.next()) {

                //NOTE: We get an error when dates return '0000-00-00' make sure WE TAKE THIS INTO ACCOUNT SOON
                temp = new String[] {rs.getString("Card_Number"), rs.getString("CVV"), rs.getString("Name_On_Card"), rs.getString("Expiration_Date"), rs.getString("Username")};
                out.add(temp);
            }
        }

        catch (SQLException e) {

            ErrorCode.setCode(45);
            System.out.println(ErrorCode.errorMessage());
        }


        return out;
    }

    public static void insertPaymentInfo(int card_num, int cvv, String name_on_card, Date exp, String user) {

        java.sql.Date expSQL = new java.sql.Date(exp.getTime());
        String payInfoStatement = "INSERT INTO PAYMENT_INFO VALUES ('"+card_num+"', '"+cvv+"', '"+name_on_card+"', '"+expSQL.toString()+"', '"+user+"') ";
        //build our SQL statement
        try {

            SQLConnector.runUpdate(payInfoStatement);//run our statement and return if something janky happens

        }
        catch (Exception e) {
            ErrorCode.setCode(44);
            System.out.println(ErrorCode.errorMessage());
        }

        return;
    }

}
