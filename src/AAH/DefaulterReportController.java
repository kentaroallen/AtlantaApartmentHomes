/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Defaulters;
import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class DefaulterReportController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */

    @FXML
    private TableColumn aptcol;
    @FXML
    private TableColumn paidcol;
    @FXML
    private TableColumn defaultcol;
    @FXML
    private TableView tablefield;
    @FXML
    private ComboBox monthchose;

    private boolean populateOnceDefaulter = true;

    /**
     * This handler chooses the month picked in the combo box.
     * @param e
     * @throws IOException 
     */
    public void monthHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        String chosenMonth = monthchose.getValue().toString().toLowerCase();
        System.out.println("month chosen " + chosenMonth);
        int monthInt = 0;
        if (chosenMonth.equals("january"))
            monthInt = 1;
        if (chosenMonth.equals("february"))
            monthInt = 2;
        if (chosenMonth.equals("march"))
            monthInt = 3;
        if (chosenMonth.equals("april"))
            monthInt = 4;
        if (chosenMonth.equals("may"))
            monthInt = 5;
        if (chosenMonth.equals("june"))
            monthInt = 6;
        if (chosenMonth.equals("july"))
            monthInt = 7;
        if (chosenMonth.equals("august"))
            monthInt = 8;
        if (chosenMonth.equals("september"))
            monthInt = 9;
        if (chosenMonth.equals("october"))
            monthInt = 10;
        if (chosenMonth.equals("november"))
            monthInt = 11;
        if (chosenMonth.equals("december"))
            monthInt = 12;

        populateTableBasedOnMonth(monthInt);
    }
    /**
     * This populates the tables according to the selected month.
     * @param month 
     */
    public void populateTableBasedOnMonth(int month) {
        ArrayList<Defaulters> tablePopulator = new ArrayList<Defaulters>();

        ArrayList<String[]> out = DefaulterReportSQLObject.getDefaulterReport(month);

        for (int apt = 0; apt < out.size(); apt++)
        {
            tablePopulator.add(new Defaulters(out.get(apt)[0], out.get(apt)[1], out.get(apt)[2]));
        }


//        if (month.contains("january") || month.contains("december")) {
//            tablePopulator.add(new Defaulters("1234", "100", "20"));
//            tablePopulator.add(new Defaulters("3243", "200", "10"));
//            tablePopulator.add(new Defaulters("5452", "300", "5"));
//        }else{
//            tablePopulator.add(new Defaulters("999", "999", "9"));
//        }

        /*Month, Category, Apartment*/
        ObservableList<Defaulters> obList = FXCollections.observableArrayList(tablePopulator);
        tablefield.setItems(obList);
    }
    /*This may not be needed since table is loaded only after a selection choice.*/
    public void autoPopulateDefaulter() {
        if (populateOnceDefaulter) {
            System.out.println("Auto populated defaulter report");

            populateOnceDefaulter = false;
        } else {
            System.out.println("Prevented auto populate @ Defaulter report");
        }
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
        ArrayList<String> posGender = new ArrayList<String>(Arrays.asList(monthNames));
        ObservableList<String> obListmo = FXCollections.observableArrayList(posGender);

        monthchose.setItems(obListmo);

        aptcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("apartment"));
        paidcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("extramoney"));
        defaultcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("defaultby"));

        this.setTitleLabel("Defaulter Report");
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
