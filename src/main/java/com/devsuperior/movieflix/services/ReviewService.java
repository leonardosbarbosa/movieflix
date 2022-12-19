package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    @Autowired
    private AuthService authService;

    public ReviewDTO save(ReviewDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getMovieId());
        Review review = new Review();
        review.setMovie(movie);
        review.setText(dto.getText());
        User user = authService.authenticated();
        review.setUser(user);
        review = repository.save(review);
        return new ReviewDTO(review);
    }
}
