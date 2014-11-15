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
public class Apartment {

     SimpleStringProperty aptno;
     SimpleStringProperty category;
     SimpleStringProperty monthlyRent;
     SimpleStringProperty sqft;
     SimpleStringProperty availableDate;


    public Apartment(String num, String catt, String rent, String area, String date) {
        this.aptno = new SimpleStringProperty(num);
        this.category = new SimpleStringProperty(catt);
        this.monthlyRent = new SimpleStringProperty(rent);
        this.sqft = new SimpleStringProperty(area);
        this.availableDate = new SimpleStringProperty(date);


    }

    public void setApt(String num) {
        aptno.set(num);
    }

    public void setCategory(String doba) {
        category.set(doba);
    }

    public void setRent(String gendera) {
        monthlyRent.set(gendera);
    }

    public void setSqft(String incomea) {
        sqft.set(incomea);
    }

    public void setAvailability(String typeApta) {
        availableDate.set(typeApta);
    }


    public String getAptno() {
        return aptno.get();
    }

    public String getCategory() {
        return category.get();
    }

    public String getMonthlyRent() {
        return monthlyRent.get();
    }

    public String getSqft() {
        return sqft.get();
    }

    public String getAvailableDate() {
        return availableDate.get();
    }
    /**
     * Super hack
     * @return hack
     */
    @Override
    public String toString(){
        return getAptno() + "," + getCategory() + "," + getMonthlyRent() + "," + getSqft() + "," + getAvailableDate();
    }
}
