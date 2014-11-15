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
public class Person {

     SimpleStringProperty name;
     SimpleStringProperty dob;
     SimpleStringProperty gender;
     SimpleStringProperty income;
     SimpleStringProperty typeApt;
     SimpleStringProperty prefdate;
     SimpleStringProperty leaseterm;
     SimpleStringProperty approval;

    public Person(String name, String dob, String gender, String income, String typeApt, String prefdate, String leaseterm, String approval) {
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.income = new SimpleStringProperty(income);
        this.typeApt = new SimpleStringProperty(typeApt);
        this.prefdate = new SimpleStringProperty(prefdate);
        this.leaseterm = new SimpleStringProperty(leaseterm);
        this.approval = new SimpleStringProperty(approval);

    }

    public void setName(String namea) {
        name.set(namea);
    }

    public void setDob(String doba) {
        dob.set(doba);
    }

    public void setGender(String gendera) {
        gender.set(gendera);
    }

    public void setIncome(String incomea) {
        income.set(incomea);
    }

    public void setTypeApt(String typeApta) {
        typeApt.set(typeApta);
    }

    public void setPrefdate(String prefdatea) {
        prefdate.set(prefdatea);
    }

    public void setLeaseterm(String leaseterma) {
        leaseterm.set(leaseterma);
    }

    public void setApproval(String approvala) {
        approval.set(approvala);
    }

    public String getName() {
        return name.get();
    }

    public String getDob() {
        return dob.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getIncome() {
        return income.get();
    }

    public String getTypeApt() {
        return typeApt.get();
    }

    public String getPrefdate() {
        return prefdate.get();
    }

    public String getLeaseterm() {
        return leaseterm.get();
    }

    public String getApproval() {
        return approval.get();
    }
    /**
     * Super hack
     * @return hack
     */
    @Override
    public String toString(){
        return getName() + "," + getDob() + "," + getGender() + "," + getIncome() + "," + getTypeApt() + "," + getPrefdate() + "," + getLeaseterm() + "," + getApproval();
    }
}
