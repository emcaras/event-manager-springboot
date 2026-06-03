package com.emcaras.eventos.controller;

import com.emcaras.eventos.domain.Event;
import com.emcaras.eventos.dto.EventRequestDto;
import com.emcaras.eventos.dto.EventResponseDto;
import com.emcaras.eventos.mapper.EventMapper;
import com.emcaras.eventos.service.IEventService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController{
    private final IEventService eventService;
    private final EventMapper eventMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Listar todos los eventos")
    public ResponseEntity<List<EventResponseDto>> findAll(){
        return ResponseEntity.ok(eventMapper.toEventListResponseDto(eventService.findAll()));
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo evento")
    public ResponseEntity<EventResponseDto> save(@Valid @RequestBody EventRequestDto eventRequestDto){
        Event event = eventService.save(eventMapper.toEventEntity(eventRequestDto));
        return new ResponseEntity<>(eventMapper.toEventResponseDto(event), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evento por id")
    public ResponseEntity<EventResponseDto> findById(@PathVariable Long id){
        Event event = eventService.findById(id);
        EventResponseDto eventResponseDto = eventMapper.toEventResponseDto(event);
        return ResponseEntity.ok(eventResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un evento")
    public ResponseEntity<EventResponseDto> update(@PathVariable Long id, @Valid @RequestBody EventRequestDto eventRequestDto){
        Event eventToUpdate = eventService.findById(id);
        eventMapper.updateEventFromDto(eventRequestDto, eventToUpdate);
        Event event = eventService.save(eventToUpdate);
        EventResponseDto eventResponseDto = eventMapper.toEventResponseDto(event);
        return new ResponseEntity<>(eventResponseDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
