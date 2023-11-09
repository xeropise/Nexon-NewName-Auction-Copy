package com.auction.user.repository;

import com.auction.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(
            """
                      SELECT u FROM UserEntity u 
                      LEFT JOIN FETCH u.userRoles ur
                      LEFT JOIN FETCH ur.role
                    """
    )
    List<UserEntity> findAll();

    @Query(
            """
                              SELECT DISTINCT u FROM UserEntity u 
                              LEFT JOIN FETCH u.userRoles ur
                              WHERE u.userId = :uuid
                    """
    )
    Optional<UserEntity> findByUserId(UUID uuid);

    @Query(
            """
                              SELECT DISTINCT u FROM UserEntity u 
                              LEFT JOIN FETCH u.userRoles ur
                              LEFT JOIN FETCH ur.role
                              WHERE u.account = :account
                    """
    )
    Optional<UserEntity> findByAccount(String account);

    @Query(
            """
                              SELECT DISTINCT u FROM UserEntity u 
                              LEFT JOIN FETCH u.userRoles ur
                              LEFT JOIN FETCH ur.role
                              WHERE u.email = :email
                    """
    )
    Optional<UserEntity> findByEmail(String email);

    boolean existsByAccountOrEmail(String account, String email);
}
