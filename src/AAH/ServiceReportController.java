/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.Report;
import AAH.model.ScreenTemplate;
import AAH.model.ServiceReport;
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
public class ServiceReportController extends ScreenTemplate implements Initializable, SetControlScreen {

    ScreenController controller;
    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox reportbox;
    @FXML
    private TableColumn monthcol;
    @FXML
    private TableColumn issuecol;
    @FXML
    private TableColumn dayscol;
    @FXML
    private TableView tablefield;

    private boolean populateOnceS = true;


    public void autoPopulateService() {
        if (populateOnceS) {
            System.out.println("auto populated service report");

            ArrayList<ServiceReport> tablePopulator = new ArrayList<ServiceReport>();
            ArrayList<ArrayList<String[]>> out = ServiceReportSQLObject.serviceRequestReport();

            for (int month = 0; month < 3; month++)
            {
                String strMonth = "";
                switch (month)
                {
                    case 0: strMonth = "August";
                        break;
                    case 1: strMonth = "September";
                        break;
                    case 2: strMonth = "October";
                        break;
                    default: strMonth = "Invalid Month";
                        break;
                }
                for (int issue = 0; issue < out.get(month).size(); issue++)
                {
                    if (issue == 0)
                        tablePopulator.add(new ServiceReport(strMonth, ((out.get(month)).get(issue))[0], ((out.get(month)).get(issue))[1]));
                    else
                        tablePopulator.add(new ServiceReport("", ((out.get(month)).get(issue))[0], ((out.get(month)).get(issue))[1]));
                }
            }

//            tablePopulator.add(new ServiceReport("August", "Garbage Disposal", "20"));
//            tablePopulator.add(new ServiceReport("", "Plumbing", "10"));
//            tablePopulator.add(new ServiceReport("", "BS", "5"));
//
//            tablePopulator.add(new ServiceReport("September", "Garbage Disposal", "20"));
//            tablePopulator.add(new ServiceReport("", "Plumbing", "10"));
//            tablePopulator.add(new ServiceReport("", "BS", "5"));
//
//            tablePopulator.add(new ServiceReport("October", "Garbage Disposal", "20"));
//            tablePopulator.add(new ServiceReport("", "Plumbing", "10"));
//            tablePopulator.add(new ServiceReport("", "BS", "5"));

            ObservableList<ServiceReport> obList = FXCollections.observableArrayList(tablePopulator);
            tablefield.setItems(obList);

            populateOnceS = false;
        } else {
            System.out.println("Prevented auto populate @ service report");
        }
    }

    @Override
    /**
     * Placeholder method for correct operation.
     */
    public void initialize(URL url, ResourceBundle rb) {

        monthcol.setCellValueFactory(new PropertyValueFactory<ServiceReport, String>("month"));
        issuecol.setCellValueFactory(new PropertyValueFactory<ServiceReport, String>("issue"));
        dayscol.setCellValueFactory(new PropertyValueFactory<ServiceReport, String>("days"));


        /*This populates the table.*/
        /*Month, Category, Apartment*/
        /*This is the idea to over come the lazy mode listed in the comments below*/
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

        /*Lazy mode. Month need only show once every 3 entry.*/
        //this.setTitleLabel(this.getHomepage());
    }

    @Override
    public void setScreenParent(ScreenController screen) {
        controller = screen;
    }

}
