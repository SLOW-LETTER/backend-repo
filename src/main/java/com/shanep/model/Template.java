package com.shanep.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @package : com.shanep.config
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
@Getter
@SQLDelete(sql = "UPDATE template SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false") // 검색에 대한 filter
@NoArgsConstructor
@Setter
@ToString
public class Template extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String fileName;

    @Column
    private String fileUrl;

    @Column
    private boolean isDeleted = Boolean.FALSE;

    public Template(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
