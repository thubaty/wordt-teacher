package sk.th.word;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        GrantedAuthority[] role_users = {new GrantedAuthorityImpl("ROLE_USER")};
        List<GrantedAuthority> grantedAuthorities = Arrays.asList(role_users);
        UserDetails user = new User("tomas","password", grantedAuthorities);
        return user;
    }
}
