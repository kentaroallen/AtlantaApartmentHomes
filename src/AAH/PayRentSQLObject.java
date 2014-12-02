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

            if (!rs.next()) {

                ResultSet rs2 = SQLConnector.runQuery(getMoveInDate);
                rs2.next();
                java.sql.Date moveIn = rs2.getDate("Preferred_Move_In_Date");
                System.out.println("hit");
                return proratedRent(moveIn, baseRent);
            }

            else {


                if (paymentDate.after(new Date(pay_year-1900, pay_month-1, 3))) {

                    System.out.println("hit2");
                    return lateRent(pay_month, pay_year, paymentDate, baseRent);
                }

                System.out.println(baseRent);
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

        int daysLate = daysBetween(new Date(year, month, 3), payDate);
        return (amt + 50*daysLate);
    }

    public static int proratedRent(Date payDate, int amt) {


        System.out.println(lastDayOfMonth(payDate).toString());
        double totalDaysInMonth = lastDayOfMonth(payDate).getDate();
        System.out.println(totalDaysInMonth);
        System.out.println(daysBetween(payDate, lastDayOfMonth(payDate)));


        return (int) ((amt/totalDaysInMonth)*daysBetween(payDate, lastDayOfMonth(payDate)));

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

        int x = d2.getDate() - d1.getDate();
        return (x > 0) ? x : 1;
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
