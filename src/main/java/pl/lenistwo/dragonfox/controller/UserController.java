package pl.lenistwo.dragonfox.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.lenistwo.dragonfox.document.User;
import pl.lenistwo.dragonfox.exceptions.UserNotFoundException;
import pl.lenistwo.dragonfox.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority(T(pl.lenistwo.dragonfox.document.Role).ADMIN.toString(), T(pl.lenistwo.dragonfox.document.Role).MODERATOR.toString())")
    public Flux<User> getAllUsers() {
        return Flux.fromIterable(userRepository.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(T(pl.lenistwo.dragonfox.document.Role).ADMIN.toString())")
    public Mono<User> getUserById(@PathVariable String id) {
        return Mono.just(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping
    @PreAuthorize("hasAuthority(T(pl.lenistwo.dragonfox.document.Role).ADMIN.toString())")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @PreAuthorize("hasAuthority(T(pl.lenistwo.dragonfox.document.Role).ADMIN.toString())")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
