package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class ThreeMonthReportSQLObject {

    public ArrayList<ArrayList<String[]>> getThreeMonthReport() {


        ArrayList<ArrayList<String[]>> out = new ArrayList<ArrayList<String[]>>();
        String threeMonthReportStatement = "";//PUT SQL CODE HERE

        try {

            ResultSet rs = SQLConnector.runQuery(threeMonthReportStatement);

            while (rs.next()) {

                //out.add();
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

