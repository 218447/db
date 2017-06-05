package demo.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Account findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        return (Account) session.createQuery("from Account where username =:name").setParameter("name", username)
                .uniqueResult();
    }

    @Override
    public void insertUser(Account newUser) {
        newUser.setUserRole(new HashSet<>(asList(new AccountRole(newUser, "ROLE_USER"))));
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
    }

    @Override
    public void registerUser(String username, String password) {
        Account newUser = new Account(username, password, true);
        insertUser(newUser);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> findRoles(String username) {
        List<AccountRole> roles;
        Session session = sessionFactory.getCurrentSession();

        roles = session.createQuery("from AccountRole where username =:name").setParameter("name", username)
                .list();

        return roles.stream().map(ar -> ar.getRole()).collect(toList());
    }

}