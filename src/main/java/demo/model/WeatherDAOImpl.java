package demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class WeatherDAOImpl implements WeatherDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insertAtmosphericData(AtmosphericData atmosphericData) {
        Session session = sessionFactory.getCurrentSession();
        session.save(atmosphericData);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AtmosphericData> getAtmosphericDataByCityAndMonth(String city, int month) {
        Session session = sessionFactory.getCurrentSession();

        return  session.createQuery("from AtmosphericData data where data.city.city =:acity and data.month =:month")
                .setParameter("acity", city).setParameter("month", month).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AtmosphericData> getAtmosphericDataByCity(String city) {
        Session session = sessionFactory.getCurrentSession();

        return  session.createQuery("from AtmosphericData data where data.city.city = :acity").setParameter("acity", "Warszawa").list();
    }


    @Override
    public AtmosphericData getAtmosphericDataByDate(int date) {
        Session session = sessionFactory.getCurrentSession();

        return (AtmosphericData) session.createQuery("from atmospheric_data where date =: adate").setParameter
                ("adate", date).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AtmosphericData> getAtmosphericDataPast(int past) {
        Session session = sessionFactory.getCurrentSession();

        int lastDay = (int) session.createQuery("Select max(data) from atmospheric_data").uniqueResult();

        return session.createQuery("from atmospheric_data where date > adate").setParameter
                ("adate", lastDay-past).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AtmosphericData> getAll() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from AtmosphericData").list();
    }

    @Override
    public void insertPrognosis(Prognosis prognosis) {
        Session session = sessionFactory.getCurrentSession();
        session.save(prognosis);
    }
}
