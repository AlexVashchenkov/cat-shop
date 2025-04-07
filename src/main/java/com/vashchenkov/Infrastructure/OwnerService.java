package com.vashchenkov.Infrastructure;

import com.vashchenkov.Data.CatRepository;
import com.vashchenkov.Data.Models.Cat;
import com.vashchenkov.Data.Models.Owner;
import com.vashchenkov.Data.Models.OwnerDTO;
import com.vashchenkov.Data.Models.User;
import com.vashchenkov.Data.OwnerRepository;
import com.vashchenkov.Data.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OwnerService {
    private final UserRepository userRepository;

    private final OwnerRepository ownerRepository;

    private final CatRepository catRepository;

    @Transactional
    public void add(Owner owner) {
        ownerRepository.save(owner);
    }

    @Transactional
    public List<Owner> list() {
        return ownerRepository.findAll();
    }

    @Transactional
    public ResponseEntity<OwnerDTO> findOwnerById(Long id) {
        Owner owner = ownerRepository.findById(id).orElse(null);

        if (owner != null) {
            OwnerDTO ownerDTO = new OwnerDTO(owner.getId(), owner.getName(), owner.getDob(), owner.getCats());
            return ResponseEntity.ok(ownerDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    public ResponseEntity<Owner> findLoggedOwner() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void updateOwner(Owner ownerDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        logged_user.setDob(ownerDetails.getDob());
        logged_user.setReal_name(ownerDetails.getName());

        owner.setName(ownerDetails.getName());
        owner.setDob(ownerDetails.getDob());
        owner.setCats(ownerDetails.getCats());

        ownerRepository.save(owner);
        userRepository.save(logged_user);
    }

    @Transactional
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Transactional
    public void addCat(Cat cat) {
        Owner logged_owner = findLoggedOwner().getBody();

        logged_owner.addCat(cat);
        ownerRepository.save(logged_owner);
        catRepository.save(cat);
    }

    @Transactional
    public Owner findByName(String name) {
        return ownerRepository.findByName(name);
    }
}
