package com.vashchenkov.Presentation;

import com.vashchenkov.Data.Models.Cat;
import com.vashchenkov.Data.Models.Owner;
import com.vashchenkov.Data.Models.OwnerDTO;
import com.vashchenkov.Infrastructure.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owners")
@AllArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping
    public void add(@RequestBody Owner owner) {
        ownerService.add(owner);
    }

    @GetMapping(path = "all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Owner> list() {
        return ownerService.list();
    }

    @GetMapping(path = "{id}/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
        return ownerService.findOwnerById(id);
    }

    @PutMapping(path = "update_owner")
    @PreAuthorize("isAuthenticated()")
    public void updateOwner(@RequestBody Owner ownerDetails) {
        ownerService.updateOwner(ownerDetails);
    }

    @DeleteMapping(path = "{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        ownerService.deleteById(id);
    }

    @PatchMapping("add_cat")
    @PreAuthorize("isAuthenticated()")
    public void addCat(@RequestBody Cat cat) {
        ownerService.addCat(cat);
    }

    @GetMapping(path = "find_by_name")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Owner findByName(@RequestParam(name = "name", required = false) String name) {
        return ownerService.findByName(name);
    }
}
