package demo.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service("userDetailsService")
@Transactional
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountDAO accountDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        Account user = accountDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("no user with such name or password");
        }
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(Account user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<AccountRole> userRoles) {
        return userRoles.stream().map(
                userRole -> new SimpleGrantedAuthority(userRole.getRole()))
                .collect(toList());
    }
}