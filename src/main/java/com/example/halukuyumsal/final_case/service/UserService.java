package com.example.halukuyumsal.final_case.service;

import com.example.halukuyumsal.final_case.dao.UserRepository;
import com.example.halukuyumsal.final_case.dto.UserDTO;
import com.example.halukuyumsal.final_case.entity.User;
import com.example.halukuyumsal.final_case.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElse(null);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
