package com.example.honeyshop.entity.user;

import com.example.honeyshop.converter.RoleTypesConverter;
import com.example.honeyshop.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false, length = 50) private Set<RoleType> roleTypes = new LinkedHashSet<>();
    @Column(nullable = false, length = 20) private String phoneNumber;
}
