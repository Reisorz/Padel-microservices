package com.mls.service.padel_court.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PadelCourtSaveRequest {

    private String name;
    private String address;
    private Integer number;
    private boolean isGlass;
    private boolean isExterior;
}
