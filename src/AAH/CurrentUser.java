package AAH;

import org.omg.CORBA.Current;

import java.util.Date;

/**
 * Created by AmierNaji on 11/21/14.
 */
public class CurrentUser {

    private static boolean set;
    private static String username;
    private static String password;
    private static int apartment;
    private static int type;
    private int rent;
    private Date moveInDate;



    public static void setUserInfo(String u, String p, int a, int t) {// all relevant user information.

        set = true;
        username = u;
        apartment = a;
        type = t;
        password = p;

    }

    public void setUserType(int t) {

        type = t;
    }


    public static String getUsername() { return username; }
    public static String getPassword() { return password; }
    public static int getApartmentNumber() { return apartment; }
    public static int getUserType() { return type; }

}
