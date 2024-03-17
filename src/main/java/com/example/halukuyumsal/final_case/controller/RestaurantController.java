package com.example.halukuyumsal.final_case.controller;

import com.example.halukuyumsal.final_case.dto.RestaurantDTO;
import com.example.halukuyumsal.final_case.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO savedRestaurantDTO = restaurantService.saveRestaurant(restaurantDTO);
        return new ResponseEntity<>(savedRestaurantDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurant(@PathVariable String id) {
        Optional<RestaurantDTO> restaurantDTO = restaurantService.getRestaurant(id);
        return restaurantDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable String id, @RequestBody RestaurantDTO restaurantDTO) {
        restaurantDTO.setId(id);
        RestaurantDTO updatedRestaurantDTO = restaurantService.updateRestaurant(restaurantDTO);
        return new ResponseEntity<>(updatedRestaurantDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<RestaurantDTO>> getRecommendations(@RequestParam double userLat, @RequestParam double userLon) {
        List<RestaurantDTO> recommendations = restaurantService.recommendRestaurants(userLat, userLon);
        return new ResponseEntity<>(recommendations, HttpStatus.OK);
    }
}