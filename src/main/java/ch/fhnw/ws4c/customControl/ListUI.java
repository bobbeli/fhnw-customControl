package ch.fhnw.ws4c.customControl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Created by dimitri on 21.05.2016.
 */
public class ListUI extends VBox {
    private Button button;
    private ListView<Station> listView;
    private final PresentationModel model;
    private Background background;
    private HBox hBox;
    private Region trainIcon;
    private Text trainSp;
    private Region trainType;

    public ListUI(PresentationModel _model) {
        this.model = _model;
        initializeControls();
        layoutControls();
        addEventHandlers();
        addValueChangedListeners();
        addBindings();
    }


    private void initializeControls() {
        button = new Button("Add New Element");
        background = new Background(new BackgroundFill(Paint.valueOf("White"), null, null));
        listView = new ListView<>(model.getAllItems());
        listView.setCellFactory(param -> new StationCell());

        listView.backgroundProperty().setValue(background);
    }

    private void layoutControls() {
        //setPadding(new Insets(10));
        //setSpacing(10);
        this.setPadding(new Insets(0,0,0,0));

        button.setMaxWidth(Double.MAX_VALUE);
        setVgrow(listView, Priority.ALWAYS);

        // New shizzel
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


        /*hBox = new HBox();

        hBox.getChildren().addAll(trainIcon, trainType, trainSp);
        hBox.setPadding(new Insets(80,0,0,0));
        hBox.setSpacing(18);
        this.setCenter(hBox);

        // positions of the Train Rail Icon
        this.setAlignment(trainRail, Pos.TOP_RIGHT);
        this.setMargin(trainRail, new Insets(10,10,0,0));

        */
        getChildren().addAll(button, listView);
    }

    private void addEventHandlers() {
        // Check Button Listener
        button.setOnAction(event -> model.addItem());
    }

    private void addValueChangedListeners() {

    }

    private void addBindings() {
    }
}
