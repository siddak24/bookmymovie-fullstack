package com.bookmymovie.bookmymovie.controller;

import com.bookmymovie.bookmymovie.dto.ShowResponseDTO;
import com.bookmymovie.bookmymovie.entity.Show;
import com.bookmymovie.bookmymovie.repository.ShowRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // 🔹 Get all shows for a movie
    @GetMapping("/movie/{movieId}")
public List<ShowResponseDTO> getShowsByMovie(@PathVariable Long movieId) {

    return showRepository.findByMovieId(movieId)
            .stream()
            .map(show -> new ShowResponseDTO(
                    show.getId(),
                    show.getShowTime()
            ))
            .toList();
}

    // 🔹 Get show by ID
    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));
    }
}