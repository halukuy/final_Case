package com.example.halukuyumsal.final_case.dao;

import com.example.halukuyumsal.final_case.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository <Restaurant,Long > {
}
