/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
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
            controller.setScreen(this.getHomepage());
            populateOncePI = true;
        } catch (Exception nullInput) {
            ErrorCode.setCode(46);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
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
            controller.setScreen(this.getHomepage());
            populateOncePI = true;
        }

    }

    public void autoPopulatePI() {
        if (populateOncePI) {
            /*You can use this to autoPopulate the list of cards the user
             may want to delete from the database.*/
            System.out.println("auto populated payment information controller.");
            ArrayList<String> posCards = new ArrayList<String>();
            posCards.add("1234567890");
            posCards.add("14535235535");
            posCards.add("13371337133");
            posCards.add("0987654321");
            posCards.add("1122334455");
            ObservableList<String> obListCards = FXCollections.observableArrayList(posCards);
            cardbox.setItems(obListCards);
            populateOncePI = false;
        } else {
            System.out.println("Prevented auto populate for data integrity. @ payment information");
        }
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
