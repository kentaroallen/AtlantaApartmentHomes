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
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class Everything about the application review is a straight
 * hack.
 *
 *
 * @author Kentaro
 */
public class ReminderController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    @FXML
    private Label datelabel;
    @FXML
    private ComboBox aptnobox;
    @FXML
    private TextArea messagearea;
    private boolean populateOnceRem = true;
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
        String aptnum = "";
        aptnum = aptnobox.getValue().toString();
        String outputMessage = "";
        outputMessage = outputMessage = messagearea.getText().toString();
        System.out.println("Message to: " + aptnum + "\nContaining: \n" + outputMessage);
        controller.setScreen(this.getHomepage());
        populateOnceRem = true;
    }

    /**
     * This is a cancel button to return to the homepage.
     *
     * @param e
     * @throws IOException
     */
    public void cancelHandler(ActionEvent e) throws IOException {
        System.out.println("Cancel hit");
        controller.setScreen(this.getHomepage());
        populateOnceRem = true;
    }

    public void autoPopulateRem() {
        if(populateOnceRem) {
            System.out.println("Auto populated reminder controller.");
            ArrayList<String> delinquentApts = new ArrayList<String>();
            delinquentApts.add("1544");
            delinquentApts.add("1206");
            delinquentApts.add("5623");
            ObservableList<String> obListDelinquents = FXCollections.observableArrayList(delinquentApts);
            aptnobox.setItems(obListDelinquents);
            populateOnceRem = false;
        } else {
            System.out.println("Prevented auto populate @ ReminderController");
        }
    }

    @Override
    /**
     * This should populate the table with each application needed to review.
     */
    public void initialize(URL url, ResourceBundle rb) {

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDay = localCalendar.get(Calendar.DATE);
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);
        String date = currentMonth + "/" + currentDay + "/" + currentYear;
        datelabel.setText(date);

        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
