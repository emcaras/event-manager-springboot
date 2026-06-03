package com.emcaras.eventos.controller;

import com.emcaras.eventos.domain.User;
import com.emcaras.eventos.security.dto.UserRequestDto;
import com.emcaras.eventos.security.dto.UserResponseDto;
import com.emcaras.eventos.mapper.UserMapper;
import com.emcaras.eventos.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    private final UserMapper userMapper;

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<User> users = userService.findAll();
        List<UserResponseDto> usersDto= userMapper.toListDto(users);
        return ResponseEntity.ok(usersDto);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar usuario por id")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        User user = userService.findById(id);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Buscar usuario por username")
    public ResponseEntity<UserResponseDto> findByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo usuario")
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto){
        User userToSave = userMapper.toEntity(userRequestDto);
        User user = userService.save(userToSave);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario")
    public ResponseEntity<UserResponseDto> update(@Valid @PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        User userToUpdate = userService.findById(id);
        userMapper.updateFromDto(userRequestDto, userToUpdate);
        User updatedUser = userService.save(userToUpdate);
        UserResponseDto userResponseDto = userMapper.toResponseDto(updatedUser);

        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
