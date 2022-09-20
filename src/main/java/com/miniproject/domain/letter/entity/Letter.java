package com.miniproject.domain.letter.entity;

import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

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
    private String boarding_time; // 타입 나중에 Timestamp로 변경?

    @Column(nullable = false)
    private String departure_time; // 타입 나중에 Timestamp로 변경?

    @Column(nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @Column(nullable = false)
    private String receiverId;

//    @ElementCollection (위도 경도)
//    private List<String> destinationLatLon;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (탬플릿)
    @JoinColumn(name = "template_id")
    private Template templateId;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (교통수단)
    @JoinColumn(name = "transportation_id")
    private Transportation transportationId;

//    @OneToMany(mappedBy = "letter") // 일대다 매핑 (파일)
//    //private List<File> files = new ArrayList<File>();
//    private Set<File> files;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name="letter_id")
//    private Set<File> file;

    @ManyToOne //n:1 관계 (user : Letter)
    @JoinColumn(name ="sender_id")
    private User user;
//
//    @ManyToOne
//    @JoinColumn(name ="receiver_id")
//    private User user;

}