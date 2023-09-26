package com.auction.user.entity;


import com.auction.common.entity.AbstractSystemEntity;
import com.auction.user.model.RoleType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER")
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
    private Set<UserRoleEntity> roles = new LinkedHashSet<>();

    public UserEntity(String account, String password, String email, Set<UserRoleEntity> roles) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public void addRole(RoleType roleType) {
        UserRoleEntity role = UserRoleEntity.create(this, roleType);
        roles.add(role);
    }

    public void deleteRole(RoleType roleType) {
        roles.removeIf((it) -> it.getRoleType().equals(roleType));
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

    public static UserEntity create(
            String account,
            String password,
            String email
    ) {
        return new UserEntity(account, password, email, new LinkedHashSet());
    }
}
