package com.bookmymovie.bookmymovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookmymovie.bookmymovie.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    @Query("SELECT SUM(b.totalAmount) FROM Booking b WHERE b.show.id = :showId")
    Double getRevenueByShowId(Long showId);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.show.id = :showId")
    Long getBookingCountByShowId(Long showId);
}
