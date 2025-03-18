package com.mls.service.padel_court;

import com.mls.service.padel_court.dto.request.PadelCourtSaveRequest;
import com.mls.service.padel_court.model.PadelCourtEntity;

import java.util.List;

public class DataProvider {


    public static PadelCourtEntity getPadelCourtEntity() {
        return PadelCourtEntity.builder()
                .id(1L)
                .address("Calle Mayor 1")
                .name("Padel World")
                .number(1)
                .isGlass(true)
                .isExterior(false)
                .build();
    }

    public static PadelCourtSaveRequest newPadelCourtSaveRequest() {
        return PadelCourtSaveRequest.builder()
                .address("Calle Lopez 1")
                .name("Padel Place")
                .number(2)
                .isGlass(true)
                .isExterior(false)
                .build();
    }

    public static PadelCourtEntity savePadelCourtEntity() {
        return PadelCourtEntity.builder()
                .id(1L)
                .address("Calle Lopez 1")
                .name("Padel Place")
                .number(2)
                .isGlass(true)
                .isExterior(false)
                .build();
    }

    public static List<PadelCourtEntity> listPadelCourtEntity() {
        return List.of(
                new PadelCourtEntity(1L,"Padel cool", "Calle Palma 12",4,true,false),
                new PadelCourtEntity(1L,"Tres Olivos", "Calle olivo 23",2,false,false),
                new PadelCourtEntity(1L,"Padel courts", "Calle tenerife 1",1,true,true));
    }

}
