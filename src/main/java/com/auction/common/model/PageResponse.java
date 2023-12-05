package com.auction.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PageResponse<T> {
    private Boolean hasNext;
    private long totalCount;
    private List<T> items;
    private int currentCursor;
    private int nextCursor;

    public static PageResponse from(Page page, int currentPage) {
        return new PageResponse(page.hasNext(), page.getTotalElements(), page.getContent(), currentPage, currentPage + 1);
    }
}
