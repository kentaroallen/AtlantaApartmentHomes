package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AmierNaji on 11/27/14.
 */
public class PayRentSQLObject {


    public static void main(String[] args) {

        Date now = Calendar.getInstance().getTime();

        payRent("Bhavs", 465, 354, "100090003444588", new Date(2014-1900, 11-1, 30 ), 11, 2014);


    }

    public static void payRent(String user, int apt_number, int baseRent, String card_num, Date paymentDate, int pay_month, int pay_year) {



        int rent = baseRent;

        String firstTimeStatement = "SELECT * FROM PAYS_RENT PR JOIN RESIDENT R WHERE R.Username = '"+user+"' AND R.Apt_Number = '"+apt_number+"';";

        //System.out.println(firstTimeStatement);


        try {

           ResultSet rs =  SQLConnector.runQuery(firstTimeStatement);

            if ( (!rs.next()) && paymentDate.after(new Date(pay_year-1900, pay_month-1, 3))) {

                System.out.println("hit");
                proratedRent(card_num, apt_number, pay_month, pay_year, paymentDate, baseRent);
                return;
            }

            else {


                if (paymentDate.after(new Date(pay_year-1900, pay_month-1, 3))) {

                    lateRent(card_num, apt_number, pay_month, pay_year, paymentDate, baseRent);
                    return;
                }

                regularRent(card_num, apt_number, pay_month, pay_year, paymentDate, baseRent);
            }
        }
        catch (Exception e) {

            ErrorCode.setCode(9);
            System.out.println(ErrorCode.errorMessage());
            return;
        }

    }

    public static void regularRent(String credit_card, int apt_num, int month, int year, Date payDate, int amt) {

        insertRentPayment(credit_card, apt_num, month, year, payDate, amt);
    }

    public static void lateRent(String credit_card, int apt_num, int month, int year, Date payDate, int amt) {

        int daysLate = daysBetween(new Date(year, month, 3), payDate);
        insertRentPayment(credit_card, apt_num, month, year, payDate, amt + 50*daysLate);
    }

    public static void proratedRent(String credit_card, int apt_num, int month, int year, Date payDate, int amt) {


        System.out.println(lastDayOfMonth(payDate).toString());
        double totalDaysInMonth = lastDayOfMonth(payDate).getDate();
        System.out.println(totalDaysInMonth);
        System.out.println(daysBetween(payDate, lastDayOfMonth(payDate)));

        int rent = (int) ((amt/totalDaysInMonth)*daysBetween(payDate, lastDayOfMonth(payDate)));
        insertRentPayment(credit_card, apt_num, month, year, payDate, rent);
    }

    public static void insertRentPayment(String credit_card, int apt_num, int month, int year, Date payDate, int amt) {

        java.sql.Date sqlPayDate = new java.sql.Date(payDate.getTime());
        String insertRentStatement = "INSERT INTO PAYS_RENT VALUES('"+credit_card+"', '"+month+"', '"+year+"', '"+apt_num+"', '"+sqlPayDate.toString()+"', '"+amt+"');";

        try {


            SQLConnector.runUpdate(insertRentStatement);
        }
        catch (Exception e) {

            ErrorCode.setCode(59);
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public static Date lastDayOfMonth(Date d) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        Date lastDayOfMonth = calendar.getTime();
        return lastDayOfMonth;
    }

    public static int daysBetween(Date d1, Date d2) {

        int x = d2.getDate() - d1.getDate();
        return (x > 0) ? x : 1;
    }

}
