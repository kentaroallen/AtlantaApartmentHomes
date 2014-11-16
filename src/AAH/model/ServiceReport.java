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
public class ServiceReport {

    SimpleStringProperty month;
    SimpleStringProperty issue;
    SimpleStringProperty days;

    public ServiceReport(String n, String d, String g) {
        this.month = new SimpleStringProperty(n);
        this.issue = new SimpleStringProperty(d);
        this.days = new SimpleStringProperty(g);
    }

    public void setMonth(String n) {
        month.set(n);
    }

    public void setIssue(String d) {
        issue.set(d);
    }

    public void setDays(String g) {
        days.set(g);
    }

    public String getMonth() {
        return month.get();
    }

    public String getIssue() {
        return issue.get();
    }

    public String getDays() {
        return days.get();
    }

    /**
     * Super hack
     *
     * @return hack
     */
    @Override
    public String toString() {
        return getMonth() + "," + getIssue() + "," + getDays();
    }
}
