package com.auction.user.entity;

import com.auction.common.entity.AbstractSystemEntity;
import com.auction.user.model.type.RoleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "USER_ROLE")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userRoleId;

    @ManyToOne
    @JoinColumn(name = "userId", updatable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private RoleEntity role;

    private UserRoleEntity(UserEntity user, RoleEntity role) {
        this.user = user;
        this.role = role;
    }

    public UUID getUserRoleId() {
        return userRoleId;
    }

    public UserRoleEntity changeUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public RoleType getRoleType() {
        return role.getRoleType();
    }

    public static UserRoleEntity create(UserEntity user, RoleEntity role) {
        return new UserRoleEntity(user, role);
    }
}
