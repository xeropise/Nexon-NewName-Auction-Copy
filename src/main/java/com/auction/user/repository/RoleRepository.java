package com.auction.user.repository;

import com.auction.user.entity.RoleEntity;
import com.auction.user.model.type.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByRoleType(RoleType type);
}
