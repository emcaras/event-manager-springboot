package com.emcaras.eventos.service;

import com.emcaras.eventos.domain.Event;

import java.util.List;
import java.util.Optional;

public interface IEventService {
    List<Event> findAll();
    Event findById(Long id);
    Event save(Event event);
    void delete(Long id);
}
