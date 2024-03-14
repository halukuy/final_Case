package com.example.halukuyumsal.final_case.mapper;

import com.example.halukuyumsal.final_case.dto.ReviewDTO;
import com.example.halukuyumsal.final_case.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "enumComment", target = "enumComment")
    })
    ReviewDTO reviewToReviewDTO(Review review);

    @Mappings({
            @Mapping(target = "user", ignore = true)
    })
    Review reviewDTOToReview(ReviewDTO reviewDTO);
}
