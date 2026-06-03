package com.emcaras.eventos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDto {
    @NotBlank(message = "El nombre no deve ir vacio")
    private String name;
    @NotNull(message = "La fecha no debe ser null")
    private LocalDate date;
    @NotBlank(message = "La ubicacion no debe ir vacia")
    private String location;
}
