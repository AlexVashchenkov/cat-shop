package com.vashchenkov.Presentation;

import com.vashchenkov.Data.Models.User;
import com.vashchenkov.Infrastructure.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> list() {
        return userService.list();
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(path = "/add")
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @DeleteMapping(path = "{id}/delete")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping(path = "/get")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User findByName(@RequestParam String name) {
        return userService.findByName(name);
    }
}
