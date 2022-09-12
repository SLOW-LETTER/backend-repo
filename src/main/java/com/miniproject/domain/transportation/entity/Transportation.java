package com.miniproject.domain.transportation.entity;

import com.shanep.model.TimeEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

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

    @Column
    private String name;

    @Column
    private double velocity;

    @Column
    private boolean is_deleted = Boolean.FALSE;

}