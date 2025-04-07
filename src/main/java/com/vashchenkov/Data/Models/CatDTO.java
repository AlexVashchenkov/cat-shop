package com.vashchenkov.Data.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatDTO {
    private Long id;

    private String name;

    private LocalDate dob;

    private Breed breed;

    private Color color;

    private Long owner_id;

    private List<Cat> friends = new ArrayList<>();

    @Override
    public String toString() {
        return "CatDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", breed=" + breed +
                ", color=" + color +
                ", owner_id=" + owner_id +
                ", friends=" + friends +
                '}';
    }
}
