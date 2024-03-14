package com.example.halukuyumsal.final_case.dao;

import com.example.halukuyumsal.final_case.entity.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
}