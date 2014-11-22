package AAH;

import org.omg.CORBA.Current;

/**
 * Created by AmierNaji on 11/21/14.
 */
public class CurrentUser {

    private static boolean set;
    private static String username;
    private static int apartment;
    private static int type;


    public static void setUserInfo(String u, int a, int t) {// all relevant user information.

        set = true;
        username = u;
        apartment = a;
        type = t;

    }

    public static String getUsername() { return username; }
    public static int getApartmentNumber() { return apartment; }
    public static int getApartmentType() { return type; }

}
