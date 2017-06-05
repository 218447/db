package demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "atmospheric_data", catalog = "world")
public class AtmosphericData {

    private int id;
    private City city;
    private int temperature;
    private int humidity;
    private int airVelocity;
    private String hour;
    private int month;
    private int day;

    public AtmosphericData() {}

    public AtmosphericData(City city, int temperature, int humidity, int airVelocity, String hour, int month, int day) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.airVelocity = airVelocity;
        this.hour = hour;
        this.month = month;
        this.day = day;
    }

    public AtmosphericData(String city, int temperature, int humidity, int airVelocity, String hour, int month, int day) {
        this.city = new City(city);
        this.temperature = temperature;
        this.humidity = humidity;
        this.airVelocity = airVelocity;
        this.hour = hour;
        this.month = month;
        this.day = day;    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city")
    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "temperature", nullable = false)
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Column(name = "humidity", nullable = false)
    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Column(name = "air_velocity", nullable = false, length = 5)
    public int getAirVelocity() {
        return airVelocity;
    }

    public void setAirVelocity(int airVelocity) {
        this.airVelocity = airVelocity;
    }

    @Column(name = "hour", nullable = false, length = 5)
    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Column(name = "month", nullable = false, length = 45)
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Column(name = "day", nullable = false, length = 45)
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}