/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Apartment;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class This controls the allotment screen after the
 * application review screen.
 *
 * @author Kentaro
 */
public class AllotmentController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;

    /**
     * Initializes the controller class.
     */
    @FXML
    public static TableView availtable;
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
    @FXML
    public static Label applicantname;

    public static String[] chosenPerson;

    @FXML

    /**
     * This retrieves the data values comma seperated value style of the
     * highlighted row. [0]=name,[1]=dob,[2]=gender,[3]=income,[4]=apt type,
     * [5]=pref move in date,[6]=lease time,[7]=approval
     *
     * seperatedData[] holds these values.
     *
     * SQL specific comments in implementation.
     *
     * @param e the click button event that caused this.
     */
    public void assignHandler(ActionEvent e) throws IOException {
        String rowValues = availtable.getSelectionModel().getSelectedItems().toString();
        /*Removes the [ ] around the string*/
        rowValues = rowValues.substring(1, rowValues.length() - 1);
        /*Comma seperated value retrieval*/
        String[] seperatedData = rowValues.split(",");

        /*If no selection is actually made then error otherwise continue*/
        if (seperatedData.length < 2) {
            ErrorCode.setCode(24);
            ErrorCode.errorPopUp();
            System.out.println(ErrorCode.errorMessage());
        } else {
            for (int i = 0; i < seperatedData.length; i++) {
                System.out.print(seperatedData[i] + " ");
            }
            System.out.println();
            controller.setScreen(this.getHomepage());
        }

    }

    public void cancelHandler(ActionEvent e) throws IOException {
        System.out.println("Cancel button clicked");
        controller.setScreen(this.getHomepage());

    }

    @Override
    /**
     * This is where sql data must get preloaded.
     */
    public void initialize(URL url, ResourceBundle rb) {
        aptnocol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("aptno"));
        catcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("category"));
        rentcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("monthlyRent"));
        sqftcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("sqft"));
        availcol.setCellValueFactory(new PropertyValueFactory<Apartment, String>("availableDate"));

        /*This populates the table.*/
        /*Name, DOB, Gender, Income, Apt Type, Pref Date, Lease Term, Approval*/
        ArrayList<Apartment> tablePopulator = new ArrayList<Apartment>();
        tablePopulator.add(new Apartment("1234", "2BR-2B", "1200", "1500", "12/01/2014"));
        tablePopulator.add(new Apartment("4321", "1BR-2B", "1005", "1000", "01/21/2014"));
        tablePopulator.add(new Apartment("2341", "2BR-1B", "1100", "1400", "11/18/2014"));
        ObservableList<Apartment> obList = FXCollections.observableArrayList(tablePopulator);
        availtable.setItems(obList);
        /*End of populating the table.*/

//        applicantname.setText(chosenPerson[0]); //ApplicationReviewController.chosenPerson[0]
        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
