package com.vashchenkov.Infrastructure;

import com.vashchenkov.Data.CatRepository;
import com.vashchenkov.Data.Models.*;
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
@Transactional
@AllArgsConstructor
public class CatService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;
    private final UserRepository userRepository;

    @Transactional
    public void add(Cat cat) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        owner.addCat(cat);
        cat.setOwner_id(owner.getId());
        catRepository.save(cat);
        ownerRepository.save(owner);
    }

    @Transactional
    public List<Cat> list() {
        return catRepository.findAll();
    }

    @Transactional
    public ResponseEntity<CatDTO> findCatById(Long id) {
        Cat cat = catRepository.findById(id).orElse(null);
        if (cat != null) {
            CatDTO catDTO = new CatDTO(cat.getId(), cat.getName(), cat.getDob(), cat.getBreed(), cat.getColor(), cat.getOwner_id(), cat.getFriends());
            return ResponseEntity.ok(catDTO);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public List<Cat> getCats() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        return owner.getCats();
    }

    @Transactional
    public ResponseEntity<Cat> updateCat(Long id, Cat catDetails) {
        Cat cat = catRepository.findById(id).orElseThrow();

        cat.setName(catDetails.getName());
        cat.setDob(catDetails.getDob());
        cat.setBreed(catDetails.getBreed());
        cat.setColor(catDetails.getColor());
        cat.setOwner_id(catDetails.getOwner_id());
        cat.setFriends(catDetails.getFriends());

        catRepository.save(cat);
        return ResponseEntity.ok(cat);
    }

    @Transactional
    public void deleteById(Long id) {
        catRepository.deleteById(id);
    }

    @Transactional
    public void addFriend(Long cat_id, Long friend_id){
        Cat cat = catRepository.findById(cat_id).orElseThrow();
        Cat friend = catRepository.findById(friend_id).orElseThrow();

        cat.addFriend(friend);
        friend.addFriend(cat);
        catRepository.save(cat);
        catRepository.save(friend);
    }

    @Transactional
    public List<Cat> findAllByColor(Color color) {
        return catRepository.findByColor(color);
    }

    @Transactional
    public List<Cat> findByColor(Color filter_color) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        return owner.getCats().stream().filter(cat -> cat.getColor().equals(filter_color)).toList();
    }

    @Transactional
    public List<Cat> findAllByName(String name) {
        return catRepository.findByName(name);
    }

    @Transactional
    public List<Cat> findByName(String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        return owner.getCats().stream().filter(cat -> cat.getName().equals(name)).toList();
    }

    @Transactional
    public List<Cat> findAllByBreed(Breed breed) {
        return catRepository.findByBreed(breed);
    }

    @Transactional
    public List<Cat> findByBreed(Breed breed) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logged_user = userRepository.findByUsername(auth.getName()).orElseThrow();
        Owner owner = ownerRepository.findByName(logged_user.getReal_name());

        return owner.getCats().stream().filter(cat -> cat.getBreed().equals(breed)).toList();
    }
}
