package com.korniushin.eshop.DTO;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
        private Long id;
        private String title;
        private String urlLesson;
}

