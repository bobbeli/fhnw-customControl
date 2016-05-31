package ch.fhnw.ws4c.customControl;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

/**
 * Created by dimitri on 21.05.2016.
 */
public class ListUI extends VBox {
    private Button button;
    private ListView<Station> listView;
    private final PresentationModel model;
    private Background background;

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
