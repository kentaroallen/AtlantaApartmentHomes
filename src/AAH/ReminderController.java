/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Apartment;
import AAH.model.Person;
import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class Everything about the application review is a straight
 * hack.
 *
 *
 * @author Kentaro
 */
public class ReminderController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;

    /**
     * Initializes the controller class.
     */
    @FXML
    /**
     * This should save what information you clicked for the next screen.
     * chosenPerson contains the data from the selectedrow
     *
     * @param e the click button event that caused this.
     */
    public void sendHandler(ActionEvent e) throws IOException {
        System.out.println("Things were hit.");
        controller.setScreen(this.getHomepage());
    }

    @Override
    /**
     * This should populate the table with each application needed to review.
     */
    public void initialize(URL url, ResourceBundle rb) {

        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
