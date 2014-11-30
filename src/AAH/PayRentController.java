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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class PayRentController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField datefield;
    @FXML
    private TextField aptfield;
    @FXML
    private ComboBox monthfield;
    @FXML
    private ComboBox yearfield;
    @FXML
    private TextField duefield;
    @FXML
    private ComboBox cardfield;

    private boolean monthSet;
    private boolean yearSet;
    /**
     * Prevents multiple auto populates from fucking up data.
     */
    private boolean populateOncePR = true;
    @FXML
    /**
     * This will take in the apartment number, rent for which month and year,
     * amount due, and card
     *
     * @param e the click button event that caused this.
     * @param aptNum apartment number
     * @param mos month
     * @param year year
     * @param cc card to use
     */
    public void payHandler(ActionEvent e) throws IOException {
        try {
            String aptNum = aptfield.getText().toString();
            String mosRent = monthfield.getValue().toString();
            String yearRent = yearfield.getValue().toString();
            String creditCard = cardfield.getValue().toString();
            String todaysDate = datefield.getText().toString();

            System.out.println("pay clicked");
            System.out.println("apt number: " + aptNum + " monthRent: " + mosRent + " yearRent: " + yearRent
                    + " credit card num: " + creditCard + " todaysDate: " + todaysDate);

            int amtOwed = Integer.parseInt(duefield.getText());
            int month = Integer.parseInt(mosRent);
            int year = Integer.parseInt(yearRent);

            PayRentSQLObject.payRent(CurrentUser.getUsername(), creditCard, CurrentUser.getApartmentNumber(), month, year, Calendar.getInstance().getTime(), amtOwed);

            if (ErrorCode.getCurrentError() != 0) {

                return;
            }
            /*Go to different screen here.*/
            controller.setScreen(this.getHomepage());
            populateOncePR = true;
        } catch (Exception nullInput) {
            ErrorCode.setCode(10);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }
    public void exitHandler(ActionEvent e) {
        System.out.println("Exit to home page");
        controller.setScreen(this.getHomepage());
    }

    @Override
    /**
     * Card will need to be populated from SQL here. It will be marked
     */
    public void initialize(URL url, ResourceBundle rb) {
        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDay = localCalendar.get(Calendar.DATE);
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);
        String date = currentMonth + "/" + currentDay + "/" + currentYear;
       // System.out.println(date);

        datefield.setText(date);

        ArrayList<Integer> posMons = new ArrayList<Integer>();
        for (int i = 1; i < 13; i++) {
            posMons.add(i);
        }
        ObservableList<Integer> obListMonths = FXCollections.observableArrayList(posMons);
        ArrayList<Integer> posYears = new ArrayList<Integer>();
        posYears.add(2014);
        posYears.add(2015);
        ObservableList<Integer> obListYears = FXCollections.observableArrayList(posYears);

        /*Populate the credit card numbers from SQL here*/
        
        /*SQL NEEDED*/

        
        monthfield.setItems(obListMonths);
        yearfield.setItems(obListYears);

        this.setTitleLabel(this.getLogin());
    }

    public void autoPopulatePR() {
        if(populateOncePR){
        System.out.println("auto populated pay rent controller.");
        aptfield.setText(CurrentUser.getApartmentNumber() + "");
        String DueAmount = "x";
        duefield.setText(DueAmount + "");
        
        ArrayList<String> cardNumbers = payInfoCardNumbers();
        ObservableList<String> obListCards = FXCollections.observableArrayList(cardNumbers);
        cardfield.setItems(obListCards);
        populateOncePR = false;
        }else{
            System.out.println("Prevented auto populate for data integrity.");
        }
        
    }

    public void setMonth() {
        monthSet = true;
        setRent();
    }
    public void setYear() {
        yearSet = true;
        setRent();
    }

    public void setRent() {

        if (monthSet && yearSet) {

            int month = Integer.parseInt(monthfield.getValue().toString());
            int year = Integer.parseInt(yearfield.getValue().toString());
            int set = PayRentSQLObject.amountOwed(CurrentUser.getUsername(), CurrentUser.getApartmentNumber(), CurrentUser.getRentAmount(), Calendar.getInstance().getTime(), month, year);
            duefield.setText(set+"");
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
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
