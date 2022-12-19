package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public MovieDTO findById(Long id) {
        Optional<Movie> movieOp =  repository.findById(id);
        Movie entity = movieOp.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new MovieDTO(entity);
    }
}
