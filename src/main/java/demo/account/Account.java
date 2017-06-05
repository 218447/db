package demo.account;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users", catalog = "world")
public class Account {

    private String username;
    private String password;
    private boolean enabled;
    private Set<AccountRole> userRole;

    public Account(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 45)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    public Set<AccountRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<AccountRole> userRole) {
        this.userRole = userRole;
    }

    @Column(name = "enabled", nullable = false)
    public boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}