package com.korniushin.eshop.model.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "reviews")
public class Reviews {
    public static final String SEQ_NAME = "review_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "comment", nullable = false, length = 300)
    private String comment;

    @Column(name = "date-review")
    private String dateTime;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    public Reviews(String comment, User user, Product product) {
        this.comment = comment;
        this.user = user;
        this.product = product;

    }
}
