package com.auction.user.entity;

import com.auction.common.entity.AbstractSystemEntity;
import com.auction.user.model.type.RoleType;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "USER_ROLE")
@Entity
@RequiredArgsConstructor
public class UserRoleEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userRoleId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public UserRoleEntity(UserEntity user, RoleType roleType) {
        this.user = user;
        this.roleType = roleType;
    }

    public UUID getUserRoleId() {
        return userRoleId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public static UserRoleEntity create(UserEntity user, RoleType roleType) {
        return new UserRoleEntity(user, roleType);
    }
}
