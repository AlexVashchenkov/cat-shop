package com.vashchenkov.Data;

import com.vashchenkov.Data.Models.Breed;
import com.vashchenkov.Data.Models.Cat;
import com.vashchenkov.Data.Models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findByColor(Color color);

    List<Cat> findByName(String name);

    List<Cat> findByBreed(Breed breed);
}
