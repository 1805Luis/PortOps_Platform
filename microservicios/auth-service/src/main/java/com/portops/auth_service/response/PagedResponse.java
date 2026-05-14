package com.portops.auth_service.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> extends ApiResponse<List<T>> {

    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public PagedResponse(List<T> data, int page, int size, long totalElements, int totalPages ) {
        super(200, "Usuarios recuperados exitosamente", data, LocalDateTime.now());
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
