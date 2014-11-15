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
public class RequestMaintenanceController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */

    @FXML
    private TextField aptfield;
    @FXML
    private ComboBox issuebox;
    @FXML
    private Label datelabel;

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
     * This will take in the apartment number only.
     *
     * @param e the click button event that caused this.
     * @param apartmentNum is the apartment number that was input
     */
    public void requestHandler(ActionEvent e) throws IOException {

        //String apartmentNum = aptfield.getText().toString();

        /*SQL logic here*/
        /*Go to different screen here.*/
        controller.setScreen(this.getHomepage());
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> posIssues = new ArrayList<String>();
        String[] types = {"Roaches", "Broken Appliances", "Just come now"};
        posIssues.addAll(Arrays.asList(types));
        ObservableList<String> obListIssues = FXCollections.observableArrayList(posIssues);

        Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDay = localCalendar.get(Calendar.DATE);
        int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
        int currentYear = localCalendar.get(Calendar.YEAR);
        String date = currentMonth + "/" + currentDay + "/" + currentYear;
        //    System.out.println(date);

        datelabel.setText(date);

        issuebox.setItems(obListIssues);
        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
