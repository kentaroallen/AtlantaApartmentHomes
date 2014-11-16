/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Defaulters;
import AAH.model.ScreenTemplate;
import AAH.model.SetControlScreen;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class DefaulterReportController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */

    @FXML
    private TableColumn aptcol;
    @FXML
    private TableColumn paidcol;
    @FXML
    private TableColumn defaultcol;
    @FXML
    private TableView tablefield;
    @FXML
    private ComboBox monthchose;

    public void closeHandler(ActionEvent e) throws IOException {
        System.out.println("close clicked");
        controller.setScreen(this.getHomepage());
    }

    public void monthHandler(ActionEvent e) throws IOException {
        System.out.println("month chosen");
        String chosenMonth = monthchose.getValue().toString().toLowerCase();
        System.out.println(chosenMonth);

    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};
        ArrayList<String> posGender = new ArrayList<String>(Arrays.asList(monthNames));
        ObservableList<String> obListmo = FXCollections.observableArrayList(posGender);

        monthchose.setItems(obListmo);
        
        
        
        aptcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("apartment"));
        paidcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("extramoney"));
        defaultcol.setCellValueFactory(new PropertyValueFactory<Defaulters, String>("defaultby"));
        
        /*This populates the table.*/
        /*Month, Category, Apartment*/
        /*Lazy mode. Month need only show once every 3 entry.
         Algorithm fix in other comments in service report*/
        ArrayList<Defaulters> tablePopulator = new ArrayList<Defaulters>();
        tablePopulator.add(new Defaulters("1234","100","20"));
        tablePopulator.add(new Defaulters("3243","200","10"));
        tablePopulator.add(new Defaulters("5452","300","5"));

        tablePopulator.add(new Defaulters("1234","100","20"));
        tablePopulator.add(new Defaulters("3243","200","10"));
        tablePopulator.add(new Defaulters("5452","300","5"));
        
        tablePopulator.add(new Defaulters("1234","100","20"));
        tablePopulator.add(new Defaulters("3243","200","10"));
        tablePopulator.add(new Defaulters("5452","300","5"));
        ObservableList<Defaulters> obList = FXCollections.observableArrayList(tablePopulator);
        tablefield.setItems(obList);

        this.setTitleLabel(this.getHomepage());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
