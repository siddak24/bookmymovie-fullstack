package com.bookmymovie.bookmymovie.repository;

import com.bookmymovie.bookmymovie.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByShowId(Long showId);
}