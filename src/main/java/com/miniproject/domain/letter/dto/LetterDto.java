package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.Letter;
import lombok.Getter;
import lombok.Setter;

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
public class LetterDto {
    private String receiverEmail;
    private String boardingTime;
    private String departureTime;
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
                .departureCountry(departureCountry)
                .departureCity(departureCity)
                .arrivalCity(arrivalCity)
                .arrivalCountry(arrivalCountry)
                .title(title)
                .content(content)
                .boardingTime(boardingTime)
                .departureTime(departureTime)
                .receiverEmail(receiverEmail)
                .build();
        return letter;
    }
}
