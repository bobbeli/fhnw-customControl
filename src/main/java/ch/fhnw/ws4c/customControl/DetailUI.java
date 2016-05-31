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
public class DetailUI extends BorderPane {
    private Region trainIcon;
    private Region trainRail;
    private Text trainSp;
    private Region trainType;
    private HBox hBox;
    private PresentationModel model;

    public DetailUI(PresentationModel _model){
        model = _model;
        initializeControls();
        layoutControls();

    }

    private void initializeControls() {
        // Train Rail Number
        trainRail = new Region();
        trainRail.getStyleClass().addAll("detailUI","trainRail");
        trainRail.setMinWidth(88);
        trainRail.setMinHeight(86);
        trainRail.setMaxWidth(88);
        trainRail.setMaxHeight(86);

        // Train Icon
        trainIcon = new Region();
        trainIcon.getStyleClass().addAll("detailUI", "trainIcon");
        trainIcon.setMaxWidth(44);
        trainIcon.setMaxHeight(43);

        // Train Type
        trainType = new Region();
        trainType.getStyleClass().addAll("detailUI", "trainType");
        trainType.setMinWidth(110);
        trainType.setMinHeight(43);
        trainType.setMaxHeight(44);

        // Train Specification
        trainSp = new Text();
        trainSp.setText( Integer.toString(model.getTrainNumber()));
        trainSp.getStyleClass().add("trainSp");
        trainSp.setTextAlignment(TextAlignment.JUSTIFY);
        trainSp.setFont(Font.font("Verdana", FontWeight.NORMAL,22));
    }

    private void layoutControls() {

        this.setPrefWidth(300);
        this.setMinWidth(200);
        this.setMaxWidth(800);

        this.backgroundProperty().setValue(new Background(new BackgroundFill(Paint.valueOf("White"), null, null)));

        hBox = new HBox();

        hBox.getChildren().addAll(trainIcon, trainType, trainSp);
        hBox.setPadding(new Insets(80,0,0,0));
        hBox.setSpacing(18);

        this.setTop(trainRail);
        this.setCenter(hBox);

        // positions of the Train Rail Icon
        this.setAlignment(trainRail, Pos.TOP_RIGHT);
        this.setMargin(trainRail, new Insets(10,10,0,0));




    }
}
