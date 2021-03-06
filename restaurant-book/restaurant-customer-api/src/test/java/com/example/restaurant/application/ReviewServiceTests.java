package com.example.restaurant.application;

import com.example.restaurant.domain.Review;
import com.example.restaurant.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


public class ReviewServiceTests {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
       Review review = Review.builder()
               .name("JOKER")
               .score(3)
               .description("Mat-it-da")
               .build();

       reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");

       verify(reviewRepository).save(any());
   }
}