package com.mls.service.padel_match.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "padel_matches")
public class PadelMatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime matchDateStart;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime matchDateEnd;

    @Column(nullable = false)
    private Integer durationInMinutes;

    @Column(nullable = false)
    private boolean isCompetitive;

    @Column(nullable = false)
    private boolean isPrivate;

    @Column(nullable = false)
    private Double pricePerPerson;

    @Column(nullable = false)
    private Double matchLevelStart;

    @Column(nullable = false)
    private Double matchLevelEnd;

    private List<Long> teamA;

    private List<Long> teamB;

    private List<Integer> scoreA;

    private List<Integer> scoreB;

    private Boolean isResultValidated;

    private Winner winner;

    @Column(nullable = false)
    private Long organizer;

    @Column(nullable = false)
    private Long padelCourtId;


    public enum Winner {
        A,
        B
    }


}
