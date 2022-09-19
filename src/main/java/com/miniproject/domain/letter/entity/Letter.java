package com.miniproject.domain.letter.entity;

import com.miniproject.domain.template.entity.Template;
import com.miniproject.domain.transportation.entity.Transportation;
import com.miniproject.domain.user.entity.User;
import com.miniproject.global.entity.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
@SQLDelete(sql = "UPDATE template SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false") // 검색에 대한 filter
@Setter
@ToString
public class Letter extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String departureCountry;

    @Column
    private String departureCity;

    @Column
    private String arrivalCountry;

    @Column
    private String arrivalCity;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String boarding_time; // 타입 나중에 Timestamp로 변경?

    @Column
    private String departure_time; // 타입 나중에 Timestamp로 변경?

    @Column
    private boolean isDeleted = Boolean.FALSE;

//    @ElementCollection (위도 경도)
//    private List<String> destinationLatLon;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (탬플릿)
    @JoinColumn(name = "template_id")
    private Template templateId;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑 (교통수단)
    @JoinColumn(name = "transportation_id")
    private Transportation transportationId;

    @OneToMany(mappedBy = "letter") // 일대다 매핑 (파일)
    //private List<File> files = new ArrayList<File>();
    private Set<File> files;

    @ManyToOne //n:1 관계 (user : Letter)
    @JoinColumn(name ="sender_id")
    private User senderId;
//
//    @ManyToOne
//    private User receiverId;

}