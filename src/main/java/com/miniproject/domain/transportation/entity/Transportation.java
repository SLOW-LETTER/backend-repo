package com.miniproject.domain.transportation.entity;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author : 원우연
 * @version : 1.0.0
 * @package : com.miniproject.domain.transportation.entity
 * @name : Transportation
 * @create-date: 2022.09.06
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transportation")
@Getter
@Setter
@ToString
@Builder
public class Transportation extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false)
    private double velocity;

    @Column(nullable = false)
    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
