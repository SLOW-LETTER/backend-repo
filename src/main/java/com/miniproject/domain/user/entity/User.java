package com.miniproject.domain.user.entity;

import javax.persistence.*;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
* @package : com.miniproject.domain.user.entity
* @name : User
* @create-date: 2022-09-13
* @author : 박수현
* @version : 1.0.0
* 
* @update-date :
* @update-author : 000
* @update-description : 
*/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name="user")
@Data
@Setter
@SQLDelete(sql = "UPDATE user SET is_deleted = true WHERE id = ?")
@ToString
public class User extends TimeEntity implements UserDetails {
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
    private Boolean is_deleted = Boolean.FALSE;

    @Column
    private String withdraw_feedback;

    @Column
    private String profile_image_url;

    @Column
    private String bio;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_other_send = Boolean.TRUE;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_my_receive = Boolean.TRUE;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_my_send = Boolean.TRUE;

    @Column(nullable = false)
    // @ColumnDefault("true")
    private Boolean is_checked_other_receive = Boolean.TRUE;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    // @ColumnDefault(Role.USER.getValue())
//    private Role role = Role.USER;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
