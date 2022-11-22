package com.security.securityjwt.service;

import com.security.securityjwt.domain.User;

import java.util.List;

public interface UserService {
    User findOne(Integer id);
    List<User> findAll();
    User update(Integer id, User user);
    User save(User user);
}
