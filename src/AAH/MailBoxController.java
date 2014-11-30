/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Mail;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class MailBoxController implements Initializable {

    @FXML
    private TextArea messagearea;
    @FXML
    private TableColumn messcol;
    @FXML
    private TableColumn receivedcol;
    @FXML
    private TableView tablefieldmb;

    private boolean populateOnceMB = true;

    public void autoPopulateMailBox() {
        messcol.setCellValueFactory(new PropertyValueFactory<Mail, String>("number"));
        receivedcol.setCellValueFactory(new PropertyValueFactory<Mail, String>("date"));

        if (populateOnceMB) {
            ArrayList<Mail> tablePopulator = new ArrayList<Mail>();
            tablePopulator.add(new Mail("1", "1/1/2316"));
            tablePopulator.add(new Mail("2", "2/3/2116"));
            ObservableList<Mail> obList = FXCollections.observableArrayList(tablePopulator);
            tablefieldmb.setItems(obList);
            populateOnceMB = false;
        } else {
            System.out.println("preventing auto populate @ mailbox");
        }
    }

    public void readMessage() {
        String[] chosenMail;
        String rowValues = tablefieldmb.getSelectionModel().getSelectedItems().toString();//...
        rowValues = rowValues.substring(1, rowValues.length() - 1); /*Removes the [ ] around the string*/

        chosenMail = rowValues.split(","); /*Comma seperated value retrieval*/


        /*If the length of the selection was empty set error, otherwise continue*/
        if (chosenMail.length < 2) {
            ErrorCode.setCode(24);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        } else {
            for (int i = 0; i < chosenMail.length; i++) {
                System.out.print(chosenMail[i] + " ");
            }
            System.out.println();
            if(chosenMail[0].contains("1")){
                messagearea.setText("You are late on your bills \n\n -Management");
            }else{
                messagearea.setText("We fixed the problems in your apartment \n\n -Management");
            }
        }

        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
