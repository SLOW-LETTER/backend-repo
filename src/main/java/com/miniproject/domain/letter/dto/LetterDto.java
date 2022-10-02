package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.Letter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @package : com.miniproject.domain.letter.dto;
 * @name : LetterDto
 * @create-date: 2022.09.17
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "편지 DTO")
public class LetterDto {
    private Integer id;
    private String receiverEmail;
    private String boardingTime;
    private String arrivalTime;
    private String departureCountry;
    private String departureCity;
    private String arrivalCountry;
    private String arrivalCity;
    private String title;
    private String content;
    private int templateId;
    private int transportationId;

    public Letter toEntity(){
        Letter letter = Letter.builder()
                .id(id)
                .departureCountry(departureCountry)
                .departureCity(departureCity)
                .arrivalCity(arrivalCity)
                .arrivalCountry(arrivalCountry)
                .title(title)
                .content(content)
                .boardingTime(boardingTime)
                .arrivalTime(arrivalTime)
                .receiverEmail(receiverEmail)
                .build();
        return letter;
    }

}