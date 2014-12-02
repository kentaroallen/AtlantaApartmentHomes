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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
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
import sun.java2d.pipe.SpanShapeRenderer;

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
    private Date now = Calendar.getInstance().getTime();
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

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

            if (todaysDate.length() > 9) {
                ErrorCode.setCode(42);
                ErrorCode.errorPopUp();
            }

            if (!isDateValid(todaysDate)) {
                ErrorCode.setCode(43);
                ErrorCode.errorPopUp();
                return;
            }

            now = df.parse(todaysDate);

            PayRentSQLObject.payRent(CurrentUser.getUsername(), creditCard, CurrentUser.getApartmentNumber(), month, year, now, amtOwed);

            if (ErrorCode.getCurrentError() != 0) {

                return;
            }
            /*Go to different screen here.*/
            if (CurrentUser.getUserType() == 1) {
                controller.setScreen(this.getHomepage());
                clearFields();
            } else {
                controller.setScreen(this.getHomepageM());
                clearFields();
            }
            populateOncePR = true;
        } catch (Exception nullInput) {
            ErrorCode.setCode(10);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        }
    }

    public void exitHandler(ActionEvent e) {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("Exit to home page");
        populateOncePR = true;
        if (CurrentUser.getUserType() == 1) {
            controller.setScreen(this.getHomepage());
            clearFields();
        } else {
            controller.setScreen(this.getHomepageM());
            clearFields();
        }
    }

    public void clearFields() {
        aptfield.setText("");
        monthfield.setValue("");
        yearfield.setValue("");
        cardfield.setValue("");
        duefield.setText("");
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
        if (populateOncePR) {
            System.out.println("auto populated pay rent controller.");
            aptfield.setText(CurrentUser.getApartmentNumber() + "");

            ArrayList<String> cardNumbers = payInfoCardNumbers();
            ObservableList<String> obListCards = FXCollections.observableArrayList(cardNumbers);
            cardfield.setItems(obListCards);
            populateOncePR = false;
        } else {
            System.out.println("Prevented auto populate for data integrity.");
        }

    }

    /**
     * Set the SQL here
     *
     * @param e
     * @throws Exception
     */
    public void setAmountDue(ActionEvent e) throws Exception {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        if (!isDateValid(datefield.getText().toString())) {
            return;
        }

        try {
            now = df.parse(datefield.getText().toString());
            System.out.println(now.toString());
            String mosRent = monthfield.getValue().toString();
            String yearRent = yearfield.getValue().toString();
            String DueAmount = PayRentSQLObject.amountOwed(CurrentUser.getUsername(), CurrentUser.getApartmentNumber(), CurrentUser.getRentAmount(), now, Integer.parseInt(mosRent), Integer.parseInt(yearRent)) + "";
            duefield.setText(DueAmount + "");
        } catch (Exception bs) {

            System.out.println("Error: Both things are not set yet.");
        }

    }

    public static ArrayList<String> payInfoCardNumbers() {

        ArrayList<String> out = new ArrayList<String>();

        for (String[] s : PaymentInformationSQLObject.getPaymentInfo(CurrentUser.getUsername())) {//

            out.add(s[0]);
        }

        return out;
    }

    public static boolean isDateValid(String s) {

        String out[] = s.split("/+");

        try {

            if (isDateValid(Integer.parseInt(out[2]), Integer.parseInt(out[0]), Integer.parseInt(out[1]))) {

                return true;
            }

            ErrorCode.setCode(63);
            ErrorCode.errorPopUp();
            return false;
        } catch (Exception e) {

            ErrorCode.setCode(63);
            ErrorCode.errorPopUp();
            return false;
        }
    }

    public static boolean isDateValid(int year, int month, int day) {
        boolean dateIsValid = true;
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
