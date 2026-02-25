package com.bookmymovie.bookmymovie.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmymovie.bookmymovie.entity.Show;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieId(Long movieId);
    List<Show> findByMovieIdAndShowTimeAfter(Long movieId, LocalDateTime time);
}