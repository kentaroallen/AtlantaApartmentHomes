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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class NewUserRegController extends ScreenTemplate implements Initializable, SetControlScreen {

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
    private PasswordField confirmField;

    @FXML
    /**
     * This will take in the username,password, and confirmation for SQL retrieval.
     *
     * @param e the click button event that caused this.
     */
    public void registerHandler(ActionEvent e) throws IOException {
        String username;
        String password;
        String confirm;
        username = usernameField.getText();
        password = passwordField.getText();
        confirm = confirmField.getText();

        /*SQL logic here*/
        
        
        System.out.println("Register clicked \t Username is: " + username 
               + " password is: " + password
               + " confirm is: " + confirm);
        
        /*Go to different screen here.*/
        controller.setScreen(this.getProspective());
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
