package com.emcaras.eventos.mapper;

import com.emcaras.eventos.domain.Speaker;
import com.emcaras.eventos.dto.SpeakerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpeakerMapper {
    Speaker toEntity(SpeakerDto speakerDto);
    SpeakerDto toDto(Speaker speaker);
    List<SpeakerDto> toListDto(List<Speaker> speakers);
    void updateFromDto(SpeakerDto speakerDto, @MappingTarget Speaker speaker);
}
