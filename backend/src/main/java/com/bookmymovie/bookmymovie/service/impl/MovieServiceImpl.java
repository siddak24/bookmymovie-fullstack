package com.bookmymovie.bookmymovie.service.impl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bookmymovie.bookmymovie.dto.MovieRequestDTO;
import com.bookmymovie.bookmymovie.dto.MovieResponseDTO;
import com.bookmymovie.bookmymovie.entity.Movie;
import com.bookmymovie.bookmymovie.exception.ResourceNotFoundException;
import com.bookmymovie.bookmymovie.repository.MovieRepository;
import com.bookmymovie.bookmymovie.service.MovieService;
import org.springframework.data.domain.*;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieResponseDTO createMovie(MovieRequestDTO request) {
        Movie movie = mapToEntity(request);
        Movie saved = movieRepository.save(movie);
        return mapToResponse(saved);
    }

    @Override
        public Page<MovieResponseDTO> getAllMovies(int page, int size, String sortBy) {

            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

            Page<Movie> moviePage = movieRepository.findAll(pageable);

            return moviePage.map(this::mapToResponse);
        }

    @Override
    public MovieResponseDTO getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
        return mapToResponse(movie);
    }

    @Override
    public MovieResponseDTO updateMovie(Long id, MovieRequestDTO request) {
        Movie existing = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setGenre(request.getGenre());
        existing.setLanguage(request.getLanguage());
        existing.setDuration(request.getDuration());
        existing.setReleaseDate(request.getReleaseDate());

        Movie updated = movieRepository.save(existing);
        return mapToResponse(updated);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    private Movie mapToEntity(MovieRequestDTO request) {
        return Movie.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .genre(request.getGenre())
                .language(request.getLanguage())
                .duration(request.getDuration())
                .releaseDate(request.getReleaseDate())
                .build();
    }

    private MovieResponseDTO mapToResponse(Movie movie) {
        return MovieResponseDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .genre(movie.getGenre())
                .language(movie.getLanguage())
                .duration(movie.getDuration())
                .releaseDate(movie.getReleaseDate())
                .build();
    }
}