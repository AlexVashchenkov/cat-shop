package com.vashchenkov.Presentation;

import com.vashchenkov.Data.Models.Cat;
import com.vashchenkov.Data.Models.CatDTO;
import org.springframework.stereotype.Component;

@Component
public class CatMapper {
    public CatDTO toCatDTO(Cat cat) {
        return new CatDTO(
                cat.getId(),
                cat.getName(),
                cat.getDob(),
                cat.getBreed(),
                cat.getColor(),
                cat.getOwner_id(),
                cat.getFriends());
    }

    public Cat toCat(CatDTO catDto) {
        Cat cat = new Cat();
        cat.setId(catDto.getId());
        cat.setName(catDto.getName());
        cat.setDob(catDto.getDob());
        cat.setBreed(catDto.getBreed());
        cat.setColor(catDto.getColor());
        cat.setOwner_id(catDto.getOwner_id());
        cat.setFriends(catDto.getFriends());
        return cat;
    }
}
