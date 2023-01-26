package com.auth.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@Table(name="users")
@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String client_id; //corresponds to app id from api gateway.
    private String username;  //corresponds to username from api gateway

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User(UserDto userDto) {
        this.name = userDto.name;
        this.surname = userDto.surname;
        this.email = userDto.email;
        this.username = userDto.username;
    }
}
