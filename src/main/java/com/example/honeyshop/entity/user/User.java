package com.example.honeyshop.entity.user;

import com.example.honeyshop.converter.RoleTypesConverter;
import com.example.honeyshop.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(length = 100, unique = true, nullable = false, updatable = false)
    private String id;

    @Column(length = 100, nullable = false) private String password;
    @Column(length = 30, nullable = false) private String nickname;

    @Column(length = 20, nullable = false) private String phoneNumber;

    @Convert(converter = RoleTypesConverter.class)
    @Column(length = 50, nullable = false) private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Builder
    private User(String id, String password, String nickname, Set<RoleType> roleTypes, String phoneNumber) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.roleTypes = roleTypes;
        this.phoneNumber = phoneNumber;
    }
}
