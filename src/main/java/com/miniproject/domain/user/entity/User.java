package com.miniproject.domain.user.entity;

import javax.persistence.*;

import com.miniproject.global.entity.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="users")
public class User extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String email; // 아이디

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false, length = 50)
    // @ColumnDefault("false")
    private Boolean is_deleted = false;

    @Column
    private String withdraw_feedback;

    @Column
    private String profile_image_url;

    @Column
    private String bio;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_other_send = true;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_my_receive = true;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_my_send = true;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_other_receive = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    // @ColumnDefault(Role.USER.getValue())
    private Role role = Role.USER;
}
