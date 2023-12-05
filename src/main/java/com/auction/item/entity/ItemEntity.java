package com.auction.item.entity;


import com.auction.common.entity.AbstractSystemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "ITEM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID itemId;

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false)
    private boolean isConsumable = false;

    @Column
    private String imageUrl;

    private ItemEntity(String name, boolean isConsumable, String imageUrl) {
        this.name = name;
        this.isConsumable = isConsumable;
        this.imageUrl = imageUrl;
    }

    public boolean isConsumable() {
        return isConsumable;
    }

    public static ItemEntity create(String name, boolean isConsumable, String imageUrl) {
        return new ItemEntity(name, isConsumable, imageUrl);
    }
}
