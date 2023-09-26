package com.auction.user.repository;

import com.auction.AuctionDataJpaTest;
import com.auction.user.entity.UserEntity;
import com.auction.user.model.RoleType;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@AuctionDataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private final UserRepository userRepository = null;

    @Autowired
    private final TestEntityManager testEntityManager = null;

    @Test
    public void user_select_test() {
        List<UserEntity> users = userRepository.findAll();
        Assertions.assertThat(users).isEmpty();
    }

    @Test
    public void user_insert_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );

        user = userRepository.save(user);
        testEntityManager.flush();

        UserEntity fetchedUser = userRepository.findByUserId(user.getUserId()).get();

        Assertions.assertThat(user.getUserId()).isEqualTo(fetchedUser.getUserId());
    }

    @Test
    public void user_and_user_role_insert_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );

        user.addRole(RoleType.ADMIN);
        user = userRepository.save(user);
        testEntityManager.flush();
    }

    @Test
    public void user_and_user_role_update_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );

        user.addRole(RoleType.ADMIN);
        user = userRepository.save(user);
        testEntityManager.flush();

        user.deleteRole(RoleType.ADMIN);
        testEntityManager.flush();
    }

    @Test
    public void user_and_user_role_delete_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );

        user.addRole(RoleType.ADMIN);
        user = userRepository.save(user);
        testEntityManager.flush();

        userRepository.delete(user);
        testEntityManager.flush();
    }
}
