/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * This is created for the javafx tableview so it can properly set and retrieve data.
 * @author Kentaro
 */
public class Mail {

     SimpleStringProperty number;
     SimpleStringProperty date;


    public Mail(String n, String d) {
        this.number = new SimpleStringProperty(n);
        this.date = new SimpleStringProperty(d);


    }

    public void setNumber(String n) {
        number.set(n);
    }

    public void setDate(String d) {
        date.set(d);
    }


    public String getNumber() {
        return number.get();
    }

    public String getDate() {
        return date.get();
    }



    /**
     * Super hack
     * @return hack
     */
    @Override
    public String toString(){
        return getNumber() + "," + getDate();
    }
}
