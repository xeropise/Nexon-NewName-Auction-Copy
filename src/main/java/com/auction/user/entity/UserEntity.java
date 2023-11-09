package com.auction.user.entity;


import com.auction.common.entity.AbstractSystemEntity;
import com.auction.user.model.type.RoleType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER")
@NoArgsConstructor
public class UserEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<UserRoleEntity> userRoles = new LinkedHashSet<>();

    private UserEntity(String account, String password, String email) {
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public void addRole(RoleEntity role) {
        UserRoleEntity userRole = UserRoleEntity.create(this, role);
        userRoles.add(userRole);
    }

    public void removeRole(RoleEntity role) {
        userRoles.removeIf(it -> it.getRole().equals(role));
    }

    public UUID getUserId() {
        return this.userId;
    }

    public String getAccount() {
        return this.account;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public List<RoleType> getUserRoles() {
        return userRoles.stream().map(it -> it.getRoleType()).toList();
    }

    public static UserEntity create(
            String account,
            String password,
            String email
    ) {
        UserEntity user = new UserEntity(account, password, email);
        return user;
    }
}
