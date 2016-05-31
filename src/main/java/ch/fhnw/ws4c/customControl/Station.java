package ch.fhnw.ws4c.customControl;

import java.util.Date;
import java.text.*;


/**
 * Created by dimitri on 23.05.2016.
 */
public class Station {
    private String name;
    private Date arriveTime;
    DateFormat df = new SimpleDateFormat("HH:MM");

    public Station(String _name, Date _arriveTime){
        name = _name;
        arriveTime = _arriveTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArriveTime() {
        return df.format(arriveTime);

    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }
}
