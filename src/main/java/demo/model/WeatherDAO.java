package demo.model;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WeatherDAO {
    void insertAtmosphericData(AtmosphericData atmosphericData);
    @SuppressWarnings("unchecked")
    List<AtmosphericData> getAtmosphericDataByCity(String city);
    AtmosphericData getAtmosphericDataByDate(int date);
    List<AtmosphericData> getAtmosphericDataByCityAndMonth(String city, int month);
    List<AtmosphericData> getAtmosphericDataPast(int past);
    List<AtmosphericData> getAll();
    void insertPrognosis(Prognosis prognosis);
}
