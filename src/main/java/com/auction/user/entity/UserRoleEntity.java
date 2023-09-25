package com.auction.user.entity;

import com.auction.user.model.RoleType;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "USER_ROLE")
@Entity
@RequiredArgsConstructor
public class UserRoleEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userRoleId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private UserEntity user;
    private RoleType roleType;

    public UUID getUserRoleId() {
        return userRoleId;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RoleType getRoleType() {
        return roleType;
    }
}
