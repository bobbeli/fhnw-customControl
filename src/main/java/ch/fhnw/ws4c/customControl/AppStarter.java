package ch.fhnw.ws4c.customControl;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Created by dimitri on 19.04.2016.jhehej
 */
public class AppStarter extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootPanel = new BorderPaneUI();

        Scene scene = new Scene(rootPanel);

        String stylesheet = getClass().getResource("styles.css").toExternalForm();
        scene.getStylesheets().add(stylesheet);

        primaryStage.setTitle("SBB Custom Control");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
