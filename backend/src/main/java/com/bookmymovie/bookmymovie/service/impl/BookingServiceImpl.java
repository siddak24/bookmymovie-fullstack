package com.bookmymovie.bookmymovie.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmymovie.bookmymovie.entity.Booking;
import com.bookmymovie.bookmymovie.entity.Seat;
import com.bookmymovie.bookmymovie.entity.SeatStatus;
import com.bookmymovie.bookmymovie.entity.User;
import com.bookmymovie.bookmymovie.exception.ResourceNotFoundException;
import com.bookmymovie.bookmymovie.repository.BookingRepository;
import com.bookmymovie.bookmymovie.repository.SeatRepository;
import com.bookmymovie.bookmymovie.service.BookingService;

import jakarta.persistence.OptimisticLockException;
@Service
public class BookingServiceImpl implements BookingService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(SeatRepository seatRepository,
                              BookingRepository bookingRepository) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }

   @Override
@Transactional
public Booking bookSeat(Long seatId) {

    try {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

        if (seat.getStatus() == SeatStatus.BOOKED) {
            throw new RuntimeException("Seat already booked!");
        }

        seat.setStatus(SeatStatus.BOOKED);

        Booking booking = Booking.builder()
                .user(user)
                .bookingTime(LocalDateTime.now())
                .totalAmount(seat.getPrice())
                .show(seat.getShow())
                .build();

        bookingRepository.save(booking);

        return booking;

    } catch (OptimisticLockException e) {
        throw new RuntimeException("Seat was booked by another user. Try again.");
    }
}
}