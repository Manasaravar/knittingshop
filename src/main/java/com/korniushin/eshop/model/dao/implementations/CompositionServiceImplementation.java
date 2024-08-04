package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.model.dao.interfaces.CompositionService;
import com.korniushin.eshop.model.dao.repositories.CompositionRepository;
import com.korniushin.eshop.model.entities.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CompositionServiceImplementation implements CompositionService {

    final private CompositionRepository compositionRepository;
    @Override
    public List<Composition> all() {
        return compositionRepository.findAll();
    }

    @Override
    public Optional<Composition> findById(Long id) {
        return compositionRepository.findById(id);
    }

    @Override
    public Composition save(Composition composition) {
        if (compositionRepository.findById(composition.getId()).isEmpty()) {
            Composition compositionToSave = Composition.builder()
                    .title("")
                    .products(new HashSet<>())
                    .build();
            compositionToSave.setTitle(composition.getTitle());
            compositionToSave.setProducts(composition.getProducts());
            return compositionRepository.save(composition);
        }
        return null;
    }

    @Override
    public Composition update(Composition composition) {
        Composition compositionToUpdate = compositionRepository.findById(composition.getId()).get();
        compositionToUpdate.setTitle(composition.getTitle());
        compositionToUpdate.setProducts(composition.getProducts());

        return compositionRepository.save(compositionToUpdate);
    }

    @Override
    public boolean deleteById(Long id) {
        if(compositionRepository.findById(id).isPresent()) {
            compositionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
