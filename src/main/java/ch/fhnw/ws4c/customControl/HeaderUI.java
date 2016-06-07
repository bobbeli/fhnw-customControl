package ch.fhnw.ws4c.customControl;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.layout.HBox;

/**
 * Created by dimitri on 21.05.2016.
 */
public class HeaderUI extends HBox {
    private Integer prefWidth = 500;

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
        this.setMinWidth(prefWidth);
        this.setMaxWidth(prefWidth+10);

        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(18);

        this.getChildren().addAll(clock, directionUI, railShield);
    }
}
