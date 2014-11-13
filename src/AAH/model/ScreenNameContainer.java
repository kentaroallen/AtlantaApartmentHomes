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
}
