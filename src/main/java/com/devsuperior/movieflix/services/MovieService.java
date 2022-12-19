package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ReviewService reviewService;

    public MovieDTO findById(Long id) {
        Optional<Movie> movieOp = movieRepository.findById(id);
        Movie entity = movieOp.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new MovieDTO(entity);
    }

    public Page<MovieDTO> findBy(Pageable pageable, Long genreId) {
        Genre genre = genreId == 0L ? null : genreRepository.getOne(genreId);
        Page<Movie> moviesPage = movieRepository.findBy(pageable, genre);
        return moviesPage.map(MovieDTO::new);
    }

    public List<ReviewDTO> findReviews(Long movieId) {
        return reviewService.findByMovie(movieId);
    }

}
