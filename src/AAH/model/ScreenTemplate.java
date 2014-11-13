/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH.model;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Kentaro
 */
public class ScreenTemplate extends ScreenNameContainer implements LabelTemplate{
     @FXML
     protected Label titleLabel;

     @Override
     public void setTitleLabel(String screenName)
     {
          
     }
}
