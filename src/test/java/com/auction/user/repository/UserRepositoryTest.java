package com.auction.user.repository;

import com.auction.AuctionDataJpaTest;
import com.auction.user.entity.UserEntity;
import com.auction.user.model.type.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@AuctionDataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach
    void delete_all() {
        userRepository.deleteAll();
    }

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

        Optional<UserEntity> optionalUser = userRepository.findWithFetchByUserId(user.getUserId());

        UserEntity fetchedUser = optionalUser.get();

        Assertions.assertThat(user.getUserId()).isEqualTo(fetchedUser.getUserId());
    }

    @Test
    public void user_and_user_role_insert_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );


        user.addRole(roleRepository.findByRoleType(RoleType.USER).get());
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

        user.addRole(roleRepository.findByRoleType(RoleType.ADMIN).get());
        user = userRepository.save(user);
        testEntityManager.flush();

        user.removeRole(roleRepository.findByRoleType(RoleType.ADMIN).get());
        testEntityManager.flush();
    }

    @Test
    public void user_and_user_role_delete_test() {
        UserEntity user = UserEntity.create(
                "xeropise",
                "1234",
                "whrbql1@gmail.com"
        );

        user.addRole(roleRepository.findByRoleType(RoleType.ADMIN).get());
        user = userRepository.save(user);
        testEntityManager.flush();

        userRepository.delete(user);
        testEntityManager.flush();
    }
}
