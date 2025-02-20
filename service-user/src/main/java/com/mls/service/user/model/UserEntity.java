package com.mls.service.user.model;

import com.mls.service.user.dto.request.UserUpdateRequest;
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

    @Column(name = "padel_level")
    private Double padelLevel;

    @Column(name = "preferred_side")
    private PreferredSide preferredSide;

    @Column(name = "padel_matches_id")
    private List<Long> padelMatchId;

    @Column(name = "padel_tournaments_id")
    private List<Long> padelTournamentId;



}
