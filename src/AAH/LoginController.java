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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class LoginController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    /**
     * When the user clicks the "New User?" hyperlink.
     *
     * @param e the click event.
     */
    public void newUserHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("New user clicked");
        controller.setScreen(this.getNewUserReg());
        clearFields();
    }

    @FXML
    /**
     * This will take in the username and password for sql retrieval.
     *
     * @param e the click button event that caused this.
     */
    public void loginHandler(ActionEvent e) throws Exception {
    

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        String username;
        String password;
        username = usernameField.getText();
        password = passwordField.getText();
        
        /*This is where you should probably
        set the aptfield problem*/
      /*  if(username != "donutresidentman"){
            PayRentController.aptfield.setText("1112");
        }*/

        /*SQL logic here*/
        if (!LoginSQLObject.validateLogin(username, password)) {//don't log in if we can't.

            return;
        }

        LoginSQLObject.setCurrentUser(username, password);

        if ((CurrentUser.getUserType() == 0) && !LoginSQLObject.filledOutApplication(username)) {

            controller.setScreen(getProspective());
            return;
        }

        if (ErrorCode.currentError == 0) {

            System.out.println(CurrentUser.getUserType());

            if (CurrentUser.getUserType() != 0) {
                controller.setScreen(this.getHomepage());
                clearFields();

            }

            else {

                prospectivePopUp();
                System.out.println("Application Under Review.");
            }
            return;
        } else {

            return;
        }

        /*Go to different screen here.*/
    }
    public void clearFields(){
        usernameField.setText("");
        passwordField.setText("");
        
    }

    public static void prospectivePopUp(){
        Stage popup = new Stage();
        HBox popup_hbox = new HBox();
        Scene popup_scene = new Scene(popup_hbox, 300, 100);
        popup.setTitle("Prospective Resident");
        popup.setWidth(500);
        popup.setHeight(150);
        popup.setScene(popup_scene);
        popup.show();
        popup_hbox.setAlignment(Pos.CENTER);
        popup_hbox.setSpacing(10);
        Label errorlabel = new Label();
        // errorlabel.setStyle("-fx-font: 12px Stencil;");
        popup_hbox.getChildren().addAll(errorlabel);

        errorlabel.setText("Application Under Review.");
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        this.setTitleLabel(this.getLogin());
        
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
