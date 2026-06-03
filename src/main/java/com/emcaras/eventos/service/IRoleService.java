package com.emcaras.eventos.service;

import com.emcaras.eventos.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    Role findById(Long id);
    Role findByName(String name);
    Role save(Role role);
    void delete(Long id);
}
