package com.auction.bid.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "BID_DETAIL")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BidDetailEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private UUID bidDetailId;

    @Column(nullable = false)
    private int bidMoney;

    @ManyToOne
    @JoinColumn(name = "bidId")
    private BidEntity bid;

    public BidEntity getBid() {
        return bid;
    }

    private BidDetailEntity(
            int bidMoney,
            BidEntity bid
    ) {
        this.bidMoney = bidMoney;
        this.bid = bid;
    }

    public void changeBid(BidEntity bid) {
        this.bid = bid;
    }
}
