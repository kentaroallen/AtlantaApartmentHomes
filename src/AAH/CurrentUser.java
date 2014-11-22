package AAH;

import org.omg.CORBA.Current;

/**
 * Created by AmierNaji on 11/21/14.
 */
public class CurrentUser {

    private static CurrentUser curr = null;
    private String username;
    private int apartment;
    private int type;

    public CurrentUser(String username, int apartment, int type) {

        this.apartment = apartment;
        this.type = type;
        this.username = username;
    }

    public static CurrentUser getInstance() {

        return curr;
    }

    public static void setUserInfo(String username, int apartment, int type) {// all relevant user information.

        curr = new CurrentUser(username, apartment, type);
    }

    public String getUsername() {

        return username;
    }
}
