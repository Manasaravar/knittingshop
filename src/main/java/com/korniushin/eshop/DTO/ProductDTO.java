package com.korniushin.eshop.DTO;

import com.korniushin.eshop.model.entities.Product;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private Long id;
    private Integer price;
    private String color;
    private String article;
    private Integer quantity;
    private String unit;
    private String brand;
    private String category;
    private String composition;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.color = product.getColor();
        this.article = product.getArticle();
        this.quantity = product.getQuantity();
        this.unit = product.getUnit();
        this.brand = product.getBrand().getTitle();
        this.category = product.getCategory().getTitle();
        this.composition = product.getComposition().getTitle();
    }

}