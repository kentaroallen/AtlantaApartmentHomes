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
import java.util.Date;

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

    public static String[] chosenPerson;

    @FXML
    /**
     * This selects the highlighted row comma seperated value style and puts it
     * into the array chosenPerson[]
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
        
        /*This holds the saved value of the persons name. This is useful for SQL in comparison on screen change.*/
        AtlantaApartmentHomes.aptNameSql = chosenPerson[0];
        AllotmentController.applicantname.setText(AtlantaApartmentHomes.aptNameSql);
        /*As above ^*/
        System.out.println(AtlantaApartmentHomes.aptNameSql);
        
        
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

        try {
            populateTable(tablePopulator);
        } catch (Exception e) {

        }

        //tablePopulator.add(new Person("Kentaro Allen", "1/18/1993", "Male", "1200", "1BR-1B", "11/25/2014", "1 year", "Approved"));
        //tablePopulator.add(new Person("Homeless Joe", "2/24/1956", "Male", "40", "1BR-1B", "11/29/2014", "2 year", "Declined"));
        //tablePopulator.add(new Person("Bro Dude", "1/1/2316", "Male", "4000", "2BR-1B", "12/29/2314", "1 year", "Declined"));

        ObservableList<Person> obList = FXCollections.observableArrayList(tablePopulator);
        tablefield.setItems(obList);
        /*End of populating the table.*/

        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

    public static void populateTable(ArrayList<Person> table) throws Exception {

        for (String[] s : ApplicationReviewObject.getApplications()) {

            table.add( new Person(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7]) );
        }
    }

}
