package AAH; /**
 * Created by AmierNaji on 11/13/14.
 */
import java.sql.*;

public class SQLConnector {

    private static SQLConnector sqc = null;
    private static Connection conn = null;
    private static final String dbLocation = "jdbc:mysql://academic-mysql.cc.gatech.edu/cs4400_Group_34";
    private static final String user = "cs4400_Group_34";
    private static final String pass = "Q73RJrl6";




    public static SQLConnector getInstance() throws SQLException {//make sure this follows proper singleton design

            if (sqc == null) {

                sqc = new SQLConnector();
                System.out.println("hit");
            }

            if (conn == null) {

                conn = connectToDB();
                System.out.println("hit2");
            }

        return sqc;
    }

    public static Connection connectToDB() throws SQLException {

        Connection conn = DriverManager.getConnection(dbLocation , user, pass);
        return conn;
    }

    public static void closeConnection(Connection c) throws SQLException {

        conn.close();
        conn = null;
    }

    public static void runUpdate(String s) throws SQLException {

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(s);
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


}
