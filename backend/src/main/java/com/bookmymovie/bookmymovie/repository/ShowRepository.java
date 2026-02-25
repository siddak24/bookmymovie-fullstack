package com.bookmymovie.bookmymovie.repository;

import com.bookmymovie.bookmymovie.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieId(Long movieId);
}