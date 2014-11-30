package AAH;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by AmierNaji on 11/30/14.
 */
public class ServiceReportSQLObject {

    public ArrayList<ArrayList<String[]>> serviceRequestReport() {


        ArrayList<ArrayList<String[]>> out = new ArrayList<ArrayList<String[]>>();
        String serviceRequestReportStatement = "";//PUT SQL CODE HERE

        try {

            ResultSet rs = SQLConnector.runQuery(serviceRequestReportStatement);

            while (rs.next()) {

                //out.add();
            }

        }
        catch(Exception e) {

            ErrorCode.setCode(84);
            System.out.println(ErrorCode.errorMessage());
        }

        return out;
    }
}
