package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class ThreeMonthReportSQLObject {

//    public static void main(String args[])
//    {
//        getThreeMonthReport();
//    }

    public static ArrayList<ArrayList<String[]>> getThreeMonthReport() {


        ArrayList<ArrayList<String[]>> out = new ArrayList<ArrayList<String[]>>();
        out.add(new ArrayList<String[]>()); //adding month 1
        out.add(new ArrayList<String[]>()); //adding month 2
        out.add(new ArrayList<String[]>()); //adding month 3

        String threeMonthReportStatement = "SELECT LeaseReport.Month, LeaseReport.Category, COUNT(*)\n" +
                "FROM (SELECT * FROM APARTMENT JOIN PAYS_RENT ON Apt_Number = Apartment_Number) AS LeaseReport\n" +
                "WHERE (LeaseReport.Month = 8) OR \n" +
                "(LeaseReport.Month = 9) OR\n" +
                "(LeaseReport.Month = 10)\n" +
                "GROUP BY LeaseReport.Month, LeaseReport.Category";//PUT SQL CODE HERE

        try {
            ResultSet rs = SQLConnector.runQuery(threeMonthReportStatement);
            while (rs.next()) {
                String[] strArr = new String[2];
                strArr[0] = rs.getString(2);
                strArr[1] = "" + rs.getInt(3);
                (out.get(rs.getInt(1) - 8)).add(strArr);  //rs.getString(2) + "," + rs.getInt(3)
            }

        }
        catch(Exception e) {
            ErrorCode.setCode(81);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }
}

