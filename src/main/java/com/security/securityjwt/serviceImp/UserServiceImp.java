package com.security.securityjwt.serviceImp;

import com.security.securityjwt.domain.Rol;
import com.security.securityjwt.domain.User;
import com.security.securityjwt.repository.RolRepository;
import com.security.securityjwt.repository.UserRepository;
import com.security.securityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

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

    @Override
    public User findOne(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User update(Integer id, User userRequest) {
        User user = userRepository.findById(id).get();
        user.setName(userRequest.getName());
        user.setLastName(userRequest.getLastName());
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        user.setPass(encoder.encode(user.getPass()));
        user.setStatus("VERIFIED");
        Rol rol = rolRepository.findByRol("USER");
        user.setRoles(new HashSet<Rol>(Arrays.asList(rol)));
        return userRepository.save(user);
    }
}
