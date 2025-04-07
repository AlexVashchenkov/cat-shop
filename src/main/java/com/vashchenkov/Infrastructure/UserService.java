package com.vashchenkov.Infrastructure;

import com.vashchenkov.Data.Models.Owner;
import com.vashchenkov.Data.Models.User;
import com.vashchenkov.Data.OwnerRepository;
import com.vashchenkov.Data.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final OwnerRepository ownerRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        Owner owner = new Owner();
        owner.setName(user.getReal_name());
        owner.setDob(user.getDob());

        ownerRepository.save(owner);
    }

    @Transactional
    public List<User> list() {
        return userRepository.findAll();
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Owner owner = ownerRepository.findByName(user.getReal_name());

        userRepository.delete(user);
        ownerRepository.delete(owner);
    }

    @Transactional
    public User findByName(String name) {
        return userRepository.findByUsername(name).orElseThrow();
    }
}
