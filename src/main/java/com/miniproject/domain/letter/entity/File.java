package com.miniproject.domain.letter.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @package : com.miniproject.domain.letter.entity;
 * @name : File
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
@Data
@Table(name = "file")
@Setter
@ToString
public class File extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fileUrl;

    @Column
    private boolean isDeleted = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL) // n:1 관계 (File : Letter)
    @JoinColumn(name ="letter_id")
    private Letter letter;
}