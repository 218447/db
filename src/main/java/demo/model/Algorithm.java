package demo.model;


import org.la4j.LinearAlgebra;
import org.la4j.Matrix;
import org.la4j.inversion.MatrixInverter;
import org.la4j.matrix.dense.Basic1DMatrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class Algorithm {

    @Autowired
    private WeatherDAO weatherDAO;

    private City city;
    private PolynomialRegreesion cityFunction;
    double[] data = new double[12];
    double[] monthsTrend = new double[12];

    public Algorithm () {}

    public void polynomialAproximation() {
        getDataForApproximation();

        double[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        cityFunction = new PolynomialRegreesion(x, data,  5);
    }

    private void getDataForApproximation () {
        for (int i = 0; i < 12 ; i++) {
            data[i] = getMonthMean(i+1);
        }
    }

    private double getMonthMean(int month) {
        List<AtmosphericData> data = weatherDAO.getAtmosphericDataByCityAndMonth(city.getCity(), month);

        double mean = 0;
        for (int i = 0; i < data.size(); i++) {
            mean += data.get(i).getTemperature();
        }
        mean = mean/data.size();
        return mean;
    }

    public void analysis() {
            polynomialAproximation();
    }

    public double countApproximation (int x) {
        return  new BigDecimal(cityFunction.predict(x)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double yearMean() {
        List<AtmosphericData> data = weatherDAO.getAll();
        double sum = 0;
        for (AtmosphericData bit: data) {
            sum += bit.getTemperature();
        }
        sum = sum/data.size();
        return new BigDecimal(cityFunction.predict(sum)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public PolynomialRegreesion getCityFunction () {
        return cityFunction;
    }

    public void setCity(City city) {
        this.city = city;
    }
}