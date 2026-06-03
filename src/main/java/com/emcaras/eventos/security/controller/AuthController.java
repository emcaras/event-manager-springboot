package com.emcaras.eventos.security.controller;

import com.emcaras.eventos.domain.Role;
import com.emcaras.eventos.domain.User;
import com.emcaras.eventos.security.dto.JwtResponseDto;
import com.emcaras.eventos.security.dto.LoginDto;
import com.emcaras.eventos.security.dto.UserRequestDto;
import com.emcaras.eventos.security.dto.UserResponseDto;
import com.emcaras.eventos.mapper.UserMapper;
import com.emcaras.eventos.security.jwt.JwtGenerator;
import com.emcaras.eventos.service.IRoleService;
import com.emcaras.eventos.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final IUserService userService;
    private final IRoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){

        User userRequest = userMapper.toEntity(userRequestDto);
        userRequest.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));


        User user = userService.save(userRequest);
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
