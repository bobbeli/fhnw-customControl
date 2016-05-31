package ch.fhnw.ws4c.customControl;

import javafx.scene.layout.BorderPane;


/**
 * Created by dimitri on 21.05.2016.
 */
public class BorderPaneUI extends BorderPane  {

    private PresentationModel model;
    private ListUI list;
    private HeaderUI header;

    public BorderPaneUI(){
        initializeControls();
        layoutControls();
    }
    private void initializeControls() {
        model = new PresentationModel();
        list = new ListUI(model);
        header = new HeaderUI(model);
    }

    private void layoutControls() {
        setTop(header);
        setCenter(list);
    }

}
