package com.emcaras.eventos.service;

import com.emcaras.eventos.domain.Role;
import com.emcaras.eventos.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService implements IRoleService{
    private final RoleRepository roleRepository;
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El id " + id + " no existe"));
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findRoleByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol " + name + " no existe"));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        var role = findById(id);
        roleRepository.delete(role);
    }
}
