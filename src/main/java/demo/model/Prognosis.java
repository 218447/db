package demo.model;

import org.hibernate.annotations.GenericGenerator;
import org.la4j.Matrix;

import javax.persistence.*;

@Entity
@Table(name = "prognosis", catalog = "world")
public class Prognosis {

    private int id;
    private City city;
    private double temperatureMean;
    private String approximationFunctionForTemperature;

    public Prognosis() {}

    public Prognosis (City city, double temperatureMean, String approximationFunctionForTemperature) {
        this.city = city;
        this.temperatureMean = temperatureMean;
        this.approximationFunctionForTemperature = approximationFunctionForTemperature;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Column(name = "year_temperature_mean", nullable = false)
    public double getTemperatureMean() {
        return temperatureMean;
    }

    public void setTemperatureMean(double temperatureMean) {
        this.temperatureMean = temperatureMean;
    }

    @Column(name = "approximation_function_temperature", nullable = false)
    public String getApproximationFunction() {
        return approximationFunctionForTemperature;
    }

    public void setApproximationFunction(String approximationFunctionForTemperature) {
        this.approximationFunctionForTemperature = approximationFunctionForTemperature;
    }
}