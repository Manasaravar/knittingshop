package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.dao.repositories.LessonRepository;
import com.korniushin.eshop.model.entities.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> all();
}
