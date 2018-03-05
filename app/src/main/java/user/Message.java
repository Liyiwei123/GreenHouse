package user;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/19.
 */

public class Message {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTemp() {
        return temp;
    }

    public Message(Integer id, float temp, String time,float tempout) {
        this.id = id;
        this.temp = temp;
        this.time = time;
        this.tempout=tempout;
    }

    public Message() {
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", temp=" + temp +
                ", tempout=" + tempout +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }

    private float temp;
    private float tempout;

    public float getTempout() {
        return tempout;
    }

    public void setTempout(float tempout) {
        this.tempout = tempout;
    }

    private Date date;
private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
