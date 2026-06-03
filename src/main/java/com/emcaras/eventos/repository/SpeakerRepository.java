package com.emcaras.eventos.repository;

import com.emcaras.eventos.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    Optional<Speaker> findByEmail(String email);
    Boolean existsByEmail(String email);
}
