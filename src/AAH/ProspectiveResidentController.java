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
public class ProspectiveResidentController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox months;
    @FXML
    private ComboBox days;
    @FXML
    private ComboBox years;
    @FXML
    private ComboBox aptcategory;
    @FXML
    private TextField monthlyIncomeField;
    @FXML
    private TextField prevResField;
    @FXML
    private TextField minRent;
    @FXML
    private TextField maxRent;
    @FXML
    private DatePicker prefdate;
    @FXML
    private ComboBox leaseterm;
    @FXML
    private ComboBox genderbox;

    @FXML
    /**
     * When the user clicks the "New User?" hyperlink.
     *
     * @param e the click event.
     */
    public void newUserHandler(ActionEvent e) throws IOException {
        System.out.println("New user clicked");
        controller.setScreen(this.getNewUserReg());
    }


    public boolean allSet() {

        return (!( (prefdate.getValue() == null) || (usernameField.getText() == null) || (usernameField.getText() == "") ));
    }

    @FXML
    /**
     * This will take in the username and password for sql retrieval.
     *
     * @param e the click button event that caused this.
     * 
     * These params correspond to inputs from the UI.
     * @param name the name of the resident
     * @param month the month field for DOB
     * @param year  the year field for DOB
     * @param day the day field for DOB
     * @param monthlyIncome the monthly income for resident
     * @param prevResidence string of the previous residence
     * @param apartmentCategory for example "1BR-1B" 1 bedroom 1 bath
     * @param minimumRent int 
     * @param maximumRent int
     * @param prefMoveInDate This is an array. [0] is year [1] is month [2] is day for the preferred move in date
     * @param lease this is how long the lease is
     * @param resGender this is the residents gender
     */
    public void loginHandler(ActionEvent e) throws Exception { // change this name to registration handler

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        if (!allSet()) {

            ErrorCode.setCode(10);
            System.out.println(ErrorCode.errorMessage());
            return;
        }

        LocalDate date = prefdate.getValue();
        String name = usernameField.getText();
        String month = months.getValue().toString();
        int year = Integer.parseInt(years.getValue().toString());
        int day = Integer.parseInt(days.getValue().toString());
        int monthlyIncome = Integer.valueOf(monthlyIncomeField.getText());
        String prevResidence = prevResField.getText();
        String apartmentCategory = aptcategory.getValue().toString();
        int minimumRent = Integer.valueOf(minRent.getText());
        int maximumRent = Integer.valueOf(maxRent.getText());
        String[] prefMoveInDate = new String[3];
        prefMoveInDate[0] = date.toString().substring(0, 4);    //YEAR
        prefMoveInDate[1] = date.toString().substring(5, 7);     //MONTH
        prefMoveInDate[2] = date.toString().substring(8, 10);    //DAY
        String lease = leaseterm.getValue().toString();
        String resGender = genderbox.getValue().toString();

        /*SQL logic here*/


        System.out.println("Name: " + name + "month " + month + year + day + monthlyIncome + " " +
               prevResidence + apartmentCategory + " " + minimumRent + " " + prefMoveInDate.length + " " + lease + resGender + " " + minimumRent + maximumRent);

        int leaseValue = Integer.parseInt(lease);
        Date dob = new Date(year, Integer.parseInt(month), day);
        Date moveInDate = new Date(Integer.parseInt(prefMoveInDate[0]), Integer.parseInt(prefMoveInDate[1]), Integer.parseInt(prefMoveInDate[2]));
        String gender = (resGender.equals("Male")) ? "M" : (resGender.equals("Female")) ? "F" : "N";
        /*Go to different screen here.*/

        ProspectiveResidentSQLObject.insertProspectiveResident(CurrentUser.getUsername(), dob, name, gender, moveInDate, leaseValue, monthlyIncome, apartmentCategory, prevResidence, minimumRent, maximumRent, "Accepted");
        NewUserRegSQLObject.insertUser(CurrentUser.getUsername(), CurrentUser.getPassword());

        if (ErrorCode.getCurrentError() == 0) {
            controller.setScreen(this.getLogin());
        }
        else {

            System.out.println(ErrorCode.errorMessage());
        }
        //String username, Date dob, String name, String gender, Date moveIn, int leaseTerm,  int monthlyIncome, String category, String prevAddress, int minRent, int maxRent, String apartmentStatus
    }


    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Integer> posMonths = new ArrayList<Integer>();
        for (int i = 1; i < 13; i++) {
            posMonths.add(i);
        }

        ObservableList<Integer> oblist = FXCollections.observableArrayList(posMonths);
        ArrayList<Integer> posDays = new ArrayList<Integer>();
        for (int i = 0; i < 32; i++) {
            posDays.add(i);
        }

        ObservableList<Integer> obListdays = FXCollections.observableArrayList(posDays);
        ArrayList<Integer> posYears = new ArrayList<Integer>();
        for (int i = 1900; i < 2015; i++) {
            posYears.add(i);
        }
        ObservableList<Integer> obListyears = FXCollections.observableArrayList(posYears);

        // HERE, WE GRAB OUR APARTMENT INFO

        String[] apartmentCat = availableApartmentCategories();
        String[] apartmentLease = availableApartmentLeaseTerms();

        if (apartmentCat.length == 0 || apartmentLease.length == 0) {

            System.out.println("No Apartments Available!");
        }

        //WEEEEEEEEEEEE

        ArrayList<String> posCats = new ArrayList<String>();
        String[] types = apartmentCat;//{"1BR-1B", "1BR-2B", "2BR-1B", "2BR-1B"};
        posCats.addAll(Arrays.asList(types));
        ObservableList<String> obListcats = FXCollections.observableArrayList(posCats);

        ArrayList<String> posLeaseTerm = new ArrayList<String>();
        String[] leaseTypes = apartmentLease;//{"6 months", "1 year", "1.5 years", "2 years"};
        posLeaseTerm.addAll(Arrays.asList(leaseTypes));
        ObservableList<String> obListLease = FXCollections.observableArrayList(posLeaseTerm);

        ArrayList<String> posGender = new ArrayList<String>();
        posGender.add("Male");
        posGender.add("Female");
        posGender.add("N/A");
        ObservableList<String> obListGender = FXCollections.observableArrayList(posGender);

        genderbox.setItems(obListGender);
        leaseterm.setItems(obListLease);
        aptcategory.setItems(obListcats);
        years.setItems(obListyears);
        days.setItems(obListdays);
        months.setItems(oblist);
        this.setTitleLabel(this.getNewUserReg());
    }

    public String[] availableApartmentCategories() {

        String[] apartmentCat = {};
        ArrayList<String> ac = null;
        try {
            ac = ProspectiveResidentSQLObject.getAvailableApartmentCategories();
            apartmentCat = new String[ac.size()];

            int iterator = 0;
            for (String s : ac) {

                apartmentCat[iterator++] = s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apartmentCat;
    }

    public String[] availableApartmentLeaseTerms() {

        String[] apartmentLease = {};
        ArrayList<String> al = null;
        try {
            al = ProspectiveResidentSQLObject.getAvailableApartmentLeaseTerms();
            apartmentLease = new String[al.size()];

            int iterator = 0;
            for (String s : al) {

                apartmentLease[iterator++] = s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apartmentLease;
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
