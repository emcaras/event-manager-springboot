package com.emcaras.eventos.dto;

import com.emcaras.eventos.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto {
    private Long id;
    private String name;
    private LocalDate date;
    private String location;
    private Category category;
    private List<SpeakerDto> speakerDtos;
}
