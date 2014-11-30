/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Person;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class Everything about the application review is a straight
 * hack.
 *
 * @author Kentaro
 */
public class ApplicationReviewController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tablefield;
    @FXML
    private TableColumn namecol;
    @FXML
    private TableColumn dobcol;
    @FXML
    private TableColumn gendercol;
    @FXML
    private TableColumn incomecol;
    @FXML
    private TableColumn aptcol;
    @FXML
    private TableColumn datecol;
    @FXML
    private TableColumn leasecol;
    @FXML
    private TableColumn approvalcol;

    private boolean populateOnceApp = true;

    @FXML
    /**
     * This selects the highlighted row comma seperated value style and puts it
     * into the array chosenPerson[] The following defines chosenPerson
     * [0]=name,[1]=dob,[2]=gender,[3]=income,[4]=apt type, [5]=pref move in
     * date,[6]=lease time,[7]=approval
     *
     * @param e the click button event that caused this.
     */
    public void nextHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        String[] chosenPerson;
        String rowValues = tablefield.getSelectionModel().getSelectedItems().toString();
        rowValues = rowValues.substring(1, rowValues.length() - 1); /*Removes the [ ] around the string*/

        chosenPerson = rowValues.split(","); /*Comma seperated value retrieval*/

        if (ApplicationReviewSQLObject.applicantAllotedAlready(chosenPerson[0]) || ErrorCode.getCurrentError() != 0) {

            return;
        }

        ApartmentAllotmentChoice.setApplicant(chosenPerson[0], Integer.parseInt(chosenPerson[3]), "2B");
        /*If the length of the selection was empty set error, otherwise continue*/
        if (chosenPerson.length < 2) {
            ErrorCode.setCode(24);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        } else {
            for (int i = 0; i < chosenPerson.length; i++) {
                System.out.print(chosenPerson[i] + " ");

            }
            System.out.println();

            controller.setScreen(this.getAllotment());
        }

    }
    /**
     * Auto population happens here
     * @throws Exception 
     */
    public void autoPopulateApp() throws Exception {
        if (populateOnceApp) {
            System.out.println("Auto populated application review");
            
            ArrayList<Person> tablePopulator = new ArrayList<Person>();
            for (String[] s : ApplicationReviewSQLObject.getApplications()) {

                tablePopulator.add(new Person(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]));
            }
            ObservableList<Person> obList = FXCollections.observableArrayList(tablePopulator);
            tablefield.setItems(obList);
            
            populateOnceApp = false;
        } else {
            System.out.println("Prevented auto populate @ application review");
        }
    }

    public void exitHandler(ActionEvent e) throws IOException {

        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("Exited application review screen");
        controller.setScreen(this.getHomepage());
        populateOnceApp = true;
    }

    @Override
    /**
     * This should populate the table with each application needed to review.
     */
    public void initialize(URL url, ResourceBundle rb) {
        namecol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        dobcol.setCellValueFactory(new PropertyValueFactory<Person, String>("dob"));
        gendercol.setCellValueFactory(new PropertyValueFactory<Person, String>("gender"));
        incomecol.setCellValueFactory(new PropertyValueFactory<Person, String>("income"));
        aptcol.setCellValueFactory(new PropertyValueFactory<Person, String>("typeApt"));
        datecol.setCellValueFactory(new PropertyValueFactory<Person, String>("prefdate"));
        leasecol.setCellValueFactory(new PropertyValueFactory<Person, String>("leaseterm"));
        approvalcol.setCellValueFactory(new PropertyValueFactory<Person, String>("approval"));

        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        //tablePopulator.add(new Person("Kentaro Allen", "1/18/1993", "Male", "1200", "1BR-1B", "11/25/2014", "1 year", "Approved"));
        //tablePopulator.add(new Person("Homeless Joe", "2/24/1956", "Male", "40", "1BR-1B", "11/29/2014", "2 year", "Declined"));
        //tablePopulator.add(new Person("Bro Dude", "1/1/2316", "Male", "4000", "2BR-1B", "12/29/2314", "1 year", "Declined"));
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
