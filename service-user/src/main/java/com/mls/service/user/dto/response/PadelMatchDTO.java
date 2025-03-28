package com.mls.service.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadelMatchDTO {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime matchDateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime matchDateEnd;

    private Integer durationInMinutes;

    private boolean isCompetitive;

    private boolean isPrivate;

    private Double pricePerPerson;

    private Double matchLevelStart;

    private Double matchLevelEnd;

    @Builder.Default
    private List<Integer> scoreA = new ArrayList<>();

    @Builder.Default
    private List<Integer> scoreB = new ArrayList<>();

    private Boolean isResultValidated;

    private Winner winner;

    private Long padelCourtId;

    public enum Winner {
        A,
        B
    }

}
