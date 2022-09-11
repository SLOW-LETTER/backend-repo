package com.shanep.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLUpdate;

import javax.persistence.*;

/**
 * @package : com.shanep.model
 * @name : Transportation
 * @create-date: 2022.09.06
 * @author : 원우연
 * @version : 1.0.0
 *
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transportation")
@Getter
@Setter
@SQLDelete(sql = "UPDATE transportation SET is_deleted = true WHERE transportation_id = ?")
@ToString
@Builder

public class Transportation extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transportationId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double velocity;

    @Column
    private boolean is_deleted = Boolean.FALSE;

}