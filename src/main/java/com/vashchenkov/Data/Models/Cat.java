package com.vashchenkov.Data.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private LocalDate dob;

    private Breed breed;

    private Color color;

    private Long owner_id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cat> friends = new ArrayList<>();

    public void addFriend(Cat cat){
        friends.add(cat);
    }

    @Override
    public String toString() {
        return "Cat{" +
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
