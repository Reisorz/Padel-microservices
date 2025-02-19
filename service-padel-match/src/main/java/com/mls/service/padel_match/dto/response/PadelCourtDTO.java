package com.mls.service.padel_match.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadelCourtDTO {

    private Long id;
    private String name;
    private String address;
    private Integer number;
    private boolean isGlass;
    private boolean isExterior;

}
