package com.bookmymovie.bookmymovie.controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookmymovie.bookmymovie.dto.MovieRequestDTO;
import com.bookmymovie.bookmymovie.dto.MovieResponseDTO;
import com.bookmymovie.bookmymovie.service.MovieService;
import org.springframework.data.domain.Page;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public MovieResponseDTO createMovie(@Valid @RequestBody MovieRequestDTO request) {
        return movieService.createMovie(request);
    }

    @GetMapping
    public Page<MovieResponseDTO> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return movieService.getAllMovies(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public MovieResponseDTO getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    public MovieResponseDTO updateMovie(@PathVariable Long id,
                                        @Valid @RequestBody MovieRequestDTO request) {
        return movieService.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "Movie deleted successfully";
    }
}