package com.vashchenkov.Data.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long id;

    private String name;

    private LocalDate dob;

    private List<Cat> cats = new ArrayList<>();
}
