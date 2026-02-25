package com.bookmymovie.bookmymovie.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.bookmymovie.dto.ShowResponseDTO;
import com.bookmymovie.bookmymovie.entity.Show;
import com.bookmymovie.bookmymovie.repository.ShowRepository;

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
    return showRepository.findByMovieIdAndShowTimeAfter(movieId, LocalDateTime.now())
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