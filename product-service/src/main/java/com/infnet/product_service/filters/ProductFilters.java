package com.infnet.product_service.filters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductFilters {
    private Optional<String> name;
    private Optional<String> authorName;
    private Optional<String> genreName;
}

