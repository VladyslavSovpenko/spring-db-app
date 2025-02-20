package com.example.springdbapp.mapper;

import com.example.springdbapp.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserModelMapper {

    public UserDto userToDto(Map<String, Object> user) {
        UserDto userDto = new UserDto();
        userDto.setId(String.valueOf(user.get("id")));
        userDto.setUsername(String.valueOf(user.get("username")));
        userDto.setName(String.valueOf(user.get("name")));
        userDto.setSurname(String.valueOf(user.get("surname")));
        return userDto;
    }
}
