package com.emcaras.eventos.controller;

import com.emcaras.eventos.domain.Role;
import com.emcaras.eventos.security.dto.RoleRequestDto;
import com.emcaras.eventos.security.dto.RoleResponseDto;
import com.emcaras.eventos.mapper.RoleMapper;
import com.emcaras.eventos.service.IRoleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping
    @Operation(summary = "Listar todos los roles")
    public ResponseEntity<List<RoleResponseDto>> findAll(){
        return ResponseEntity.ok(roleMapper.toRoleListResponseDto(roleService.findAll()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar rol por id")
    public ResponseEntity<RoleResponseDto> findById(@PathVariable Long id){
        Role role = roleService.findById(id);
        RoleResponseDto roleDto = roleMapper.toResponseDto(role);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo rol")
    public ResponseEntity<RoleResponseDto> save(@RequestBody RoleRequestDto roleRequestDto){
        Role role = roleService.save(roleMapper.toEntity(roleRequestDto));
        RoleResponseDto roleResponseDto = roleMapper.toResponseDto(role);
        return new ResponseEntity<>(roleResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol")
    public ResponseEntity<RoleResponseDto> update(@PathVariable Long id, @RequestBody RoleRequestDto roleRequestDto){
        Role roleToUpdate = roleService.findById(id);
        roleMapper.updateFromDto(roleRequestDto, roleToUpdate);
        Role updatedRole = roleService.save(roleToUpdate);
        RoleResponseDto roleResponseDto = roleMapper.toResponseDto(updatedRole);
        return ResponseEntity.ok(roleResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol")
    ResponseEntity<Void> delete(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
