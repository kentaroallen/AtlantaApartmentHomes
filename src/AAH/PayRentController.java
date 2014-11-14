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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
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
    private Label label;
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
    @FXML
    /**
     * This will take in the apartment number, rent for which month and year, amount due, and card
     *
     * @param e the click button event that caused this.
     * @param aptNum apartment number
     * @param mos month
     * @param year year
     * @param cc card to use
     */
    public void payHandler(ActionEvent e) throws IOException {
//        String aptNum = aptfield.getText().toString();
//        String mos = monthfield.getValue().toString();
//        String year = yearfield.getValue().toString();
//        String cc = cardfield.getValue().toString();
//        
//        System.out.println("pay clicked");
//        System.out.println(aptNum + mos + year+ cc);
        /*Go to different screen here.*/
        controller.setScreen(this.getHomepage());
    }

    @Override
    /**
     * Card will need to be populated from SQL here. It will be marked
     */
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(date.getMonth());
        sb.append("/");
        sb.append(date.getDay());
        sb.append("/2015");
        datefield.setText(sb.toString());
        
        ArrayList<Integer> posMons = new ArrayList<Integer>();
        for(int i = 1 ;i < 13; i++){
            posMons.add(i);
        }
        ObservableList<Integer> obListMonths = FXCollections.observableArrayList(posMons);
        ArrayList<Integer> posYears = new ArrayList<Integer>();
        posYears.add(2014);
        posYears.add(2015);
        ObservableList<Integer> obListYears= FXCollections.observableArrayList(posYears);
        
        /*Populate the credit card numbers from SQL here*/
        ArrayList<String> cardNumbers = new ArrayList<String>();
        cardNumbers.add("81571581142");
        cardNumbers.add("412844812441");
        ObservableList<String> obListCards = FXCollections.observableArrayList(cardNumbers);
        /*SQL NEEDED*/
        
        cardfield.setItems(obListCards);
        monthfield.setItems(obListMonths);
        yearfield.setItems(obListYears);
               
        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
