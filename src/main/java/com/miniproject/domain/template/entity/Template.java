package com.miniproject.domain.template.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@SQLDelete(sql = "UPDATE template SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false") // 검색에 대한 filter
@NoArgsConstructor
@Setter
@ToString
public class Template extends TimeEntity {

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

//    @Override
//    public String toString() {
//        return "FileEntity{" +
//                "id=" + id +
//                ", fileName='" + fileName + '\'' +
//                ", s3Url='" + fileUrl + '\'' +
//                '}';
//    }
}
