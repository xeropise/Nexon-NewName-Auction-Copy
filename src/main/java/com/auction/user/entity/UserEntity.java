package com.auction.user.entity;


import com.auction.common.entity.AbstractSystemEntity;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER")
@RequiredArgsConstructor
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private Set<UserRoleEntity> roles = Set.of();

    public void addRole(UserRoleEntity role) {
        role.setUser(this);
        roles.add(role);
    }

    public void deleteRole(UserRoleEntity role) {
        roles.removeIf((it) -> it.getRoleType().equals(role));
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
}
