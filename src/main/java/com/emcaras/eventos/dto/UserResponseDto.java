package com.emcaras.eventos.dto;

import com.emcaras.eventos.domain.Event;
import com.emcaras.eventos.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private String email;

    private Set<RoleDto> roles;
    private Set<EventSummaryDto> attendedEvents;

}
