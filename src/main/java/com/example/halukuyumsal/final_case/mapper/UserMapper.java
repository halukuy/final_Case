package com.example.halukuyumsal.final_case.mapper;

import com.example.halukuyumsal.final_case.dto.UserDTO;
import com.example.halukuyumsal.final_case.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
