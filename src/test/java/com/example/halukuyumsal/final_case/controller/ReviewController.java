package com.example.halukuyumsal.final_case.controller;

import com.example.halukuyumsal.final_case.dto.ReviewDTO;
import com.example.halukuyumsal.final_case.enums.EnumComment;
import com.example.halukuyumsal.final_case.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void getAllReviews_ShouldReturnReviews() throws Exception {
        ReviewDTO review1 = new ReviewDTO(1L, EnumComment.BIR, 1L);
        ReviewDTO review2 = new ReviewDTO(2L, EnumComment.DORT, 2L);
        when(reviewService.findAllReviews()).thenReturn(Arrays.asList(review1, review2));

        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].enumComment", is("BIR")))
                .andExpect(jsonPath("$[1].enumComment", is("DORT")));
    }

    @Test
    void getReviewById_WhenReviewExists_ShouldReturnReview() throws Exception {
        ReviewDTO review1 = new ReviewDTO(1L, EnumComment.IKI, 1L);
        when(reviewService.findReviewById(1L)).thenReturn(review1);

        mockMvc.perform(get("/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enumComment", is("IKI")));
    }

    @Test
    void getReviewById_WhenReviewDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(reviewService.findReviewById(1L)).thenReturn(null);

        mockMvc.perform(get("/reviews/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createReview_ShouldCreateReviewAndReturnCreated() throws Exception {
        ReviewDTO newReviewDTO = new ReviewDTO(null, EnumComment.UC, 1L);
        ReviewDTO savedReviewDTO = new ReviewDTO(1L, EnumComment.UC, 1L);

        when(reviewService.saveReview(any(ReviewDTO.class))).thenReturn(savedReviewDTO);

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newReviewDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.enumComment", is(savedReviewDTO.getEnumComment().toString())));
    }
    @Test
    void deleteReview_WhenReviewExists_ShouldDeleteReviewAndReturnNoContent() throws Exception {
        Long reviewId = 1L;
        doNothing().when(reviewService).deleteReview(reviewId);

        mockMvc.perform(delete("/reviews/" + reviewId))
                .andExpect(status().isNoContent());
    }
}