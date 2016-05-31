package eu.hansolo.fx.customcontrols.ledcss;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Demo extends Application {

    @Override public void start(Stage stage) {
        Led control = new Led();
        control.setPrefSize(200, 200);

        StackPane pane = new StackPane();
        pane.getChildren().setAll(control);

        Scene scene = new Scene(pane);

        stage.setTitle("JavaFX Led CSS");
        stage.setScene(scene);
        stage.show();

        control.setBlinking(true);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


