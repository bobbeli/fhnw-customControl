package ch.fhnw.ws4c.module08.simplelistviewinwork;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Dieter Holz
 *
 * Funktionalität sämtlicher Daten Findet im PResentationsModel statt (CRUD options etc)
 */
public class PresentationModel {
	private final StringProperty windowTitle = new SimpleStringProperty("A very simple list");

	private final ObservableList<String> allItems = FXCollections.observableArrayList();

	// all getters and setters
	public void addItem(){
		allItems.add("Item - "+ allItems.size());
	}
	public ObservableList<String> getAllItems(){
		return allItems;
	}

	public String getWindowTitle() {
		return windowTitle.get();
	}

	public StringProperty windowTitleProperty() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle.set(windowTitle);
	}

}
