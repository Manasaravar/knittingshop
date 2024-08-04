package com.korniushin.eshop.model.dao.repositories;

import com.korniushin.eshop.model.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository <Lesson,Long> {
}
