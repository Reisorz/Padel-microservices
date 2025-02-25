package com.mls.service.match_user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "match-user")
public class MatchUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long matchId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private boolean isOrganizer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Team team;

    public enum Team {
        A,
        B
    }
}
