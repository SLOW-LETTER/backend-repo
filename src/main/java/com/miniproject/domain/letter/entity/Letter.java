package com.miniproject.domain.letter.entity;

import com.miniproject.domain.template.entity.Template;
import com.miniproject.global.entity.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.List;

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
    private String departure;

    @Column
    private String destination;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Timestamp boarding_time;

    @Column
    private Timestamp departure_time;

    @Column
    private boolean isDeleted = Boolean.FALSE;

    @ElementCollection
    private List<String> destinationLatLon;

    @OneToOne(fetch = FetchType.LAZY) // 일대일 매핑
    @JoinColumn(name = "id")
    private Template templateId;

//    @ManyToOne
//    private User senderId;
//
//    @ManyToOne
//    private User receiverId;
//
//    @OnetoMany
//    private Transportation transportationId;
}