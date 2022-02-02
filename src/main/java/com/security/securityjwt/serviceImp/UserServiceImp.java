package com.security.securityjwt.serviceImp;

import com.security.securityjwt.domain.User;
import com.security.securityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        return this.userBuilder(user.getUsername(), user.getPass(), user.getRoles().toString());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPass(), new ArrayList<>());
    }

    private org.springframework.security.core.userdetails.User userBuilder(String username, String password, String roles){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles));

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }
}
