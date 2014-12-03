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


        insertPaymentInfo("594203", "2334", "ABBCMSV", new Date(), "Bhavs");

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
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }


        return out;
    }

    public static void insertPaymentInfo(String card_num, String cvv, String name_on_card, Date exp, String user) {

        if (paymentInfoExists(card_num) || ErrorCode.getCurrentError() != 0) {

            return;
        }

        java.sql.Date expSQL = new java.sql.Date(exp.getTime());
        String payInfoStatement = "INSERT INTO PAYMENT_INFO VALUES ('"+card_num+"', '"+cvv+"', '"+name_on_card+"', '"+expSQL.toString()+"', '"+user+"') ";
        //build our SQL statement
        try {

            SQLConnector.runUpdate(payInfoStatement);//run our statement and return if something janky happens

        }
        catch (Exception e) {
            ErrorCode.setCode(44);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return;
    }

    public static void deletePaymentInfo(String card_num) {

        String deletePayStatement = "DELETE FROM PAYMENT_INFO WHERE Card_Number = '"+card_num+"';";

        System.out.println(deletePayStatement);
        try {

            SQLConnector.runUpdate(deletePayStatement);

        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(45);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static boolean paymentInfoExists(String card_num) {


        String payInfoCheck = "SELECT * FROM PAYMENT_INFO PI WHERE PI.Card_Number = '"+card_num+"';";

        try {

            ResultSet rs = SQLConnector.runQuery(payInfoCheck);

            if (rs.next()) {

                ErrorCode.setCode(48);
                ErrorCode.errorPopUp();
                System.out.println(ErrorCode.errorMessage());
                return true;
            }

            return false;
        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(45);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return false;
    }

}
