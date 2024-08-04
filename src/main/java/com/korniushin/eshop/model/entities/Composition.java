package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@Builder
@Entity

@Table(name = "compositions")
public class Composition {
    public static final String SEQ_NAME = "composition_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany (mappedBy = "composition")
    private Set<Product> products;

    public Composition() {products = new HashSet<>();}
}




