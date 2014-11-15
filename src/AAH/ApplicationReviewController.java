/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Apartment;
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
 * 
 * We need to create a view from this point with a joined name. I spent a few hours trying other methods but the architecture
 * wont allow it. Please do the create view with a joined name.
 * 
 * 
 * 
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

    
        @FXML
    private TableView availtable;
    @FXML
    private TableColumn aptnocol;
    @FXML
    private TableColumn catcol;
    @FXML
    private TableColumn rentcol;
    @FXML
    private TableColumn sqftcol;
    @FXML
    private TableColumn availcol;

    public static String[] chosenPerson;

    @FXML
    /**
     * This should save what information you clicked for the next screen.
     * chosenPerson contains the data from the selectedrow
     *
     * @param e the click button event that caused this.
     */
    public void nextHandler(ActionEvent e) throws IOException {
        String rowValues = tablefield.getSelectionModel().getSelectedItems().toString();
        rowValues = rowValues.substring(1, rowValues.length() - 1); /*Removes the [ ] around the string*/

        chosenPerson = rowValues.split(","); /*Comma seperated value retrieval*/
        /*[0]=name,[1]=dob,[2]=gender,[3]=income,[4]=apt type,
         [5]=pref move in date,[6]=lease time,[7]=approval*/

        for (int i = 0; i < chosenPerson.length; i++) {
            System.out.print(chosenPerson[i] + " ");

        }
        System.out.println();

         controller.setScreen(this.getAllotment());
    }
    public void assignHandler(ActionEvent e) throws IOException {
        
        System.out.println("it hit");
    }



    @Override
    /**
     * This should populate the table with each application needed to review.
     */
    public void initialize(URL url, ResourceBundle rb) {

//
//                aptnocol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("aptno"));
//        catcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("category"));
//        rentcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("monthlyRent"));
//        sqftcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("sqft"));
//        availcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("availableDate"));
//
//        /*This populates the table.*/
//        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
//        ArrayList<Apartment> tablePopulatora = new ArrayList<Apartment>();
//        tablePopulatora.add(new Apartment("1234", "2BR-2B", "1200", "1500", "12/01/2014"));
//        tablePopulatora.add(new Apartment("4321", "1BR-2B", "1005", "1000", "01/21/2014"));
//        tablePopulatora.add(new Apartment("2341", "2BR-1B", "1100", "1400", "11/18/2014"));
//        ObservableList<Apartment> obLista = FXCollections.observableArrayList(tablePopulatora);
//        availtable.setItems(obLista);
//        /*End of populating the table.*/
//        
        
        
        namecol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        dobcol.setCellValueFactory(new PropertyValueFactory<Person, String>("dob"));
        gendercol.setCellValueFactory(new PropertyValueFactory<Person, String>("gender"));
        incomecol.setCellValueFactory(new PropertyValueFactory<Person, String>("income"));
        aptcol.setCellValueFactory(new PropertyValueFactory<Person, String>("typeApt"));
        datecol.setCellValueFactory(new PropertyValueFactory<Person, String>("prefdate"));
        leasecol.setCellValueFactory(new PropertyValueFactory<Person, String>("leaseterm"));
        approvalcol.setCellValueFactory(new PropertyValueFactory<Person, String>("approval"));

        /*This populates the table.*/
        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        ArrayList<Person> tablePopulator = new ArrayList<Person>();
        tablePopulator.add(new Person("Kentaro Allen", "1/18/1993", "Male", "1200", "1BR-1B", "11/25/2014", "1 year", "Approved"));
        tablePopulator.add(new Person("Homeless Joe", "2/24/1956", "Male", "40", "1BR-1B", "11/29/2014", "2 year", "Declined"));
        tablePopulator.add(new Person("Bro Dude", "1/1/2316", "Male", "4000", "2BR-1B", "12/29/2314", "1 year", "Declined"));
        ObservableList<Person> obList = FXCollections.observableArrayList(tablePopulator);
        tablefield.setItems(obList);
        /*End of populating the table.*/

        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
