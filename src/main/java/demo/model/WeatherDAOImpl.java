package demo.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void insertAtmosphericData(AtmosphericData atmosphericData) {
        Session session = sessionFactory.getCurrentSession();
        session.save(atmosphericData);
    }

    @Override
    public AtmosphericData getAtmosphericDataById(int id) {
        Session session = sessionFactory.getCurrentSession();

        return (AtmosphericData) session.createQuery("from atmospheric_data where id =: id").setParameter
                ("id", id).uniqueResult();
    }

    @Override
    public AtmosphericData getAtmosphericDataByDate(int date) {
        Session session = sessionFactory.getCurrentSession();

        return (AtmosphericData) session.createQuery("from atmospheric_data where date =: adate").setParameter
                ("adate", date).list();
    }

    @Override
    public List<AtmosphericData> getAtmosphericDataPast(int past) {
        Session session = sessionFactory.getCurrentSession();

        int lastDay = (int) session.createQuery("Select max(data) from atmospheric_data").uniqueResult();

        return session.createQuery("from atmospheric_data where date > adate").setParameter
                ("adate", lastDay-past).list();
    }
}
