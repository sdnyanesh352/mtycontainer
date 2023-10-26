package com.universal.containx.model;



import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.universal.containx.dao.UserDto;

@Component
public class UserMapper {
    public UserDto toUserDto(User user) {
        return new UserDto()
            .setEmail(user.getEmail())
            .setFirstName(user.getFirstName())
            .setLastName(user.getLastName())
            .setMobileNumber(user.getMobileNumber())
            .setUsername(user.getUsername()) // Add this line to set the username
            .setCoins(user.getCoins());
    }
 
    public User toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setUsername(userDto.getUsername().toLowerCase());
        user.setCoins(userDto.getCoins());
        return user;
    }
}
