package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class DefaulterReportSQLObject {

    public static ArrayList<String[]> getDefaulterReport(int month_req) {


        ArrayList<String[]> out = new ArrayList<String[]>();
        System.out.println("month: " + month_req);

        String defaulterReportStatement = "SELECT LeaseReport.Apt_Number, (LeaseReport.Amount - LeaseReport.Rent), (DAY(LeaseReport.Date_Of_Payment) - 3)\n" +
                "FROM (SELECT * FROM APARTMENT Apt JOIN PAYS_RENT PR ON Apt.Apt_Number = PR.Apartment_Number) AS LeaseReport\n" +
                "                WHERE (YEAR(LeaseReport.Date_Of_Payment) = YEAR(CURDATE())) AND\n" +
                "                (MONTH(LeaseReport.Date_Of_Payment) =" +  month_req + ") AND\n" +
                "                (DAY(LeaseReport.Date_Of_Payment) > 3) AND\n" +
                "                LeaseReport.Apt_Number NOT IN(\n" +
                "\t                SELECT A.Apt_Number FROM APARTMENT A NATURAL JOIN RESIDENT R NATURAL JOIN PROSPECTIVE_RESIDENT PRes\n" +
                "\t                WHERE (YEAR(LeaseReport.Date_Of_Payment) = YEAR(PRes.Preferred_Move_In_Date)) AND\n" +
                "               \t\t(MONTH(LeaseReport.Date_Of_Payment) = MONTH(PRes.Preferred_Move_In_Date)) AND\n" +
                "               \t\t(DAY(PRes.Preferred_Move_In_Date) > 7)\n" +
                "               \t\t);";

        System.out.println("query: " + defaulterReportStatement);
        try {
            ResultSet rs = SQLConnector.runQuery(defaulterReportStatement);

            while (rs.next()) {

                String[] str = new String[3];
                str[0] = "" + rs.getInt(1);
                str[1] = "" + rs.getInt(2);
                str[2] = "" + rs.getInt(3);
                out.add(str);
            }

        }
        catch(Exception e) {
            ErrorCode.setCode(81);
            ErrorCode.errorPopUp();
        }
        return out;
    }
}
