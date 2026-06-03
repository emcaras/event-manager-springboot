package com.emcaras.eventos.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String location;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "events_speakers",
            joinColumns = @JoinColumn(
                    name = "event_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "speaker_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Speaker> speakers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @ManyToMany(mappedBy = "attendedEvents",fetch = FetchType.LAZY)
    private Set<User> attendedUsers = new HashSet<>();

    public void addSpeaker(Speaker speaker){
        speakers.add(speaker);
        speaker.getEvents().add(this);
    }

    public void removeSpeaker(Speaker speaker){
        speakers.remove(speaker);
        speaker.getEvents().remove(this);
    }

}
