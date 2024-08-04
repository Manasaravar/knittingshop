package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "products")
public class Product {
        public static final String SEQ_NAME = "product_seq";
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
        @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
        private Long id;

        @Column(name = "article", unique = true)
        private String article;
        @Column(name = "price")
        private Integer price;
        @Column(name = "color")
        private String color;
        @Column(name = "quantity")
        private Integer quantity;
        @Column(name = "unit")
        private String unit;

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "brand_id")
        private Brand brand;

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "category_id")
        private Category category;

        @ManyToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "composition_id")
        private Composition composition;

        @OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
        //@JoinColumn(name = "order_id")
        private Set<Reviews> reviews;

}
