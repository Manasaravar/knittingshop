package com.korniushin.eshop.model.dao.repositories;


import com.korniushin.eshop.model.entities.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {
}


