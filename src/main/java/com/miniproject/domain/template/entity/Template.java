package com.miniproject.domain.template.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @package : com.miniproject.domain.template.entity
 * @name : Template
 * @create-date: 2022.09.06
 * @author : 김현진
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "template")
@ToString
public class Template extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false)
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;

//    public Template(String fileName, String fileUrl) {
//        this.fileName = fileName;
//        this.fileUrl = fileUrl;
//    }
}
