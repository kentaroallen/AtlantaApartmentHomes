/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;

import AAH.model.ScreenNameContainer;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * This main class.
 *
 * @author Kentaro
 */
public class AtlantaApartmentHomes extends Application {

    private ScreenNameContainer snc = new ScreenNameContainer();
    public static MusicPlayerController elevator;

    /**
     * https://docs.google.com/document/d/1GHgOBdYnp0VR9j4evQbVS0C3GGKIxISOnbKHm7rs9O4/edit
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        ScreenController main = new ScreenController();
        initialize(main);
        main.setScreen(snc.getLogin());
        Group root = new Group();

        root.getChildren().add(main);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*Singleton init*/
        //elevator = MusicPlayerController.getInstance();

    }

    private void initialize(ScreenController main) {
        for (String s : snc.screenNames) {
            main.loadScreen(s, s + ".fxml");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
