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
import org.omg.CORBA.Current;

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
        System.out.println("New user clicked");
        controller.setScreen(this.getNewUserReg());
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

        /*SQL logic here*/
        LoginSQLObject.validateLogin(username, password);
        LoginSQLObject.setCurrentUser(username);


        if ((CurrentUser.getUserType() == 0) && !LoginSQLObject.filledOutApplication(username)) { // if our user is prospective and hasn't completed their prospective resident application, we will make them finish it before loggin in to the good stuff

                controller.setScreen(getProspective());
                return;
        }

        if (ErrorCode.currentError == 0) {

            System.out.println(CurrentUser.getUserType());
            controller.setScreen(this.getHomepage());
            return;
        }
        else {

            return;
        }

        /*Go to different screen here.*/

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
