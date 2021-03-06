/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    public void registerHandler(ActionEvent e) throws Exception {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        String username;
        String password;
        String confirm;
        username = usernameField.getText();
        password = passwordField.getText();
        confirm = confirmField.getText();

        /*SQL logic here*/
        
        if(username.length() > 15){
            ErrorCode.setCode(22);
            ErrorCode.errorPopUp();
            return;
        }
        if(password.length() > 15){
            ErrorCode.setCode(23);
            ErrorCode.errorPopUp();
            return;
        }
        if(confirm.length() > 15){
            ErrorCode.setCode(23);
            ErrorCode.errorPopUp();
            return;
        }
        
        if (!password.equals(confirm)) {

            ErrorCode.setCode(2);
            ErrorCode.errorPopUp();
            
            System.out.println(ErrorCode.errorMessage());
            return;
        }

        if (NewUserRegSQLObject.userExists(username)) {

            ErrorCode.setCode(1);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
            return;
        }

        if(ErrorCode.getCurrentError() == 0 && (username != null && !username.equals("") && password != null && !password.equals(""))){

            CurrentUser.setUserInfo(username, password, -1, 0, 0);
            controller.setScreen(this.getProspective());
            clearFields();
        }
        else{
            ErrorCode.setCode(10);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());

        }
        
        
        System.out.println("Register clicked \t Username is: " + username 
               + " password is: " + password
               + " confirm is: " + confirm);
        
        /*Go to different screen here.*/

    }
    public void clearFields(){
        usernameField.setText("");
        passwordField.setText("");
        confirmField.setText("");
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
