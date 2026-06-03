package com.emcaras.eventos.mapper;

import com.emcaras.eventos.domain.Role;
import com.emcaras.eventos.security.dto.RoleRequestDto;
import com.emcaras.eventos.security.dto.RoleResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<RoleResponseDto> toRoleListResponseDto(List<Role> roles);
    Role toEntity(RoleRequestDto role);
    RoleResponseDto toResponseDto(Role role);
    void updateFromDto(RoleRequestDto roleRequestDto, @MappingTarget Role role);
}
