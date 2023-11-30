package com.auction.bid.entity;

import com.auction.character.entity.CharacterItemEntity;
import com.auction.common.entity.AbstractSystemEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "BID")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BidEntity extends AbstractSystemEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID bidId;

    @OneToOne
    @JoinColumn(name = "characterItemId", unique = true)
    private CharacterItemEntity characterItem;

    @Column(nullable = false)
    private int startingPrice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime registrationTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "bid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BidDetailEntity> bidDetails = new ArrayList<>();

    public void registerBidDetail(BidDetailEntity bidDetail) {
        bidDetail.changeBid(this);
        bidDetails.add(bidDetail);
    }

    public void cancelBidDetail(BidDetailEntity bidDetail) {
        bidDetail.changeBid(null);
        bidDetails.removeIf(it -> it.getBid().equals(bidDetail.getBid()));
    }


    private BidEntity(UUID bidId, CharacterItemEntity characterItem, LocalDateTime endTime) {
        this.bidId = bidId;
        this.characterItem = characterItem;
        this.registrationTime = LocalDateTime.now();
        this.endTime = endTime;
    }

    public static BidEntity create(UUID bidId, CharacterItemEntity characterItem, LocalDateTime endTime) {
        return new BidEntity(
                bidId,
                characterItem,
                endTime
        );
    }
}
