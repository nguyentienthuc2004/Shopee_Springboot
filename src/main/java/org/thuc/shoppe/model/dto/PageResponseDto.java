package org.thuc.shoppe.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDto<T> {
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private T content;
}
