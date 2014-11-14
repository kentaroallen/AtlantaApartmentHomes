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
     * Deletes card information
     *
     * @param e the click button event that caused this.
     *
     * These params correspond to inputs from the UI.
     */
    public void deleteHandler(ActionEvent e) throws IOException {
        System.out.println("delete was clicked");
        controller.setScreen(this.getHomepage());
    }
    @FXML
    /**
     * Saves card information button
     */
    public void saveHandler(ActionEvent e) throws IOException {
        System.out.println("Save was clicked");
        controller.setScreen(this.getHomepage());
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
