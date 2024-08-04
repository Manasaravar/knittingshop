package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.LessonService;
import com.korniushin.eshop.model.dao.repositories.LessonRepository;
import com.korniushin.eshop.model.entities.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class lessonServiceImplementation implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public List<Lesson> all() {
        return lessonRepository.findAll();
    }

}
