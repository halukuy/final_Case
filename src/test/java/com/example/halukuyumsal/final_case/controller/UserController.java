package com.example.halukuyumsal.final_case.controller;

import com.example.halukuyumsal.final_case.dto.UserDTO;
import com.example.halukuyumsal.final_case.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getAllUsers_ShouldReturnUsers() {
        UserDTO user1 = new UserDTO(1L, "johnDoe", "John", "Doe", 40L, -74L);
        UserDTO user2 = new UserDTO(2L, "janeDoe", "Jane", "Doe", 41L, -75L);

        when(userService.findAllUsers()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<List<UserDTO>> response = userController.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()).containsExactly(user1, user2);
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        UserDTO user1 = new UserDTO(1L, "johnDoe", "John", "Doe", 40L, -74L);
        when(userService.findUserById(1L)).thenReturn(user1);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user1);
    }

    @Test
    void getUserById_WhenUserDoesNotExist_ShouldReturnNotFound() {
        when(userService.findUserById(1L)).thenReturn(null);

        ResponseEntity<UserDTO> response = userController.getUserById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createUser_ShouldCreateUserAndReturnCreated() {
        UserDTO newUserDTO = new UserDTO(null, "newUser", "New", "User", 42L, -73L);
        UserDTO savedUserDTO = new UserDTO(1L, "newUser", "New", "User", 42L, -73L);

        when(userService.saveUser(any(UserDTO.class))).thenReturn(savedUserDTO);

        ResponseEntity<UserDTO> response = userController.createUser(newUserDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(savedUserDTO);
    }
    @Test
    void updateUser_ShouldUpdateUserAndReturnUpdated() {
        Long userId = 1L;
        UserDTO updateUserDTO = new UserDTO(userId, "updatedUser", "Updated", "User", 43L, -72L);
        UserDTO updatedUserDTO = new UserDTO(userId, "updatedUser", "Updated", "User", 43L, -72L);

        when(userService.saveUser(any(UserDTO.class))).thenReturn(updatedUserDTO);

        ResponseEntity<UserDTO> response = userController.updateUser(userId, updateUserDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedUserDTO);
    }
    @Test
    void deleteUser_ShouldDeleteUserAndReturnNoContent() {
        Long userId = 1L;

        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}