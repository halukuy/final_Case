package com.example.halukuyumsal.final_case.service;

import com.example.halukuyumsal.final_case.entity.Restaurant;
import com.example.halukuyumsal.final_case.exceptions.ResourceNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private SolrTemplate solrTemplate;

    public Restaurant saveRestaurant(Restaurant restaurant) {
        try {
            solrTemplate.saveBean("restaurants", restaurant);
            solrTemplate.commit("restaurants");
            return restaurant;
        } catch (RuntimeException e) {
            throw new ServiceException("Error saving restaurant to Solr", e);
        }
    }

    public Optional<Restaurant> getRestaurant(String id) {
        try {
            return Optional.of(solrTemplate.getById("restaurants", id, Restaurant.class))
                    .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
        } catch (RuntimeException e) {
            throw new ServiceException("Error retrieving restaurant from Solr", e);
        }
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
        if (getRestaurant(restaurant.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + restaurant.getId());
        }
        try {
            solrTemplate.saveBean("restaurants", restaurant);
            solrTemplate.commit("restaurants");
            return restaurant;
        } catch (RuntimeException e) {
            throw new ServiceException("Error updating restaurant in Solr", e);
        }
    }

    public void deleteRestaurant(String id) {
        if (getRestaurant(id).isEmpty()) {
            throw new ResourceNotFoundException("Restaurant not found with id: " + id);
        }
        try {
            solrTemplate.deleteByIds("restaurants", id);
            solrTemplate.commit("restaurants");
        } catch (RuntimeException e) {
            throw new ServiceException("Error deleting restaurant from Solr", e);
        }
    }

    public List<Restaurant> recommendRestaurants(double userLat, double userLon) {
        try {
            List<Restaurant> allRestaurants = solrTemplate.queryForPage("restaurants", new SimpleQuery("*:*"), Restaurant.class).getContent();
            return allRestaurants.stream()
                    .filter(restaurant -> calculateDistance(userLat, userLon, restaurant.getLatitude(), restaurant.getLongitude()) <= 10)
                    .sorted(Comparator.comparingDouble((Restaurant r) -> calculateScore(r.getRating(), calculateDistance(userLat, userLon, r.getLatitude(), r.getLongitude()))).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new ServiceException("Error retrieving restaurants from Solr for recommendation", e);
        }
    }

    private double calculateScore(double rating, double distance) {
        double distanceScore = Math.max(0, (10 - distance) / 10);
        return (rating * 0.7) + (distanceScore * 0.3);
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
