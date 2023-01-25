package com.auth.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Store createUser(UserDto userDto) {
        User user = new User(userDto);
        usersRepository.save(user);

        return user;
    }

    public Optional<User> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
