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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime matchDate;

    private Integer durationInMinutes;

    private boolean isCompetitive;

    private boolean isPrivate;

    private Double pricePerPerson;

    private List<Long> teamA;

    private List<Long> teamB;

    private List<Integer> scoreA;

    private List<Integer> scoreB;

    private boolean isResultValidated;

    private Winner winner;

    private Long organizer;

    private Long padelCourtId;


    public enum Winner {
        A,
        B
    }


}
