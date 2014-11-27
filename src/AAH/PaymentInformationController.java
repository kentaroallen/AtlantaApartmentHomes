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

    @FXML
    /**
     * Deletes card information
     *
     * @param e the click button event that caused this.
     *
     * These params correspond to inputs from the UI.
     */
    public void deleteHandler(ActionEvent e) throws IOException {
        System.out.println("Delete was clicked");
        try{
            String cardInfo = cardbox.getValue().toString();
            System.out.println("card info is: " + cardInfo);
            controller.setScreen(this.getHomepage());
        }catch(Exception nullInput){
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
        System.out.println("Save was clicked");
        String name = namefield.getText();
        String card = cardfield.getText();
        String month = monthfield.getText();
        String year = yearfield.getText();
        String cw = cwfield.getText();
        System.out.println(name + " " + card + " " + month + " "
                + year + " " + cw);

        if (name.equals("") || card.equals("") || month.equals("") || year.equals("") || cw.equals("")) {
            ErrorCode.setCode(47);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        } else {
            controller.setScreen(this.getHomepage());
        }

    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        /*Need to load cardbox with sql statements into an observable list*/
        ArrayList<String> posCards = new ArrayList<String>();
        posCards.add("1234567890");
        posCards.add("0987654321");
        posCards.add("1122334455");
        ObservableList<String> obListCards = FXCollections.observableArrayList(posCards);
        cardbox.setItems(obListCards);
        
        
        this.setTitleLabel(this.getNewUserReg());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
