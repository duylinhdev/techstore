package com.linh.techstore.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResult<T> {
    private List<T> list;
    private int currentPage;
    private int totalPages;
    private long totalRecords;
    private List<Integer> navigationPages;
}
