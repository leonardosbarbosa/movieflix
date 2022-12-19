package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {
    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> find(
            Pageable pageable,
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId) {

        return ResponseEntity.ok(service.findBy(pageable, genreId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findMovieReviews (@PathVariable Long id) {
        return ResponseEntity.ok(service.findReviews(id));
    }
}
