package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class DefaulterReportSQLObject {

    public ArrayList<ArrayList<String[]>> getDefaulterReport() {


        ArrayList<ArrayList<String[]>> out = new ArrayList<ArrayList<String[]>>();
        String defaulterReportStatement = "";//PUT SQL CODE HERE

        try {

            ResultSet rs = SQLConnector.runQuery(defaulterReportStatement);

            while (rs.next()) {

                //out.add();
            }

        }
        catch(Exception e) {

            ErrorCode.setCode(81);
        }

        return out;
    }
}
