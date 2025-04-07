package com.vashchenkov.Data.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "dob")
    private LocalDate dob;

    @OneToMany
    private List<Cat> cats = new ArrayList<>();

    public void addCat(Cat cat){
        cat.setOwner_id(id);
        cats.add(cat);
    }
}