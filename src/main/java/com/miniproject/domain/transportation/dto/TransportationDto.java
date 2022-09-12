package com.miniproject.domain.transportation.dto;

import com.miniproject.domain.transportation.entity.Transportation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

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

    private  double velocity;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Transportation toEntity() {
        Transportation transportation = Transportation.builder()
                .velocity(velocity)

                .build();
        return transportation;
    }
}
