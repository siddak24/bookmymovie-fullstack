package com.bookmymovie.bookmymovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmymovie.bookmymovie.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}