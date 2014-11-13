/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.*;
import AAH.model.SetControlScreen;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Kentaro
 */
public class ScreenController extends StackPane {

    private final HashMap<String, Node> theScreens = new HashMap<>();

    public ScreenController() {
        super();
    }

    public boolean loadScreen(String screenName, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/" + path ));         //load fxml
            Parent loadScreen = (Parent) loader.load();                //load scene
            SetControlScreen myScreenController = (SetControlScreen) loader.getController();       //return screen controller
            myScreenController.setScreenParent(this);
            theScreens.put(screenName, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(String screenName) {
        if (theScreens.get(screenName) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0); //remove the current displayed screen
                getChildren().add(0, theScreens.get(screenName)); //replace with the new screen
            } else {
                getChildren().add(theScreens.get(screenName)); //no screen has been displayed
            }
            return true;
        } else {
            System.out.println("Screen hasn't been loaded!\n");
            return false;
        }
    }
}
