package com.auction.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractSystemEntity {
    @Column(nullable = false, updatable = false, name = "insertDatetime")
    public ZonedDateTime createDate = ZonedDateTime.now();

    @Column(nullable = false, name = "updateDatetime")
    public ZonedDateTime updateDate = ZonedDateTime.now();

    @PrePersist
    void prePersiste() {
        createDate = ZonedDateTime.now();
        updateDate = ZonedDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        updateDate = ZonedDateTime.now();
    }
}

