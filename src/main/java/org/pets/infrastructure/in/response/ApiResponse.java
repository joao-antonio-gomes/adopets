package org.pets.infrastructure.in.response;


import java.util.List;

public record ApiResponse<T>(List<T> data,
                             PaginationResponse pagination) {

    public ApiResponse(List<T> data, long totalElements, int totalPages, int number, int pageSize) {
        this(data, new PaginationResponse(totalElements, totalPages, number, pageSize));
    }

    public record PaginationResponse(Long totalElements,
                                     int totalPages,
                                     int page,
                                     int pageSize) {
    }
}
