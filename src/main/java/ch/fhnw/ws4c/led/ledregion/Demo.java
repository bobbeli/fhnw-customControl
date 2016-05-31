package eu.hansolo.fx.customcontrols.ledregion;


import javafx.application.Application;
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

        Scene scene = new Scene(pane, 100, 100, Color.DARKGRAY);

        stage.setTitle("JavaFX Led Region");
        stage.setScene(scene);
        stage.show();

        control.setBlinking(true);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}


