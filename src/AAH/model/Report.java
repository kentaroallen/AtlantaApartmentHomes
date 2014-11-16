/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * This is created for the javafx tableview so it can properly set and retrieve
 * data.
 *
 * @author Kentaro
 */
public class Report {

    SimpleStringProperty month;
    SimpleStringProperty category;
    SimpleStringProperty apts;

    public Report(String n, String d, String g) {
        this.month = new SimpleStringProperty(n);
        this.category = new SimpleStringProperty(d);
        this.apts = new SimpleStringProperty(g);
    }

    public void setMonth(String n) {
        month.set(n);
    }

    public void setCategory(String d) {
        category.set(d);
    }

    public void setGender(String g) {
        apts.set(g);
    }

    public String getMonth() {
        return month.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getApts() {
        return apts.get();
    }

    /**
     * Super hack
     *
     * @return hack
     */
    @Override
    public String toString() {
        return getMonth() + "," + getCategory() + "," + getApts();
    }
}
