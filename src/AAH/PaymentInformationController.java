/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Date;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class PaymentInformationController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField namefield;
    @FXML
    private TextField cardfield;
    @FXML
    private TextField monthfield;
    @FXML
    private TextField yearfield;
    @FXML
    private TextField cwfield;
    @FXML
    private ComboBox cardbox;
    /*Data integrity.*/
    private boolean populateOncePI = true;

    @FXML
    /**
     * Deletes card information
     *
     * @param e the click button event that caused this.
     *
     * These params correspond to inputs from the UI.
     */
    public void deleteHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("Delete was clicked");
        try {
            String cardInfo = cardbox.getValue().toString();
            System.out.println("card info is: " + cardInfo);

            PaymentInformationSQLObject.deletePaymentInfo(cardInfo);
            if (ErrorCode.getCurrentError() != 0) {

                return;
            }

            if (CurrentUser.getUserType() == 1) {
                controller.setScreen(this.getHomepage());
                clearFields();
            } else {
                controller.setScreen(this.getHomepageM());
                clearFields();
            }
            populateOncePI = true;
        } catch (Exception nullInput) {
            ErrorCode.setCode(46);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public void exitHandler(ActionEvent e) {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("Exit to home page");
        if (CurrentUser.getUserType() == 1) {
            controller.setScreen(this.getHomepage());
            clearFields();
        } else {
            controller.setScreen(this.getHomepageM());
            clearFields();
        }
        clearFields();
    }

    public void clearFields() {
        namefield.setText("");
        cardfield.setText("");
        monthfield.setText("");
        cwfield.setText("");
        cardbox.setValue("");
        yearfield.setText("");
    }

    @FXML
    /**
     * Saves card information button
     */
    public void saveHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("Save was clicked");
        String name = namefield.getText();
        String card = cardfield.getText();
        String month = monthfield.getText();
        String year = "20" + yearfield.getText();
        String cw = cwfield.getText();
        System.out.println(name + " " + card + " " + month + " "
               + year + " " + cw);

        if (name.length() > 30) {
            ErrorCode.setCode(64);
            ErrorCode.errorPopUp();
            return;
        }
        if (card.length() != 16) {
            ErrorCode.setCode(65);
            ErrorCode.errorPopUp();
            return;
        }
        if (month.length() > 2) {
            ErrorCode.setCode(71);
            ErrorCode.errorPopUp();
            return;
        }
        if (year.length() > 4) {
            ErrorCode.setCode(72);
            ErrorCode.errorPopUp();
            return;
        }
        if (cw.length() > 3) {
            ErrorCode.setCode(73);
            ErrorCode.errorPopUp();
            return;
        }

        if (name.equals("") || card.equals("") || month.equals("") || year.equals("") || cw.equals("")) {
            ErrorCode.setCode(47);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        } else {

            int expyear = Integer.parseInt(year) - 1900;
            int expmonth = Integer.parseInt(month) - 1;
            Date exp = new Date(expyear, expmonth, 1);
            PaymentInformationSQLObject.insertPaymentInfo(card, cw, name, exp, CurrentUser.getUsername());

            if (ErrorCode.getCurrentError() != 0) {

                return;
            }
            if (CurrentUser.getUserType() == 1) {
                controller.setScreen(this.getHomepage());
                clearFields();
            } else {
                controller.setScreen(this.getHomepageM());
                clearFields();
            }
            clearFields();
            populateOncePI = true;
        }

    }

    public void autoPopulatePI() {
        if (populateOncePI) {
            /*You can use this to autoPopulate the list of cards the user
             may want to delete from the database.*/
            System.out.println("auto populated payment information controller.");

            ArrayList<String> posCards = payInfoCardNumbers();

            ObservableList<String> obListCards = FXCollections.observableArrayList(posCards);
            cardbox.setItems(obListCards);
            populateOncePI = false;
        } else {
            System.out.println("Prevented auto populate for data integrity. @ payment information");
        }
    }

    public static ArrayList<String> payInfoCardNumbers() {

        ArrayList<String> out = new ArrayList<String>();

        for (String[] s : PaymentInformationSQLObject.getPaymentInfo(CurrentUser.getUsername())) {

            out.add(s[0]);
        }

        return out;
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        /*Need to load cardbox with sql statements into an observable list*/
        this.setTitleLabel(this.getNewUserReg());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
