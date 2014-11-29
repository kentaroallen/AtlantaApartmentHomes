/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import static AAH.ErrorCode.errorMessage;
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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
            /*The real way involves opening a new stage up
             I will get to that later.
             */

            controller.setScreen(this.getThreeMonthReport());
            populateOnceHome = true;
        } else if (selectedReport.contains("service")) {
            System.out.println("Service request report");
            controller.setScreen(this.getServiceReport());
            populateOnceHome = true;
        } else {
            System.out.println("rent defaulter report");
            controller.setScreen(this.getDefaulterReport());
            populateOnceHome = true;
        }

    }

    /**
     * Read the latest mail message.
     */
    public void readLatestMail() {
        Stage popup = new Stage();
        HBox popup_hbox = new HBox();
        Scene popup_scene = new Scene(popup_hbox, 300, 100);
        popup.setTitle("Mail");
        popup.setWidth(500);
        popup.setHeight(150);
        popup.setScene(popup_scene);
        popup.show();
        popup_hbox.setAlignment(Pos.CENTER);
        popup_hbox.setSpacing(10);
        Label mailLabel = new Label();
        popup_hbox.getChildren().addAll(mailLabel);

        mailLabel.setText("Hey you smell like taco sandwich \n\n -Management");

    }

    /**
     * the auto populate for home page mail function
     */
    public void autoPopulateHome() {
        if (populateOnceHome) {
            System.out.println("Auto populated the home page");
            mail.setText("1 new message");
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
        repType.add("LeasingReport");
        repType.add("ServiceRequestReport");
        repType.add("RentDefaulterReport");
        ObservableList<String> obListReport = FXCollections.observableArrayList(repType);

        reportbox.setItems(obListReport);
        this.setTitleLabel(this.getHomepage());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
