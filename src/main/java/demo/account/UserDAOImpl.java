package demo.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;

    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        return (User) session.createQuery("from Account where username =: username").setParameter("name", username)
                .uniqueResult();
    }

    public void insertUser(User newUser) {
        newUser.setUserRole(Role.user);
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
    }

    public void registerUser(String username, String password) {
        User newUser = new User(username, password, Role.user);
        insertUser(newUser);
    }

    public Role findRole(String username) {
        Session session = sessionFactory.getCurrentSession();

        return (Role) session.createQuery("from users where username =:name").setParameter("name", username)
                .uniqueResult();
    }
}