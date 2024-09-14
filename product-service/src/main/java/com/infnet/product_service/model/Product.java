package com.infnet.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String genreName;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String description;


    private Integer genreId;
    private Integer authorId;

    public String generateOrderRegistryKey() {
        return String.format("%d-%s%s%s-%d-%d",
                this.id,
                this.name != null ? this.name.charAt(0) : "N",
                this.genreName != null ? this.genreName.charAt(0) : "G",
                this.authorName != null ? this.authorName.charAt(0) : "A",
                this.genreId,
                this.authorId
        );
    }
}

