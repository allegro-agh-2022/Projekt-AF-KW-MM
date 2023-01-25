package com.auth.users;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(201).body(usersService.createUser(userDto));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(usersService.getUserById(id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
      usersService.deleteUser(id);
      return ResponseEntity.ok().body(true);
    }
}
