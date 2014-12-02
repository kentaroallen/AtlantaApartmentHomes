package AAH;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by AmierNaji on 11/22/14.
 */
public class ErrorCode {
    
    public static int currentError = 0;


    /*

<<<<<<< HEAD
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
     13 :
     14 :
     15 :

     Login Errors :
     16 : No Such Username
     17 : Incorrect Password
     18 : SQL Error getting User Type
     19 : SQL Error Validating Password
     20 : SQL Error Checking if Application is Filled Out
     21 :
     22 :
     23 :

     Application Review Errors:
     24 : Selection Error: No selection highlighted.
     25 :
     26 :
     27 :
     28 :
     29 :
     30 :

     Apartment Allotment Errors:
     31 : SQL Error Getting Apartment Values
     32 :
     33 :
     34 :
     35 :
     36 :
     37 :

     View Requests Errors:
     38 : SQL Error Getting Unresolved Maintenance Requests
     39 : SQL Error Getting Resolved Maintenance Requests
     40 : SQL Error Updating a request from Unresolved to Resolved
     41 :
     42 :
     43 :

     Payment Info Errors:
     44 : SQL Error Inputting Card Info
     45 : SQL Error Retrieving Payment Info
     46 : No card selected for deletion
     47 : Selection Error: Some payment information fields are blank.
     =======
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
     13 : Some Values are Null
     14 : SQL Error Checking Apartment Availability
     15 : Invalid Date

     Login Errors :
     16 : No Such Username
     17 : Incorrect Password
     18 : SQL Error getting User Type
     19 : SQL Error Validating Password
     20 : SQL Error Checking if Application is Filled Out
     21 : Not after Move in Date
     22 :
     23 :

     Application Review Errors:
     24 : Selection Error: No selection highlighted.
     25 : Applicant Already Has Apartment Alloted
     26 : SQL Error Checking for Allotment
     27 :
     28 :
     29 :
     30 :

     Apartment Allotment Errors:
     31 : SQL Error Getting Apartment Values
     32 : SQL Error Allotting to Resident
     33 :
     34 :
     35 :
     36 :
     37 :

     View Requests Errors:
     38 : SQL Error Getting Unresolved Maintenance Requests
     39 : SQL Error Getting Resolved Maintenance Requests
     40 : SQL Error Updating a request from Unresolved to Resolved
     41 :
     42 :
     43 :

     Payment Info Errors:
     44 : SQL Error Inputting Card Info
     45 : SQL Error Retrieving Payment Info
     46 : Selection Error: No card selected for Deletion
     47 : Selection Error: Some payment information fields are blank.
     48 : Card Already Exists
     49 : SQL Error Deleting Card Info


     Maintenance Request Errors:
     50 : SQL Error Getting Issue Types
     51 : SQL Error Inserting Maintenance Request
     52 : Error Retrieving Request Info!
     53 : Maintenance Request Already Made
     54 :
     55 :
     56 :
     57 :
     58 :

     Pay Rent Errors:
     59 : Error Inserting Values into Rent Payment
     60 : Error Getting Previous Info
     61 : SQL Error Checking if payment has been made already
     62 : Payment has been made already
     63 : Invalid Date
     64 :
     65 :
     66 :
     67 :

     Rent Reminder Errors:
     68 : SQL Error Getting Remindable Apartments
     69 :
     70 :
     71 :
     72 :
     73 :
     74 :

     HomeMessage Errors:
     75 : SQL Error Getting Messages
     76 : SQL Error G
     77 :
     78 :
     79 :
     80 :

     Three Month Report Errors:
     81 : SQL Error Getting Report
     82 :
     83 :

     Service Request Report Errors:
     84 : SQL Error Getting Report
     85 :
     86 :

     Defaulter Report Errors:
     87 : SQL Error Getting Report
     88 :
     89 :

     >>>>>>> ee58a9b63607107a01d9947795a361efb12e4d47



     */
    public static void setCode(int e) {
        currentError = e;
    }

    public static int getCurrentError() {
        return currentError;
    }
    public static void errorPopUp(){
        Stage popup = new Stage();
        HBox popup_hbox = new HBox();
        Scene popup_scene = new Scene(popup_hbox, 300, 100);
        popup.setTitle("Error (Code "+getCurrentError()+")");
        popup.setWidth(500);
        popup.setHeight(150);
        popup.setScene(popup_scene);
        popup.show();
        popup_hbox.setAlignment(Pos.CENTER);
        popup_hbox.setSpacing(10);
        Label errorlabel = new Label();
        // errorlabel.setStyle("-fx-font: 12px Stencil;");
        popup_hbox.getChildren().addAll(errorlabel);

        errorlabel.setText(errorMessage());
    }

