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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class HomepageController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox reportbox;
    @FXML
    private Hyperlink mail;
    @FXML
    private TextArea messagearea;
    @FXML
    private AnchorPane managepane;
  

    private boolean populateOnceHome = true;

    public void payRentHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 1) {
            return;
        }
        System.out.println("pay rent clicked");
        controller.setScreen(this.getPayRent());
        populateOnceHome = true;
    }

    public void requestMaintenanceHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 1) {
            return;
        }
        System.out.println("request maintenance clicked");
        controller.setScreen(this.getRequestMaintenance());
        populateOnceHome = true;
    }

    public void paymentInformationHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 1) {
            return;
        }
        System.out.println("payment information clicked");
        controller.setScreen(this.getPaymentInformation());
        populateOnceHome = true;
    }

    public void applicationReviewHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 2) {
            return;
        }
        System.out.println("application review clicked");
        controller.setScreen(this.getApplicationReview());
        populateOnceHome = true;
    }

    public void mainRequestsHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 2) {
            return;
        }
        controller.setScreen(this.getViewRequests());
        System.out.println("maintenance request clicked");
        populateOnceHome = true;
    }

    public void rentReminderHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 2) {
            return;
        }
        controller.setScreen(this.getReminder());
        System.out.println("rent reminder clicked");
        populateOnceHome = true;
    }
    /*
     * Amier if you see this remind me to make it such that we get pop up reports
     * This is fine for now but I want it the other way.
     */

    public void reportHandler(ActionEvent e) throws IOException {

        if (CurrentUser.getUserType() != 2) {
            return;
        }
        System.out.println("Report handler clicked");
        String selectedReport = reportbox.getValue().toString().toLowerCase();
        if (selectedReport.contains("leasing")) {
            System.out.println("Three month report");
            setReportPopUp(0);
            populateOnceHome = true;
        } else if (selectedReport.contains("service")) {
            System.out.println("Service request report");
            setReportPopUp(1);
            populateOnceHome = true;
        } else {
            System.out.println("rent defaulter report");
            setReportPopUp(2);
            populateOnceHome = true;
        }

    }
    /**
     * The action handled upon logout
     * @param e 
     * @throws Exception 
     */
    public void logoutHandler(ActionEvent e) throws Exception{

        CurrentUser.clear();
        System.out.println("Logged out");
        controller.setScreen(this.getLogin());
        populateOnceHome = true;
        
    }

    /**
     * Loads the pop up screen report. Data handling handled in respected controller.
     * 0 for ThreeMonth, 1 for Service and 2 for Defaulter
     * @param reportType
     */
    public void setReportPopUp(int reportType) {
        String[] types = {"ThreeMonthReport", "ServiceReport", "DefaulterReport"};
        try {
            Stage popup = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("view/" + types[reportType] + ".fxml"));
            Scene popup_scene = new Scene(root);
            popup.setTitle(types[reportType]);
            popup.setScene(popup_scene);
            popup.show();
        } catch (Exception e) {
            System.out.println("failure to open. " + types[reportType]);
        }
    }

    /**
     * Read the latest mail message.
     */
    public void readLatestMail() {

        if (CurrentUser.getUserType() == 2) {
            return;
        }

        try {
            Stage popup = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("view/MailBox.fxml"));
            Scene popup_scene = new Scene(root);
            popup.setTitle("Mail Box");
            popup.setScene(popup_scene);
            popup.show();
            populateOnceHome = true;

        } catch (Exception e) {
            System.out.println("failure to open mailbox.");
        }
    }

    /**
     * the auto populate for home page mail function
     */
    public void autoPopulateHome() {
        if (populateOnceHome) {
            System.out.println("Auto populated the home page");
            String numberOfMessages = (CurrentUser.getUserType() == 2) ? "0" : ""+MailBoxSQLObject.getUnreadMessages(CurrentUser.getUsername()).size();
            mail.setText(numberOfMessages + " unread messages");
            populateOnceHome = false;
        } else {
            System.out.println("prevented auto populate on home");
        }
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> repType = new ArrayList<String>();
        repType.add("Leasing Report");
        repType.add("Service Request Report");
        repType.add("Rent Defaulter Report");
        ObservableList<String> obListReport = FXCollections.observableArrayList(repType);

        reportbox.setItems(obListReport);
        //this.setTitleLabel(this.getHomepage());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
