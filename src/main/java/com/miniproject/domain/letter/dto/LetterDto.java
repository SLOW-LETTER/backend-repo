package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.Letter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


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
    public String receiverEmail;
    public String senderEmail;
    public String templateId;
    public String transportationId;
    public String departureCountry;
    public String departureCity;
    public String arrivalCountry;
    public String arrivalCity;
    public String title;
    public String content;

    public Letter toEntity() {
        Letter letter = Letter.builder()
                .templateId(templateId)
                .transportationId(transportationId)
                .departureCity(departureCity)
    }
}
