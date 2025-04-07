package com.vashchenkov.Presentation;

import com.vashchenkov.Data.Models.Breed;
import com.vashchenkov.Data.Models.Cat;
import com.vashchenkov.Data.Models.CatDTO;
import com.vashchenkov.Data.Models.Color;
import com.vashchenkov.Infrastructure.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cats")
@AllArgsConstructor
public class CatController {
    private final CatService catService;
    private final CatMapper catMapper;

    @PostMapping(path = "/create")
    @PreAuthorize("isAuthenticated()")
    public void add(@RequestBody Cat cat) {
        catService.add(cat);
    }

    @GetMapping(path = "/read_all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CatDTO> list() {
        return catService.list().stream().map(catMapper::toCatDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "my_cats")
    @PreAuthorize("isAuthenticated()")
    public List<CatDTO> getCats() {
        return catService.getCats().stream().map(catMapper::toCatDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CatDTO> findById(@PathVariable Long id) {
        return catService.findCatById(id);
    }


    @GetMapping(path = "{id}/getFriends")
    @PreAuthorize("isAuthenticated()")
    public List<CatDTO> getFriends(@PathVariable Long id) {
        return catService.findCatById(id).getBody().getFriends().stream().map(catMapper::toCatDTO).collect(Collectors.toList());
    }

    @PutMapping("{id}/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Cat> updateCat(@RequestParam Long id, @RequestBody Cat catDetails) {
        return catService.updateCat(id, catDetails);
    }

    @DeleteMapping(path = "{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public void delete(@PathVariable Long id) {
        catService.deleteById(id);
    }

    @PutMapping(path = "add_friend")
    @PreAuthorize("isAuthenticated()")
    public void addFriend(@RequestParam Long cat_id, @RequestParam Long friend_id){
        catService.addFriend(cat_id, friend_id);
    }

    @GetMapping(path = "/getAllByColor")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CatDTO> findAllByColor(@RequestParam Color color) {
        return catService.findAllByColor(color).stream().map(catMapper::toCatDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/getByColor")
    public List<CatDTO> findByColor(@RequestParam Color color) {
        return catService.findByColor(color).stream().map(catMapper::toCatDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/getAllByName")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Cat> findAllByName(@RequestParam String name) {
        return catService.findAllByName(name);
    }

    @GetMapping(path = "/getByName")
    public List<Cat> findByName(@RequestParam String name) {
        return catService.findByName(name);
    }


    @GetMapping(path = "/getAllByBreed")
    public List<Cat> findAllByBreed(@RequestParam Breed breed) {
        return catService.findAllByBreed(breed);
    }

    @GetMapping(path = "/getByBreed")
    public List<Cat> findByBreed(@RequestParam Breed breed) {
        return catService.findByBreed(breed);
    }
}
