package AAH; /**
 * Created by AmierNaji on 11/13/14.
 */
import javax.xml.transform.Result;
import java.sql.*;

public class SQLConnector {

    private static SQLConnector sqc = null;
    private static Connection conn = null;

    /*
    $mysql_host = "mysql2.000webhost.com";
    $mysql_database = "a3997589_4400";
    $mysql_user = "a3997589_4400";
    $mysql_password = "4400back";

    private static final String dbLocation = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_34";
    private static final String user = "cs4400_Group_34";
    private static final String pass = "Q73RJrl6";
     */
    private static final String dbLocation = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_34";
    private static final String user = "cs4400_Group_34";
    private static final String pass = "Q73RJrl6";


    public static void getConnection() throws SQLException{//no singleton design. Static class is sufficient

        if (conn == null) {

            conn = connectToDB();
        }
    }

    public static Connection connectToDB() throws SQLException {

        Connection conn = DriverManager.getConnection(dbLocation , user, pass);
        return conn;
    }

    //Test comment by Clarence

    public static void closeConnection(Connection c) throws SQLException {

        conn.close();
        conn = null;
    }

    public static void runUpdate(String s) throws SQLException {

        getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(s);
    }

    public static ResultSet runQuery(String s) throws SQLException {

        getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(s);
    }

    public static void testQuery() throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Test");

        while (rs.next()) {
            int x = rs.getInt("a");
            int s = rs.getInt("b");
            String f = rs.getString("c");
            System.out.println(x+s+f);
        }
    }

        /*public static SQLConnector getInstance() throws SQLException {

            if (sqc == null) {

                sqc = new SQLConnector();
                System.out.println("hit");
            }

            if (conn == null) {

                conn = connectToDB();
                System.out.println("hit2");
            }

        return sqc;
    }*/


}
