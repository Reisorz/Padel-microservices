package com.mls.service.padel_match.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mls.service.padel_match.model.PadelMatchEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMatchRequest {

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

    private Long padelCourtId;

}