    public static String errorMessage() {

        switch (currentError) {

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
            case 13:
                return "Prospective Application Error: Some Fields are null!";
            case 14:
                return "Prospective Application Error: Database Error Retrieving Apartment Availability";
            case 15:
                return "Prospective Application Error: Invalid Date!";
            case 16:
                return "Login Error: No Such Username!";
            case 17:
                return "Login Error: Incorrect Password!";
            case 18:
                return "Login Error: Database Error Getting User Type!";
            case 19:
                return "Login Error: Database Error Validating Password!";
            case 20:
                return "Login Error: Database Error Checking if Application is Filled Out!";
            case 21:
                return "Login Error: Apartment Allotted, but you have not moved in yet!";
            case 22:
                return "Login Error: Username too long";
            case 23:
                return "Login Error: Password too long. Passwords need to be\nless than 15 characters.";
            case 24:
                return "Selection Error: No selection highlighted.";
            case 25:
                return "Application Review Error: User already has Apartment Allotted to them!";
            case 26:
                return "Application Review Error: Error Checking for Apartment Allotment!";
            case 27:
                return "Input entry Error: One or more fields is too long or contain illegal characters.";
            case 28:
                return "Billionare Club Error: We do not accept trillionaires at our prestigious offerings.";
            case 29:
                return "-";
            case 30:
                return "-";
            case 31:
                return "Apartment Allotment Error: Database Error Getting Available Apartment Values!";
            case 32:
                return "Apartment Allotment Error: Database Error Creating Resident";
            case 33:
                return "-";
            case 34:
                return "-";
            case 35:
                return "-";
            case 36:
                return "-";
            case 37:
                return "-";
            case 38:
                return "View Maintenance Requests Error: Database Error Getting Unresolved Requests!";
            case 39:
                return "View Maintenance Requests Error: Database Error Getting Resolved Requests!";
            case 40:
                return "View Maintenance Requests Error: Database Error Updating Unresolved Requests to Resolved!";
            case 41:
                return "-";
            case 42:
                return "-";
            case 43:
                return "-";
            case 44:
                return "Payment Info Error: Database Error Inserting Payment Info!";
            case 45:
                return "Payment Info Error: Database Error Retrieving Payment Info!";
            case 46:
                return "Selection Error: No card selected for Deletion.";
            case 47:
                return "Selection Error: Some payment information fields are blank.";
            case 48:
                return "Payment Info Error: Card Info Already Exists!";
            case 49:
                return "Payment Info Error: Database Error Deleting Card!";
            case 50:
                return "Maintenance Request Error: Database Error Retrieving Issue Types!";
            case 51:
                return "Maintenance Request Error: Database Error Inputting Maintenance Request!";
            case 52:
                return "Maintenance Request Error: Database Error Retrieving Request Info!";
            case 53:
                return "Maintenance Request Error: Maintenance Request for this Issue Type already made!";
            case 54:
                return "-";
            case 55:
                return "-";
            case 56:
                return "-";
            case 57:
                return "-";
            case 58:
                return "-";
            case 59:
                return "Rent Payment Error: Database Error Inputting Rent Payment!";
            case 60:
                return "Rent Payment Error: Database Error Retrieving Previous Rent Payment Information!";
            case 61:
                return "Rent Payment Error: Database Error Checking for Previous Payments!";
            case 62:
                return "Rent Payment Error: User has already paid for this month and year!";
            case 63:
                return "Rent Payment Error: Invalid Date!";
            case 64:
                return "-";
            case 65:
                return "-";
            case 66:
                return "-";
            case 67:
                return "-";
            case 68:
                return "Rent Reminder Error: Database Error Retrieving Remind-able Apartments!";
            case 69:
                return "Rent Reminder Error: Database Error Sending Reminder Message!";
            case 70:
                return "Rent Reminder Error: Reminder Already Sent today for this Apartment!";
            case 71:
                return "-";
            case 72:
                return "-";
            case 73:
                return "-";
            case 74:
                return "-";
            case 75:
                return "Home Message Error: Database Error Getting Unread Messages!";
            case 76:
                return "Home Message Error: Database Error Setting Messages Read!";
            case 77:
                return "-";
            case 78:
                return "-";
            case 79:
                return "-";
            case 80:
                return "-";
            case 81:
                return "Three Month Report Error: Database Error Getting Report!";
            case 82:
                return "-";
            case 83:
                return "-";
            case 84:
                return "Service Request Report Error: Database Error Getting Report!";
            case 85:
                return "-";
            case 86:
                return "-";
            case 87:
                return "Defaulter Report Error: Database Error Getting Report!";
            case 88:
                return "-";
            case 89:
                return "-";
            case 90:
                return "-";

        }
        
        return "Success!";
    }
}
