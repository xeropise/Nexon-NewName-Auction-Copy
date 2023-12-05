package com.auction.usercharacter.entity;


import com.auction.common.entity.AbstractSystemEntity;
import com.auction.item.entity.ItemEntity;
import com.auction.usercharacter.exception.UserCharacterItemNotUsableException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "USER_CHARACTER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserCharacterEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userCharacterId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<UserCharacterItemEntity> characterItems = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private UUID userId;

    public void addCharacterItem(ItemEntity item) {
        UserCharacterItemEntity characterItem = UserCharacterItemEntity.create(this, item);
        characterItems.add(characterItem.changeCharacter(this));
    }

    public void useCharacterItem(UserCharacterItemEntity characterItem) {
        if (!characterItem.itemIsUsable()) {
            throw new UserCharacterItemNotUsableException();
        }
        characterItem.useItem();
    }

    public void removeCharacterItem(UserCharacterItemEntity characterItem) {
        characterItem.removeCharacter();
        characterItems.stream().forEach(it -> it.getCharacterItemId().equals(characterItem.getCharacterItemId()));
    }

    private UserCharacterEntity(
            String name,
            UUID userId
    ) {
        this.name = name;
        this.userId = userId;
    }

    public static UserCharacterEntity from(String name, UUID userId) {
        return new UserCharacterEntity(name, userId);
    }

}
