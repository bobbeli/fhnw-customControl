package ch.fhnw.ws4c.module08.colormemoryinwork;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorMemoryUI extends VBox {
	private final CoolColorMemoryPM model;

	private ColorMixerPane  colorMixer;
	private Button          button;
	private ListView<Color> listView;

	public ColorMemoryUI(CoolColorMemoryPM model) {
		this.model = model;
		initializeControls();
		layoutControls();
		addEventHandlers();
		addValueChangedListeners();
		addBindings();
	}

	private void initializeControls() {
		colorMixer = new ColorMixerPane(model);
		button = new Button("This is cool");
		listView = new ListView<>(model.getCoolColors());
		listView.setCellFactory(param -> new ColorListCell());
	}



	private void layoutControls() {
		setPadding(new Insets(10));
		setSpacing(10);

		button.setMaxWidth(Double.MAX_VALUE);
		setVgrow(listView, Priority.ALWAYS);

		getChildren().addAll(colorMixer, button, listView);
	}

	private void addEventHandlers() {
		//calls addColor which adds one color to the ObservableList and automatically updates the ListView
		button.setOnAction(event -> model.addColor());
	}

	private void addValueChangedListeners() {
	}

	private void addBindings() {
	}
}
