package com.korniushin.eshop.model.entities;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "lessons")
public class Lesson {
    public static final String SEQ_NAME = "lesson_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "url_lesson")
    private String urlLesson;


}


