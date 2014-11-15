/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Maintenance;
import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
 * FXML Controller class Everything about the application review is a straight
 * hack.
 *
 *
 *
 *
 *
 *
 *
 * @author Kentaro
 */
public class ViewRequestsController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView availtable;
    @FXML
    private TableColumn datereqcol;
    @FXML
    private TableColumn aptnocol;
    @FXML
    private TableColumn issuecol;

    @FXML
    private TableView resolvedtable;
    @FXML
    private TableColumn datereqrescol;
    @FXML
    private TableColumn aptnorescol;
    @FXML
    private TableColumn issuerescol;
    @FXML
    private TableColumn daterescol;
    @FXML
    private Label currentdate;

    ArrayList<Maintenance> tablePopulatoravail = new ArrayList<Maintenance>();
    ArrayList<Maintenance> tablePopulatorres = new ArrayList<Maintenance>();
    Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    int currentDay = localCalendar.get(Calendar.DATE);
    int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
    int currentYear = localCalendar.get(Calendar.YEAR);
    String date = currentMonth + "/" + currentDay + "/" + currentYear;

    /**
     * This takes from the available table and puts things into the resolved
     * table Chosenperson[] holds the values from the avail table.
     *
     * @param e the click button event that caused this.
     */
    public void resolvedHandler(ActionEvent e) throws IOException {
        String[] chosenPerson;
        String[] resolvedPerson = new String[4];
        int removeIndex;

        System.out.println("Resolved handler clicked");
        String rowValues = availtable.getSelectionModel().getSelectedItems().toString();
        rowValues = rowValues.substring(1, rowValues.length() - 1); /*Removes the [ ] around the string*/

        chosenPerson = rowValues.split(","); /*Comma seperated value retrieval*/
        /*[0] = date of request, [1] = apt no, [2] = issue*/

        for (int i = 0; i < chosenPerson.length; i++) {
            System.out.print(chosenPerson[i] + " ");

        }
        removeIndex = availtable.getSelectionModel().getSelectedIndex();
        tablePopulatoravail.remove(removeIndex);
        ObservableList<Maintenance> obListavail = FXCollections.observableArrayList(tablePopulatoravail);
        availtable.setItems(obListavail);
        System.out.println();

        /*This populates the resolved table*/
        tablePopulatorres.add(new Maintenance(chosenPerson[0], chosenPerson[1], chosenPerson[2], date));
        ObservableList<Maintenance> obListres = FXCollections.observableArrayList(tablePopulatorres);
        resolvedtable.setItems(obListres);
        resolvedPerson[0] = chosenPerson[0];
        resolvedPerson[1] = chosenPerson[1];
        resolvedPerson[2] = chosenPerson[2];
        resolvedPerson[3] = date;
        for (int i = 0; i < resolvedPerson.length; i++) {
            System.out.print(resolvedPerson[i] + " ");
        }
        System.out.println();

    }

    public void homeHandler(ActionEvent e) throws IOException {
        controller.setScreen(this.getHomepage());
        System.out.println("Home button clicked.");
    }

    @Override
    /**
     * This should populate the table with each application needed to review.
     */
    public void initialize(URL url, ResourceBundle rb) {
        datereqcol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("date"));
        aptnocol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("apartment"));
        issuecol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("issue"));

        /*This populates the table.*/
        /*Date, Apartment Num, Issue*/
        tablePopulatoravail.add(new Maintenance("01/01/1900", "1321", "Just come now", ""));
        tablePopulatoravail.add(new Maintenance("10/21/2000", "1511", "Broken Appliances", ""));
        tablePopulatoravail.add(new Maintenance("05/30/2014", "1634", "Leak", ""));
        ObservableList<Maintenance> obListavail = FXCollections.observableArrayList(tablePopulatoravail);
        availtable.setItems(obListavail);
        /*End of populating the table.*/

        datereqrescol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("date"));
        aptnorescol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("apartment"));
        issuerescol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("issue"));
        daterescol.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("resolvedDate"));

        /*This populates the table.*/
        /*Date, Apartment Num, Issue*/
        tablePopulatorres.add(new Maintenance("01/01/1900", "1321", "Just come now", "01/02/1900"));
        tablePopulatorres.add(new Maintenance("10/21/2000", "1511", "Broken Appliances", "12/22/2000"));
        ObservableList<Maintenance> obListres = FXCollections.observableArrayList(tablePopulatorres);
        resolvedtable.setItems(obListres);
        /*End of populating the table.*/

        currentdate.setText(date);
        this.setTitleLabel(this.getLogin());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
