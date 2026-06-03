package com.emcaras.eventos.mapper;

import com.emcaras.eventos.domain.Event;
import com.emcaras.eventos.dto.EventRequestDto;
import com.emcaras.eventos.dto.EventResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")//Este es un componente de spring
public interface EventMapper {
    List<EventResponseDto> toEventListResponseDto(List<Event> eventList);
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "speakers", ignore = true)
    @Mapping(target = "attendedUsers", ignore = true)
    Event toEventEntity(EventRequestDto eventRequestDto);
    EventResponseDto toEventResponseDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "speakers", ignore = true)
    @Mapping(target = "attendedUsers", ignore = true)
    void updateEventFromDto(EventRequestDto eventRequestDto, @MappingTarget Event event);
}
