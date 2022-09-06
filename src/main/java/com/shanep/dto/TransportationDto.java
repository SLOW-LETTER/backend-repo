package com.shanep.dto;

import com.shanep.model.Transportation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransportationDto {

    private  double velocity;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public  Transportation toEntity() {
         Transportation transportation = Transportation.builder()
                .velocity(velocity)

                .build();
        return transportation;
    }
}
