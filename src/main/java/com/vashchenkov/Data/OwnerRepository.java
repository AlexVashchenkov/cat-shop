package com.vashchenkov.Data;

import com.vashchenkov.Data.Models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByName(String name);
}
