package com.emcaras.eventos.service;

import com.emcaras.eventos.domain.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
    void delete(Long id);
}
