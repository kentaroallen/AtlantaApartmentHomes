/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH.model;

import AAH.*;

/**
 *
 * @author Kentaro
 */
public class ScreenNameContainer {

    public final String screenNames[] = {
        "Login", //0
        "NewUserReg", //1
        "ProspectiveResident", //2
        "Homepage",
        "PayRent",
        "RequestMaintenance",
        "PaymentInformation",
        "ApplicationReview",
        "Allotment",
        "ViewRequests",
        "Reminder"
    };

    public String[] getAll() {
        return this.screenNames;
    }

    public String getLogin() {
        return screenNames[0];
    }

    public String getNewUserReg() {
        return screenNames[1];
    }

    public String getProspective() {
        return screenNames[2];
    }

    public String getHomepage() {
        return screenNames[3];
    }

    public String getPayRent() {
        return screenNames[4];
    }

    public String getRequestMaintenance() {
        return screenNames[5];
    }

    public String getPaymentInformation() {
        return screenNames[6];
    }

    public String getApplicationReview() {
        return screenNames[7];
    }

    public String getAllotment() {
        return screenNames[8];
    }

    public String getViewRequests() {
        return screenNames[9];
    }
    public String getReminder(){
        return screenNames[10];
    }
}
