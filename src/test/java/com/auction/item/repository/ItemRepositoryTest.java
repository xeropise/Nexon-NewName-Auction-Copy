package com.auction.item.repository;

import com.auction.AuctionDataJpaTest;
import com.auction.item.entity.ItemEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

@AuctionDataJpaTest
public class ItemRepositoryTest {
    @Autowired
    private ItemModifyRepository itemModifyRepository;
    @Autowired
    private ItemSearchRepository itemSearchRepository;

    @Test
    @Rollback(false)
    public void item_create_test() {
        itemModifyRepository.save(ItemEntity.create("test", false, ""));
    }

    @Test
    @Rollback(false)
    public void item_search_all_test() {
        int count = 100;
        for (int i = 0; i < count; i++) {
            itemModifyRepository.save(ItemEntity.create("test" + String.valueOf(i), false, ""));
        }

        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        PageImpl<ItemEntity> itemEntityPage = itemSearchRepository.findAll(pageable);

        Assertions.assertEquals(size, itemEntityPage.getSize());
        Assertions.assertTrue(itemEntityPage.hasNext());
    }
}
