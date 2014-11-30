/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Report;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kentaro
 */
public class ThreeMonthReportController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox reportbox;
    @FXML
    private TableColumn monthcol;
    @FXML
    private TableColumn categorycol;
    @FXML
    private TableColumn aptscol;
    @FXML
    private TableView tablefield;

    private boolean populateOnceT = true;

    public void closeHandler(ActionEvent e) throws IOException {
        /////////////////////
        ErrorCode.setCode(0);
        ////////////////////

        System.out.println("close clicked");
        controller.setScreen(this.getHomepage());
    }

    /**
     * Auto populate this 3 month report.
     * Month --> Category --> Available
     */
    public void autoPopulateThree() {

        if (populateOnceT) {
            System.out.println("Auto populated @ Three month report");

            ArrayList<Report> tablePopulator = new ArrayList<Report>();
            tablePopulator.add(new Report("August", "1BR-1B", "20"));
            tablePopulator.add(new Report("", "2BR-1B", "10"));
            tablePopulator.add(new Report("", "2BR-2B", "5"));

            tablePopulator.add(new Report("September", "1BR-1B", "20"));
            tablePopulator.add(new Report("", "2BR-1B", "10"));
            tablePopulator.add(new Report("", "2BR-2B", "5"));

            tablePopulator.add(new Report("October", "1BR-1B", "20"));
            tablePopulator.add(new Report("", "2BR-1B", "10"));
            tablePopulator.add(new Report("", "2BR-2B", "5"));

            ObservableList<Report> obList = FXCollections.observableArrayList(tablePopulator);
            tablefield.setItems(obList);
            
            populateOnceT = false;
        } else {
            System.out.println("Prevented auto populate @ Three month report");
        }

    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {
        monthcol.setCellValueFactory(new PropertyValueFactory<AAH.model.Person, String>("month"));
        categorycol.setCellValueFactory(new PropertyValueFactory<AAH.model.Person, String>("category"));
        aptscol.setCellValueFactory(new PropertyValueFactory<AAH.model.Person, String>("apts"));
        /*Month, Category, Apartment*/

//        int start = 0;
//        String month;
//        for (int i = 0; i < sql.tuples; i++) {
//            if (start == 0) {
//                start = 1;
//                month = sql.month.retrieval;
//                tablePopulator.add(new Report(mos, sql.retrieve.type, sql.retrieve.aptsleft));
//            } else if (start == 1 && month != sql.month.retrieval) {
//                month = sql.month.retrieval;
//                tablePopulator.add(new Report(month, sql.retrieve.type, sql.retrieve.aptsleft));
//            } else { //if the same month name as before dont insert into table
//                tablePopulator.add(new Report("", sql.retrieve.typ, sql.retrieve.aptsleft))
//            }
//        }
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
