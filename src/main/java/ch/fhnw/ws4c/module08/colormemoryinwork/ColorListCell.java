package ch.fhnw.ws4c.module08.colormemoryinwork;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by dimitri on 03.05.2016.
 */
public class ColorListCell extends ListCell<Color>{

    @Override
    protected void updateItem(Color item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);
        if(item != null && !empty){
            setText(item.getRed()+", "+item.getBlue()+", "+item.getGreen());
            // over setGraphic you can add what ever Custom Control you want.!
            setGraphic(new Circle(20,item));

        }
    }
}
