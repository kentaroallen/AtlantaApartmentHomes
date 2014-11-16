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
public class Defaulters {

    SimpleStringProperty apartment;
    SimpleStringProperty extramoney;
    SimpleStringProperty defaultby;

    public Defaulters(String n, String d, String g) {
        this.apartment = new SimpleStringProperty(n);
        this.extramoney = new SimpleStringProperty(d);
        this.defaultby = new SimpleStringProperty(g);
    }

    public void setApartment(String n) {
        apartment.set(n);
    }

    public void setExtramoney(String d) {
        extramoney.set(d);
    }

    public void setDefaultby(String g) {
        defaultby.set(g);
    }

    public String getApartment() {
        return apartment.get();
    }

    public String getExtramoney() {
        return extramoney.get();
    }

    public String getDefaultby() {
        return defaultby.get();
    }

    /**
     * Super hack
     *
     * @return hack
     */
    @Override
    public String toString() {
        return getApartment() + "," + getExtramoney() + "," + getDefaultby();
    }
}
