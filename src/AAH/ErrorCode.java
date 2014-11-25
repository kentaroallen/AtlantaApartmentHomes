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
     24 :
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
     46 :



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
        popup.setTitle("Errorcode");
        popup.setWidth(500);
        popup.setHeight(150);
        popup.setScene(popup_scene);
        popup.show();
        popup_hbox.setAlignment(Pos.CENTER);
        popup_hbox.setSpacing(10);
        Label errorlabel = new Label();
        errorlabel.setStyle("-fx-font: 12px Stencil;");
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
                return "-";
            case 14:
                return "-";
            case 15:
                return "-";
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
                return "-";
            case 22:
                return "-";
            case 23:
                return "-";
            case 24:
                return "-";
            case 25:
                return "-";
            case 26:
                return "-";
            case 27:
                return "-";
            case 28:
                return "-";
            case 29:
                return "-";
            case 30:
                return "-";
            case 31:
                return "Apartment Allotment Error: Database Error Getting Available Apartment Values!";
            case 32:
                return "-";
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
                return "-";
            case 47:
                return "-";
            case 48:
                return "-";
            case 49:
                return "-";
            case 50:
                return "-";
        }
        
        return null;
    }
}
