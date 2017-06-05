package demo.account;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities", catalog = "world")
public class AccountRole implements Serializable{

    private Account account;
    private String role;

    public AccountRole () {
    }

    public AccountRole(Account account, String role) {
        this.account = account;
        this.role = role;
    }

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Column(name = "authority", nullable = false, length = 45)
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}