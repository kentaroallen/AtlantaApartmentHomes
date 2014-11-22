package AAH;

/**
 * Created by AmierNaji on 11/22/14.
 */
public class ErrorCode {

    public static int currentError = 0;


    /*

    No Error:
    0

    Registration Errors:
    1 : username already taken
    2 : password and confirm password don't match
    3 : SQL error from checking user existence
    4 : SQL error from inputting user
    5 :
    6 :
    7 :

    Prospective Resident Application Errors:
    8 : SQL error retrieving Apartment Categories
    9 : SQL error retrieving Apartment Lease Terms
    10 : Not all Fields Have Values
    11 : SQL error inputting application
    12 : Move In Date not more than 2 months after current date

    Login Errors



     */

    public static void setCode(int e) { currentError = e; }
    public static int getCurrentError() { return currentError; }

    public static String errorMessage() {

        switch(currentError) {

            case 0:
                break;
            case 1:
                return "Registration Error: Username Already Taken!";
            case 2:
                return "Registration Error: Password and Confirm Password don't match";
            case 3:
                return "Registration Error: Database Error Checking User Existence!";
            case 4:
                return "Registration Error: Database Error Inserting User!";
            case 5:
                return "-";
            case 6:
                return "-";
            case 7:
                return "-";
            case 8:
                return "Prospective Application Error: Database Error Retrieving Apartment Categories!";
            case 9:
                return "Prospective Application Error: Database Error Retrieving Apartment Lease Terms!";
            case 10:
                return "Prospective Application Error: Not all fields have been filled!";
            case 11:
                return "Prospective Application Error: Database Error Inserting PR!";
            case 12:
                return "Prospective Application Error: Invalid Move In Date (Must be after 2 months from today)";
        }

        return null;
    }
}
