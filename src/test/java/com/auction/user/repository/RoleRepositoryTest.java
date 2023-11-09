package com.auction.user.repository;

import com.auction.AuctionDataJpaTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@AuctionDataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private final RoleRepository roleRepository = null;

    @Autowired
    private final TestEntityManager testEntityManager = null;
    
    @AfterEach
    void delete_all() {
        roleRepository.deleteAll();
    }
}
