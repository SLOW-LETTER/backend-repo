package com.miniproject.domain.user.entity;

import javax.persistence.*;

import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : 박수현
 * @version : 1.0.0
 * @package : com.miniproject.domain.user.entity
 * @name : User
 * @create-date: 2022-09-13
 * @update-date :
 * @update-author : 000
 * @update-description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@Setter
@ToString
public class User extends TimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, length = 30, unique = true)
    private String email; // 아이디

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false, length = 50)
    @Builder.Default
    private Boolean is_deleted = Boolean.FALSE;

    @Column
    private String withdraw_feedback;

    @Column(nullable = false)
    @Builder.Default
    private String profile_image_url = "https://slowletter.s3.ap-northeast-2.amazonaws.com/users/default_profile.png";

    @Column
    private String bio;

    @Column(nullable = false)
    @Builder.Default
    private Boolean is_checked_other_send = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean is_checked_my_receive = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean is_checked_my_send = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean is_checked_other_receive = Boolean.TRUE;

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

//    @OneToMany(mappedBy = "sender")
//    private Set<>

}
