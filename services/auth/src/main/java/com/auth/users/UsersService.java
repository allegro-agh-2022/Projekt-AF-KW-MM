package com.auth.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final WebClient.Builder webClientBuilder;
    private final UsersRepository usersRepository;

    public User createUser(UserDto userDto) {

        List<ProductDto> products = webClientBuilder.build()
                .post()
                .uri("http://localhost/auth/users",
                        uriBuilder -> uriBuilder.queryParam("ids", idsQueryParam).build())
                .retrieve()
                .bodyToMono(ProductDto.class)
                .collectList()
                .block();

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
