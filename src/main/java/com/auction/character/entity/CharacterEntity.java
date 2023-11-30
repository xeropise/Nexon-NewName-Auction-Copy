package com.auction.character.entity;


import com.auction.character.exception.CharacterItemNotUsableException;
import com.auction.item.entity.ItemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "CHARACTER")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CharacterEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID characterId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL)
    private List<CharacterItemEntity> characterItems = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private UUID userId;

    public void addCharacterItem(ItemEntity item) {
        CharacterItemEntity characterItem = CharacterItemEntity.create(this, item);
        characterItems.add(characterItem.changeCharacter(this));
    }

    public void useCharacterItem(CharacterItemEntity characterItem) {
        if (!characterItem.itemIsUsable()) {
            throw new CharacterItemNotUsableException();
        }
        characterItem.useItem();
    }

    public void removeCharacterItem(CharacterItemEntity characterItem) {
        characterItem.removeCharacter();
        characterItems.stream().forEach(it -> it.getCharacterItemId().equals(characterItem.getCharacterItemId()));
    }

}
