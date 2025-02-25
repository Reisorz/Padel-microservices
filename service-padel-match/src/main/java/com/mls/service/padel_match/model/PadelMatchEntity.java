package com.mls.service.padel_match.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ElementCollection
    @CollectionTable(name = "match_scores_a", joinColumns = @JoinColumn(name = "match_id"))
    @Column(name = "score")
    private List<Integer> scoreA = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "match_scores_b", joinColumns = @JoinColumn(name = "match_id"))
    @Column(name = "score")
    private List<Integer> scoreB = new ArrayList<>();

    private Boolean isResultValidated;

    private Winner winner;

    @Column(nullable = false)
    private Long padelCourtId;

    public enum Winner {
        A,
        B
    }


}
