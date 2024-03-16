package com.example.halukuyumsal.final_case.controller;

import com.example.halukuyumsal.final_case.entity.Restaurant;
import com.example.halukuyumsal.final_case.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId("1");
        restaurant.setName("Test Restaurant");
        restaurant.setLatitude(40.7128);
        restaurant.setLongitude(-74.0060);
        restaurant.setRating(4.5);
    }

    @Test
    void createRestaurant_ShouldReturnCreatedRestaurant() throws Exception {
        when(restaurantService.saveRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(post("/api/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Restaurant\", \"address\": \"123 Main St\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));

        verify(restaurantService, times(1)).saveRestaurant(any(Restaurant.class));
    }

    @Test
    void getRestaurant_WhenFound_ShouldReturnRestaurant() throws Exception {
        when(restaurantService.getRestaurant("1")).thenReturn(Optional.of(restaurant));

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(restaurantService, times(1)).getRestaurant("1");
    }

    @Test
    void updateRestaurant_ShouldReturnUpdatedRestaurant() throws Exception {
        when(restaurantService.updateRestaurant(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(put("/api/restaurants/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Test Restaurant\", \"address\": \"123 Main St\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(restaurantService, times(1)).updateRestaurant(any(Restaurant.class));
    }

    @Test
    void deleteRestaurant_ShouldReturnNoContent() throws Exception {
        doNothing().when(restaurantService).deleteRestaurant("1");

        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isNoContent());

        verify(restaurantService, times(1)).deleteRestaurant("1");
    }

    @Test
    void getRecommendations_ShouldReturnListOfRestaurants() throws Exception {
        when(restaurantService.recommendRestaurants(anyDouble(), anyDouble())).thenReturn(List.of(restaurant));

        mockMvc.perform(get("/api/restaurants/recommendations?userLat=0.0&userLon=0.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));

        verify(restaurantService, times(1)).recommendRestaurants(anyDouble(), anyDouble());
    }
}