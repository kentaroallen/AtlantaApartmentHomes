package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class ServiceReportSQLObject {

    public static void main(String args[])
    {
        serviceRequestReport();
    }
    public static ArrayList<ArrayList<String[]>> serviceRequestReport() {


        ArrayList<ArrayList<String[]>> out = new ArrayList<ArrayList<String[]>>();
        out.add(new ArrayList<String[]>()); //adding month 1
        out.add(new ArrayList<String[]>()); //adding month 2
        out.add(new ArrayList<String[]>()); //adding month 3

        String serviceRequestReportStatement = "SELECT MONTH( MR.Date_Request ) , MR.Issue_Type, AVG( DATEDIFF( MR.Date_Resolved, MR.Date_Request ) ) \n" +
                "FROM MAINTENANCE_REQUEST AS MR\n" +
                "WHERE (\n" +
                "MONTH( MR.Date_Request ) =8\n" +
                "OR MONTH( MR.Date_Request ) =9\n" +
                "OR MONTH( MR.Date_Request ) =10\n" +
                ")\n" +
                "GROUP BY MONTH( MR.Date_Request ) , MR.Issue_Type\n";//PUT SQL CODE HERE

        try {

            ResultSet rs = SQLConnector.runQuery(serviceRequestReportStatement);

            while (rs.next()) {
                String[] strArr = new String[2];
                strArr[0] = rs.getString(2);
                if (rs.getDouble(3) == 0.0)
                    strArr[1] = "" + (rs.getDouble(3) + 1);
                else
                    strArr[1] = "" + rs.getDouble(3);
                (out.get(rs.getInt(1) - 8)).add(strArr);  //rs.getString(2) + "," + rs.getInt(3)
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            ErrorCode.setCode(84);
            System.out.println(ErrorCode.errorMessage());
        }
        return out;
    }
}
