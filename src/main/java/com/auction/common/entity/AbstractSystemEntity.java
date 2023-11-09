package com.auction.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractSystemEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false, name = "insertDatetime")
    public LocalDateTime createDate;

    @LastModifiedDate
    @Column(nullable = false, name = "updateDatetime")
    public LocalDateTime updateDate;
}

