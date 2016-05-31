package ch.fhnw.ws4c.customControl;


import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Created by dimitri on 25.05.2016.
 */
public class CellUI extends Pane {
    private String cityValue, timeValue;
    private Pane pane;
    private Line    line;
    private Circle  circle;
    private Text    time;
    private Text    city;


    public CellUI(String city, String time){
        cityValue = city;
        timeValue = time;
        initializeControls();
        layoutControls();
    }

    private void initializeControls() {
        time = new Text();
        time.setText(timeValue);
        time.setX(5);
        time.setY(10);
        city = new Text();
        city.setText(cityValue);
        city.setX(100);
        city.setY(10);
        line = new Line(65,5,65,60);
        circle = new Circle(65,5,5);
    }

    private void layoutControls(){
        this.getChildren().addAll(time, circle, line, city);
    }

    public Pane getCellPane(){
        pane = new Pane();
        pane.getChildren().addAll(time, circle, line, city);
        return pane;
    }

}
