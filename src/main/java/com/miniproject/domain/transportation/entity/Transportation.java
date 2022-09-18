package com.miniproject.domain.transportation.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import javax.persistence.*;

/**
 * @package : com.miniproject.domain.transportation.entity
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
@EqualsAndHashCode(callSuper=false)
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
    @Builder.Default
    private boolean is_deleted = Boolean.FALSE;
}
