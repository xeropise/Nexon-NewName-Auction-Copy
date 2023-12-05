package com.auction.item.repository;

import com.auction.item.entity.ItemEntity;
import com.auction.item.entity.QItemEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CustomItemSearchRepositoryImpl implements CustomItemSearchRepository {
    private final JPAQueryFactory jpaQueryFactory;

    QItemEntity item = QItemEntity.itemEntity;

    @Override
    public PageImpl<ItemEntity> findAll(Pageable pageable) {
        List<UUID> itemIds = jpaQueryFactory
                .from(item)
                .select(item.itemId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.createDate.asc())
                .fetch();

        int count = jpaQueryFactory
                .from(item)
                .select(item.count())
                .fetchOne().intValue();

        List<ItemEntity> content = jpaQueryFactory
                .from(item)
                .select(item)
                .where(
                        item.itemId.in(itemIds)
                ).fetch();

        return new PageImpl<ItemEntity>(content, pageable, count);
    }
}
