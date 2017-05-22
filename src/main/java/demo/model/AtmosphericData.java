package demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "atmospheric_data", catalog = "world")
public class AtmosphericData {

    private String hour;
    private int id;
    private int temperature;
    private int pressure;
    private int humidity;
    private int airVelocity;
    private int date;
    private Boolean prognosis;

    AtmosphericData(String hour, int id, int temperature, int pressure, int humidity, int airVelocity) {
        this.hour = hour;
        this.id = id;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.airVelocity = airVelocity;
    }

    AtmosphericData(String hour, int id, int temperature, int pressure, int humidity, int airVelocity, Boolean prognosis) {
        this.hour = hour;
        this.id = id;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.airVelocity = airVelocity;
        this.prognosis = prognosis;
    }

    @Id
    @GenericGenerator(name="idGenerator" , strategy="increment")
    @GeneratedValue(generator="idGenerator")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "hour", nullable = false, length = 10)
    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Column(name = "temperature", nullable = false, length = 10)
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Column(name = "humidity", nullable = false, length = 10)
    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Column(name = "pressure", nullable = false, length = 10)
    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    @Column(name = "airvelocity", nullable = false, length = 10)
    public int getAirVelocity() {
        return airVelocity;
    }

    public void setAirVelocity(int airVelocity) {
        this.airVelocity = airVelocity;
    }

    @Column(name = "date", nullable = false, length = 45)
    public int getDate() {
        return date;
    }

    public void setDate(int atmosphericData) {
        this.date = date;
    }

    @Column(name = "prognosis", nullable = true)
    public Boolean getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Boolean prognosis) {
        this.prognosis = prognosis;
    }
}