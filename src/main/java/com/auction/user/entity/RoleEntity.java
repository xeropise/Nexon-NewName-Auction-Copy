package com.auction.user.entity;

import com.auction.common.entity.AbstractSystemEntity;
import com.auction.user.model.type.RoleType;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "ROLE")
@Entity
@NoArgsConstructor
public class RoleEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID roleId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(nullable = true)
    private String description;

    private RoleEntity(RoleType roleType) {
        this.roleType = roleType;
    }

    public RoleType getRoleType() {
        return this.roleType;
    }

    public static RoleEntity create(RoleType roleType) {
        return new RoleEntity(roleType);
    }
}
