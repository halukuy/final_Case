package com.example.halukuyumsal.final_case.service;

import com.example.halukuyumsal.final_case.dao.ReviewRepository;
import com.example.halukuyumsal.final_case.dao.UserRepository;
import com.example.halukuyumsal.final_case.dto.ReviewDTO;
import com.example.halukuyumsal.final_case.entity.Review;
import com.example.halukuyumsal.final_case.exceptions.ResourceNotFoundException;
import com.example.halukuyumsal.final_case.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewDTO> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new ResourceNotFoundException("No reviews found");
        }
        return reviews.stream()
                .map(ReviewMapper.INSTANCE::reviewToReviewDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO findReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(ReviewMapper.INSTANCE::reviewToReviewDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        if (userRepository.findById(reviewDTO.getUserId()).isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + reviewDTO.getUserId());
        }
        Review review = ReviewMapper.INSTANCE.reviewDTOToReview(reviewDTO);
        userRepository.findById(reviewDTO.getUserId()).ifPresent(review::setUser);
        review = reviewRepository.save(review);
        return ReviewMapper.INSTANCE.reviewToReviewDTO(review);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
