package demo.account;

public interface UserDAO {

    User findByUsername(String username);
    void insertUser(User newUser);
    Role findRole(String username);
    void registerUser(String username, String password);
}
