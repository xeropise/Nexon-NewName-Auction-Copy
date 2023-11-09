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
    private Set<UserRoleEntity> roles = new LinkedHashSet<>();

    private UserEntity(String account, String password, String email, Set<UserRoleEntity> roles) {
        this.account = account;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public UserEntity addRole(RoleType roleType) {
        UserRoleEntity role = UserRoleEntity.create(this, roleType);
        roles.add(role);

        return this;
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

    public List<RoleType> getRoles() {
        return roles.stream().map(it -> it.getRoleType()).toList();
    }

    private static UserEntity create(
            String account,
            String password,
            String email
    ) {
        return new UserEntity(account, password, email, new LinkedHashSet());
    }

    public static UserEntity createUser(
            String account,
            String password,
            String email
    ) {
        return create(account, password, email).addRole(RoleType.USER);
    }

    public static UserEntity createAdminUser(
            String account,
            String password,
            String email
    ) {
        return create(account, password, email).addRole(RoleType.ADMIN);
    }

    public static UserEntity createTest(
            String account,
            String password,
            String email
    ) {
        return create(account, password, email).addRole(RoleType.TEST);
    }
}
