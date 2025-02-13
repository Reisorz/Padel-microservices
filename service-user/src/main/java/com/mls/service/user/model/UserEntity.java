package com.mls.service.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String city;

    @Column(name = "padel level")
    private Double padelLevel;

    @Column(name = "preferred side")
    private String preferredSide;

    @Column(name = "padel matches id")
    private List<Long> padelMatchId;

    @Column(name = "padel tournaments id")
    private List<Long> padelTournamentId;

}
