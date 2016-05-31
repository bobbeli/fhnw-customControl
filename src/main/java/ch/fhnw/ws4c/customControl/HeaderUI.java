package ch.fhnw.ws4c.customControl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;

/**
 * Created by dimitri on 21.05.2016.
 */
public class HeaderUI extends VBox {
    private Region trainRail;
    private Integer prefWidth = 500;

    private PresentationModel model;

    public HeaderUI(PresentationModel _model){
        model = _model;
        initializeControls();
        layoutControls();

    }

    private void initializeControls() {
        // Train Rail Number
        trainRail = new Region();
        // Adds all CSS Classes to the Region
        trainRail.getStyleClass().addAll("detailUI","trainRail");
        trainRail.setMinWidth(88);
        trainRail.setMinHeight(86);
        trainRail.setMaxWidth(88);
        trainRail.setMaxHeight(86);

        // ToDO Text Element für variable Gleis Number.

        // ToDO Text Center (AnkunfstBahnhof)

        // ToDO PLatzhalter 200x200 px left side

    }

    private void layoutControls() {

        this.setPrefWidth(prefWidth);
        this.setMinWidth(prefWidth);
        this.setMaxWidth(prefWidth+10);

        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(18);


        // positions of the Train Rail Icon
        this.setMargin(trainRail, new Insets(10,10,0,0));

       this.getChildren().addAll(trainRail);

    }
}
