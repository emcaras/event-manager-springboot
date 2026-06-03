package com.emcaras.eventos.mapper;

import com.emcaras.eventos.domain.Role;
import com.emcaras.eventos.domain.User;
import com.emcaras.eventos.security.dto.UserRequestDto;
import com.emcaras.eventos.security.dto.UserResponseDto;
import com.emcaras.eventos.service.IRoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected IRoleService roleService;

    public abstract List<UserResponseDto> toListDto(List<User> users);

    @Mapping(target = "password", ignore = true)
    public abstract UserResponseDto toResponseDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "attendedEvents", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    public abstract User toEntity(UserRequestDto userRequestDto);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoles")
    public abstract void updateFromDto(UserRequestDto userRequestDto, @MappingTarget User user);

    @Named("mapRoles")
    public Set<Role> mapRoles(Set<String> roles){
        if(roles == null || roles.isEmpty()){
            return Collections.singleton(roleService.findByName("ROLE_USER"));
        }

        return roles.stream().map(role ->
                    roleService.findByName(role)
        ).collect(Collectors.toSet());
    }
}
