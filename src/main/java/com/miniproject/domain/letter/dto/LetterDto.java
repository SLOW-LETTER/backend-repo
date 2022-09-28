package com.miniproject.domain.letter.dto;

import com.miniproject.domain.letter.entity.Letter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    //@Schema(description = "수신자 이메일", example ="test1@test.com")
    private String receiverEmail;

    //@Schema(description = "출발 시간", example ="2022-09-24 17:12")
    private String boardingTime;

    //@Schema(description = "도착 시간", example ="2022-09-25 02:25")
    private String arrivalTime;

    //@Schema(description = "출발 국가", example ="대한민국")
    private String departureCountry;

    //@Schema(description = "출발 도시", example ="서울")
    private String departureCity;

    //@Schema(description = "도착 국가", example ="미국")
    private String arrivalCountry;

    //@Schema(description = "도착 도시", example ="뉴욕")
    private String arrivalCity;

    //@Schema(description = "편지 제목", example ="ㅇㅇ에게")
    private String title;

    //@Schema(description = "편지 내용", example ="안녕, 잘 지내니?")
    private String content;

    //@Schema(description = "탬플릿 아이디 값", example ="1")
    private int templateId;

    //@Schema(description = "교통수단 아이디 값", example ="1")
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
                .arrivalTime(arrivalTime)
                .receiverEmail(receiverEmail)
                .build();
        return letter;
    }
}
