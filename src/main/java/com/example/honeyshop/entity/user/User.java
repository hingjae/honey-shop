package com.example.honeyshop.entity.user;

import com.example.honeyshop.converter.RoleTypesConverter;
import com.example.honeyshop.entity.BaseTimeEntity;
import com.example.honeyshop.entity.Cart;
import lombok.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    @Setter
    private Cart cart;

    @Column(length = 100, nullable = false) private String password;
    @Column(length = 30, nullable = false) private String nickname;

    @Column(length = 20, nullable = false) private String phoneNumber;

    @Convert(converter = RoleTypesConverter.class)
    @Column(length = 50, nullable = false) private Set<RoleType> roleTypes = new LinkedHashSet<>();

    @Builder
    public User(String id, Cart cart, String password, String nickname, String phoneNumber, Set<RoleType> roleTypes) {
        this.id = id;
        this.cart = cart;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.roleTypes = roleTypes;
    }
}
