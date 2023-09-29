package com.auction.user.model.response;

import com.auction.user.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponse {
    private UUID userId;
    private String account;
    private String email;

    public static UserResponse from(UserDto userDto) {
        return new UserResponse(userDto.getUserId(), userDto.getAccount(), userDto.getEmail());
    }
}
