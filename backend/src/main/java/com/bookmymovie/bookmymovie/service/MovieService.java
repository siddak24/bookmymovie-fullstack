package com.bookmymovie.bookmymovie.service;

import org.springframework.data.domain.Page;


import com.bookmymovie.bookmymovie.dto.MovieRequestDTO;
import com.bookmymovie.bookmymovie.dto.MovieResponseDTO;

public interface MovieService {

    MovieResponseDTO createMovie(MovieRequestDTO request);

    Page<MovieResponseDTO> getAllMovies(int page, int size, String sortBy);

    MovieResponseDTO getMovieById(Long id);

    MovieResponseDTO updateMovie(Long id, MovieRequestDTO request);

    void deleteMovie(Long id);
}