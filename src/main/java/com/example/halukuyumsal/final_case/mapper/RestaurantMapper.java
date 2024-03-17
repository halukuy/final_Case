package com.example.halukuyumsal.final_case.mapper;

import com.example.halukuyumsal.final_case.dto.RestaurantDTO;
import com.example.halukuyumsal.final_case.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mapping(source = "distance", target = "distance")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    RestaurantDTO restaurantToRestaurantDTO(Restaurant restaurant);

    @Mapping(source = "distance", target = "distance")
    @Mapping(source = "latitude", target = "latitude")
    @Mapping(source = "longitude", target = "longitude")
    Restaurant restaurantDTOToRestaurant(RestaurantDTO restaurantDTO);
}
