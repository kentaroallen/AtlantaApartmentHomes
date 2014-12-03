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

        proratedRent(new Date(2014-1900, 12-1, 17), 400);
    }


    public static int amountOwed(String user, int apt_number, int baseRent, Date paymentDate, int pay_month, int pay_year) {


        /*if ((paymentDate.getMonth() + 1) != pay_month || (paymentDate.getYear() + 1900) != pay_year) {//make sure we are only paying for the current month

            System.out.println((paymentDate.getMonth()+1)+" "+(paymentDate.getYear()+1900)+"");
            return 0;
        }*/

        int rent = baseRent;

        //System.out.println(paymentDate.toString());

        String firstTimeStatement = "SELECT * FROM PAYS_RENT PR JOIN RESIDENT R ON PR.Apartment_Number = R.Apt_Number WHERE R.Username = '"+user+"' AND R.Apt_Number = '"+apt_number+"';";
        String getMoveInDate = "SELECT Preferred_Move_In_Date FROM PROSPECTIVE_RESIDENT WHERE Username = '"+user+"';";
        System.out.println(firstTimeStatement);
        System.out.println(getMoveInDate);

        try {

           ResultSet rs =  SQLConnector.runQuery(firstTimeStatement);
            ResultSet rs2 = SQLConnector.runQuery(getMoveInDate);
            rs2.next();
            java.sql.Date moveIn = rs2.getDate("Preferred_Move_In_Date");

            if (!rs.next() && moveIn.after(new Date(pay_year-1900, pay_month-1, 7))) {

                System.out.println("hit");
                return proratedRent(moveIn, baseRent);
            }

            else {


                if (paymentDate.after(new Date(pay_year-1900, pay_month-1, 3))) {

                    System.out.println("hit2");
                    return lateRent(pay_month, pay_year, paymentDate, baseRent);
                }

                return baseRent;
            }
        }
        catch (Exception e) {


            e.printStackTrace();
            ErrorCode.setCode(9);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return baseRent;
        }

    }



    public static int lateRent(int month, int year, Date payDate, int amt) {

        java.sql.Date dueDate = new java.sql.Date(year-1900, month-1, 3);
        java.sql.Date payDateSQL = new java.sql.Date(payDate.getTime());

        String lateRentStatement = "SELECT (DATEDIFF('"+payDateSQL+"', '"+dueDate+"' ) + 1)*50 + '"+amt+"';";

        System.out.println(lateRentStatement);
        try {

            ResultSet rs = SQLConnector.runQuery(lateRentStatement);
            rs.next();
            return rs.getInt(1);
        }

        catch (Exception e) {

            System.out.println(lateRentStatement);
            int daysLate = daysBetween(new Date(year, month, 3), payDate);
            return (amt + 50 * daysLate);
        }
    }

    public static int proratedRent(Date payDate, int amt) {



        double totalDaysInMonth = lastDayOfMonth(payDate).getDate();

        java.sql.Date payDateSQL = new java.sql.Date(payDate.getTime());

        String prorateStatement = "SELECT (DATEDIFF(LAST_DAY('"+payDateSQL+"'), '"+payDateSQL+"' ) + 1)*('"+amt+"'/DAY(LAST_DAY('"+payDateSQL+"')));";
        System.out.println(prorateStatement);

        try {

            ResultSet rs = SQLConnector.runQuery(prorateStatement);
            rs.next();
            return rs.getInt(1);
        }

        catch (Exception e) {

            return (int) ((amt/totalDaysInMonth)*(1 + daysBetween(payDate, lastDayOfMonth(payDate))));
        }
    }

    public static void payRent(String user, String credit_card, int apt_num, int month, int year, Date payDate, int amt) {

        if (alreadyPaid(user, apt_num, month, year) || ErrorCode.getCurrentError() != 0) {

            return;
        }

        java.sql.Date sqlPayDate = new java.sql.Date(payDate.getTime());
        String insertRentStatement = "INSERT INTO PAYS_RENT VALUES('"+credit_card+"', '"+month+"', '"+year+"', '"+apt_num+"', '"+sqlPayDate.toString()+"', '"+amt+"');";

        try {


            SQLConnector.runUpdate(insertRentStatement);
        }
        catch (Exception e) {

            ErrorCode.setCode(59);
            ErrorCode.errorPopUp();
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
        return (d2.getDate() - d1.getDate());
    }

    public static boolean alreadyPaid(String user, int apt_number, int month, int year) {

        String alreadyPaidStatement = "SELECT * FROM PAYS_RENT PR JOIN RESIDENT R ON PR.Apartment_Number = R.Apt_Number  WHERE R.Username = '"+user+"' AND R.Apt_Number = '"+apt_number+"' AND PR.MONTH = '"+month+"' AND PR.YEAR = '"+year+"';";
        System.out.println(alreadyPaidStatement);

        try {

            ResultSet rs = SQLConnector.runQuery(alreadyPaidStatement);

            if (rs.next()) {

                ErrorCode.setCode(62);
                ErrorCode.errorPopUp();
                System.out.println(ErrorCode.errorMessage());
                return true;
            }

        }
        catch (Exception e) {

            e.printStackTrace();
            ErrorCode.setCode(61);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());

        }

        return false;
    }

    public ArrayList<String> getCreditCardInfo() {//

        return null;
    }

}
