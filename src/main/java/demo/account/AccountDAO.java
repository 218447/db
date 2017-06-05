package demo.account;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountDAO {

    Account findByUsername(String username);
    void insertUser(Account newAccount);
    void registerUser(String username, String password);
    @SuppressWarnings("unchecked")
    List<String> findRoles(String username);
}
