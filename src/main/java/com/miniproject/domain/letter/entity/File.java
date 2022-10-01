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

    //추후에 letterId랑 연결?
    @Column
    private Integer letterId;

    @Column
    private String fileUrl;

    @Column
    private boolean isDeleted = Boolean.FALSE;

    //일단 버려
//    @ManyToOne(cascade = CascadeType.ALL) // n:1 관계 (File : Letter)
//    @JoinColumn(name ="letter_id")
//    private Letter letter;

//    @Column(name="letter_id")
//    private int letterId;

//    public File(String fileUrl) {
//        this.fileUrl = fileUrl;
//    }
}
