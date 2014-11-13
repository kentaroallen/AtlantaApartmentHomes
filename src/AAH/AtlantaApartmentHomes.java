/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AAH;
import AAH.model.ScreenNameContainer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Kentaro
 */
public class AtlantaApartmentHomes extends Application {

    private ScreenNameContainer snc = new ScreenNameContainer();
    /**
     * 
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
