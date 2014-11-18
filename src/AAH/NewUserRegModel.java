package AAH;

/**
 * Created by AmierNaji on 11/14/14.
 */
public class NewUserRegModel {

    private static SQLConnector sqc = null;

    public static void main(String[] args) throws Exception {

        String out = (NewUserRegModel.insertUser()) ? "SUCCESS!" : "FAILURE";
        System.out.println(out);
    }

    public static boolean insertUser() throws Exception{



        try {
            sqc = SQLConnector.getInstance();
            //sqc.runQuery("INSERT INTO Test VALUES('dude', 'dheude', 'ye')");
        }
        catch (Exception e) {

            return false;
        }
        sqc.runUpdate("INSERT INTO Test VALUES('ken', 'allen', 'amier')");

        return true;
    }

}
