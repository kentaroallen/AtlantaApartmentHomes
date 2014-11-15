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
public class Maintenance {

    SimpleStringProperty date;
    SimpleStringProperty apartment;
    SimpleStringProperty issue;
    SimpleStringProperty resolvedDate;

    public Maintenance(String n, String a, String i, String d) {
        this.date = new SimpleStringProperty(n);
        this.apartment = new SimpleStringProperty(a);
        this.issue = new SimpleStringProperty(i);
        this.resolvedDate = new SimpleStringProperty(d);
    }

    public void setDate(String d) {
        date.set(d);
    }

    public void setApartment(String a) {
        apartment.set(a);
    }

    public void setIssue(String i) {
        issue.set(i);
    }
    public void setResolvedDate(String d){
        resolvedDate.set(d);
    }

    public String getDate() {
        return date.get();
    }

    public String getApartment() {
        return apartment.get();
    }

    public String getIssue() {
        return issue.get();
    }
    public String getResolvedDate(){
        return resolvedDate.get();
    }

    /**
     * Super hack
     *
     * @return hack
     */
    @Override
    public String toString() {
        return getDate() + "," + getApartment() + "," + getIssue();
    }
}
