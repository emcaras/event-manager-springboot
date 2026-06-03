package com.emcaras.eventos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "speakers")
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 500)
    private String bio;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "speakers_events",
//            joinColumns = @JoinColumn(
//                    name = "speaker_id",
//                    referencedColumnName = "id"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "event_id",
//                    referencedColumnName = "id"
//            )
//    )
//    private Set<Event> events = new HashSet<>();



        @ManyToMany(mappedBy = "speakers")
        private Set<Event> events = new HashSet<>();
}
