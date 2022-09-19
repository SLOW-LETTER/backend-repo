package com.miniproject.domain.transportation.dto;

import com.miniproject.domain.transportation.entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @package : com.miniproject.domain.transportation.dto
 * @name : TransportationDto
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportationDto {

    private String name;
    private  double velocity;

    public Transportation toEntity() {
        Transportation transportation = Transportation.builder()
                .name(name)
                .velocity(velocity)
                .build();

        return transportation;
    }
}
