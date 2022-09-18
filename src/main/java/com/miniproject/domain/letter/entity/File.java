package com.miniproject.domain.letter.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE template SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false") // 검색에 대한 filter
@Setter
@ToString
public class File extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fileName;

    @Column
    private String fileUrl;

    @Column
    private boolean isDeleted = Boolean.FALSE;

    @ManyToOne // n:1 관계 (File : Letter)
    @JoinColumn(name ="letter_id")
    private Letter letter;

    public File(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }


}
