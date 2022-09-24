package com.miniproject.domain.user.entity;

import javax.persistence.*;

import com.miniproject.domain.letter.entity.Letter;
import com.miniproject.global.entity.TimeEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
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
    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @Column
    private String withdrawFeedback;

    @Column(nullable = false)
    @Builder.Default
    private String profileImageUrl = "https://slowletter.s3.ap-northeast-2.amazonaws.com/users/default_profile.png";

    @Column
    private String bio;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isCheckedOtherSend = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isCheckedMyReceive = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isCheckedMySend = Boolean.TRUE;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isCheckedOtherReceive = Boolean.TRUE;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    private List<Letter> receiveLetters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    private List<Letter> letters = new ArrayList<>();
}
