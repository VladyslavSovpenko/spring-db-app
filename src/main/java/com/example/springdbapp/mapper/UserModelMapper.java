package com.example.springdbapp.mapper;

import com.example.springdbapp.dto.UserDto;
import com.example.springdbapp.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserModelMapper {

    public UserDto userToDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }
}
