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
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

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

    public void payRentHandler(ActionEvent e) throws IOException {
        System.out.println("pay rent clicked");
        controller.setScreen(this.getPayRent());
    }

    public void requestMaintenanceHandler(ActionEvent e) throws IOException {
        System.out.println("request maintenance clicked");
        controller.setScreen(this.getRequestMaintenance());
    }

    public void paymentInformationHandler(ActionEvent e) throws IOException {
        System.out.println("payment information clicked");
        controller.setScreen(this.getPaymentInformation());
    }

    public void applicationReviewHandler(ActionEvent e) throws IOException {
        System.out.println("application review clicked");
        controller.setScreen(this.getApplicationReview());
    }

    public void mainRequestsHandler(ActionEvent e) throws IOException {
        controller.setScreen(this.getViewRequests());
        System.out.println("maintenance request clicked");
    }

    public void rentReminderHandler(ActionEvent e) throws IOException {
        controller.setScreen(this.getReminder());
        System.out.println("rent reminder clicked");
    }

    public void reportHandler(ActionEvent e) throws IOException {
        String sb = "";
        sb = reportbox.getValue().toString();
        System.out.println(sb);
        System.out.println("Report handler clicked");

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
