package sk.th.word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sk.th.pipifax.UserRepository;
import sk.th.pipifax.entity.UserEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserEntity userByUsername = userRepository.findUserByUsername(s);
        if (userByUsername == null) {
            return null;
        } else {
            GrantedAuthority[] role_users = {new GrantedAuthorityImpl("ROLE_USER")};
            List<GrantedAuthority> grantedAuthorities = Arrays.asList(role_users);
            UserDetails user = new User(userByUsername.getUsername(),userByUsername.getPassword(), grantedAuthorities);
            return user;
        }
    }
}
