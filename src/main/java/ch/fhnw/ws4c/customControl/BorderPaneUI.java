package ch.fhnw.ws4c.customControl;

import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;


/**
 * Created by dimitri on 21.05.2016.
 */
public class BorderPaneUI extends BorderPane  {

    private PresentationModel model;
    private ListUI list;
    private DetailUI detail;

    public BorderPaneUI(){
        initializeControls();
        layoutControls();
    }
    private void initializeControls() {
        model = new PresentationModel();
        list = new ListUI(model);
        detail = new DetailUI(model);
    }

    private void layoutControls() {
        setRight(detail);
        setCenter(list);
    }

}
