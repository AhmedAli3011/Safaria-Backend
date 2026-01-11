package com.safaria.backend.DTO;

import java.util.List;

import com.safaria.backend.entity.TourismTypes;

import lombok.Data;

@Data
public class TourRequestDTO {

    private String title;
    private String description;
    private String destinationCountry;
    private Long tourProviderId;
    private String currency;
    private List<TourismTypes> tourismTypes;
    private List<TourScheduleDTO> tourSchedules;

}
