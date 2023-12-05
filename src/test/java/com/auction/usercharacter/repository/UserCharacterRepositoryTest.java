package com.auction.usercharacter.repository;

import com.auction.AuctionDataJpaTest;
import com.auction.usercharacter.entity.UserCharacterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

@AuctionDataJpaTest
public class UserCharacterRepositoryTest {
    @Autowired
    private UserCharacterModifyRepository userCharacterModifyRepository;

    @Autowired
    private UserCharacterSearchRepository userCharacterSearchRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void character_insert_select_test() {
        UUID userId = UUID.randomUUID();
        String characterName = "전사 이름";

        UserCharacterEntity character = UserCharacterEntity.from(characterName, userId);
        userCharacterModifyRepository.save(character);

        testEntityManager.flush();

        /*
        List<CharacterEntity> characters = characterSearchRepository.findAllByUserId(userId);

        Assertions.assertThat(characters.get(0).getName()).isEqualTo(characterName);
        Assertions.assertThat(characters.get(0).getUserId()).isEqualTo(userId);
         */
    }
}
