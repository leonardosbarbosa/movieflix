package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private MovieRepository movieRepository;

    public ReviewDTO save(ReviewDTO dto) {
        Movie movie = new Movie();
        movie.setId(dto.getMovieId());
        Review review = new Review();
        review.setMovie(movie);
        review.setText(dto.getText());
        User user = authService.authenticated();
        review.setUser(user);
        review = reviewRepository.save(review);
        return new ReviewDTO(review);
    }

    public List<ReviewDTO> findByMovie(Long movieId) {
        Movie movie = movieRepository.getOne(movieId);
        return reviewRepository.findByMovie(movie).stream().map(ReviewDTO::new).collect(Collectors.toList());
    }
}
