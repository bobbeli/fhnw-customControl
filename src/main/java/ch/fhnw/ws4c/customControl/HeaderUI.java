package ch.fhnw.ws4c.customControl;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.layout.HBox;

/**
 * Created by dimitri on 21.05.2016.
 */
public class HeaderUI extends BorderPane {
    private Integer prefWidth = 434;
    private Integer maxWidth = 700;
    private Integer minWidth = 434;

    private PresentationModel model;
    private ClockUI clock;
    private DirectionUI directionUI;
    private TrainRail railShield;

    public HeaderUI(PresentationModel _model){
        model = _model;
        initializeControls();
        layoutControls();
    }

    private void initializeControls() {
        clock = new ClockUI();
        railShield = new TrainRail();
        directionUI = new DirectionUI();
    }

    private void layoutControls() {
        this.setPrefWidth(prefWidth);
        this.setMinWidth(minWidth);
        this.setMaxWidth(maxWidth);

        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        this.setPadding(new Insets(10,10,10,10));
        this.setMargin(directionUI, new Insets(0,0,0,10));


        this.setLeft(clock);
        this.setCenter(directionUI);
        this.setRight(railShield);

        //this.getChildren().addAll(clock, directionUI, railShield);
    }
}
