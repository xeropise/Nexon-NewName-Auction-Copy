package com.auction.usercharacter.entity;

import com.auction.common.entity.AbstractSystemEntity;
import com.auction.item.entity.ItemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "CHARACTER_ITEM")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCharacterItemEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID characterItemId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userCharacterId", updatable = false)
    private UserCharacterEntity character;

    @ManyToOne(optional = false)
    @JoinColumn(name = "itemId", updatable = false)
    private ItemEntity item;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private boolean onBidding;


    public UUID getCharacterItemId() {
        return characterItemId;
    }

    public UserCharacterItemEntity changeCharacter(UserCharacterEntity character) {
        this.character = character;
        return this;
    }

    public UserCharacterItemEntity removeCharacter() {
        this.character = null;
        return this;
    }

    /**
     * @author xeropise
     * item이 소모가능하고, 1개 이상 존재해야 하며, 입찰 리스트에 올라가 있지 않아야 소모 가능
     */
    public boolean itemIsUsable() {
        return item.isConsumable() && count > 0 && !onBidding;
    }

    public void useItem() {
        count--;
    }

    private UserCharacterItemEntity(UserCharacterEntity character, ItemEntity item) {
        this.character = character;
        this.item = item;
    }

    public static UserCharacterItemEntity create(UserCharacterEntity character, ItemEntity item) {
        return new UserCharacterItemEntity(character, item);
    }
}
