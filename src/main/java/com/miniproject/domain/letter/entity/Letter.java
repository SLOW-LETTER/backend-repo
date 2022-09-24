package com.miniproject.domain.letter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import javax.persistence.*;

/**
 * @package : com.miniproject.domain.letter.entity;
 * @name : Letter
 * @create-date: 2022.09.14
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "letter")
@Setter
@Data
@ToString
public class Letter extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String departureCountry;

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String arrivalCountry;

    @Column(nullable = false)
    private String arrivalCity;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String boardingTime; // 타입 나중에 Timestamp로 변경?

    @Column(nullable = false)
    private String departureTime; // 타입 나중에 Timestamp로 변경?

    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    private String receiverEmail;

//    @ElementCollection (위도 경도)
//    private List<String> destinationLatLon;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (탬플릿)
    @JoinColumn(name = "template_id")
    @JsonIgnore
    private Template templateId;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (교통수단)
    @JoinColumn(name = "transportation_id")
    @JsonIgnore
    private Transportation transportationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receiver_id")
    private User receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;
}