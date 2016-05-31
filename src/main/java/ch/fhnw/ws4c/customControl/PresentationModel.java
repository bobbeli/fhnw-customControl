package ch.fhnw.ws4c.customControl;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

/**
 * Created by dimitri on 21.05.2016.
 */



public class PresentationModel {
    private final StringProperty windowTitle = new SimpleStringProperty("SBB Träffpunkt");
    private final IntegerProperty railNumber = new SimpleIntegerProperty(0);
    private final IntegerProperty trainNumber = new SimpleIntegerProperty(0);

    private final ObservableList<Station> allItems = FXCollections.observableArrayList();

    // all getters and setters
    public void addItem(){
        allItems.add(new Station("Olten", new Date()));
    }
    public ObservableList<Station> getAllItems(){
        return allItems;
    }

    // Window Title Property
    public String getWindowTitle() {
        return windowTitle.get();
    }
    public StringProperty windowTitleProperty() {
        return windowTitle;
    }
    public void setWindowTitle(String windowTitle) {
        this.windowTitle.set(windowTitle);
    }

    // Rail Number Property
    public int getRailNumber(){return railNumber.get();}
    public IntegerProperty railProberty(){return  railNumber;}

    // Train Number Property
    public int getTrainNumber(){return  trainNumber.get();}
    public IntegerProperty trainProperty(){return trainNumber;}


}
