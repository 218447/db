package demo.model;

import org.hibernate.Session;

import java.util.List;

public interface WeatherDAO {
    void insertAtmosphericData(AtmosphericData atmosphericData);
    AtmosphericData getAtmosphericDataById(int id);
    AtmosphericData getAtmosphericDataByDate(int date);
    List<AtmosphericData> getAtmosphericDataPast(int past);
}
