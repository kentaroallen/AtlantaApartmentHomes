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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private ChoiceBox months;
    @FXML
    private ChoiceBox days;
    @FXML
    private ChoiceBox years;
    @FXML
    private TextField monthlyIncomeField;
    @FXML
    private TextField prevResField;
    
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

    @FXML
    /**
     * This will take in the username and password for sql retrieval.
     *
     * @param e the click button event that caused this.
     */
    public void loginHandler(ActionEvent e) throws IOException {
        String name = usernameField.getText();
        String month = Integer.valueOf(months.getValue().toString()).toString(); 
        int year = Integer.valueOf(years.getValue().toString());
        int day = Integer.valueOf(days.getValue().toString());
        int monthlyIncome = Integer.valueOf(monthlyIncomeField.getText().toString());
        String prevResidence = prevResField.getText();
        
        
        
       // username = usernameField.getText();
        //password = passwordField.getText();

        /*SQL logic here*/
        System.out.println("Name: " + name + " income: " + monthlyIncome +
                        " prev residence: " + prevResidence);
        System.out.println("Months: " + month +
                           " Days" + day + " Years: " + year);
        
        /*Go to different screen here.*/
        
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> posMonths = new ArrayList<>();
        String[] mos = {"January", "February", "March", "April", "May",
                        "June", "July", "August", "September", "October", "November", "December"};
        posMonths.addAll(Arrays.asList(mos));
        ObservableList<String> oblist = FXCollections.observableArrayList(posMonths);
        ArrayList<Integer> posDays = new ArrayList<>();
        for(int i = 0; i < 32; i++){
            posDays.add(i);
        }
        ObservableList<Integer> obListdays = FXCollections.observableArrayList(posDays);
        ArrayList<Integer> posYears = new ArrayList<>();
        for(int i = 1900; i < 2015; i++){
            posYears.add(i);
        }
        ObservableList<Integer> obListyears = FXCollections.observableArrayList(posYears);
        years.setItems(obListyears);
        days.setItems(obListdays);
        months.setItems(oblist);
        this.setTitleLabel(this.getLogin());
    }
    @Override
    public void setScreenParent(ScreenController screen){
        controller = screen;
    }

}

