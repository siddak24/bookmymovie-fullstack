package com.bookmymovie.bookmymovie.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookmymovie.bookmymovie.entity.*;
import com.bookmymovie.bookmymovie.repository.*;
import com.bookmymovie.bookmymovie.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
    
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;

    public BookingServiceImpl(SeatRepository seatRepository,
                              BookingRepository bookingRepository,
                              UserRepository userRepository) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Booking bookSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.BOOKED) {
            throw new RuntimeException("Seat already booked");
        }
        // 🔐 Get logged-in user
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
        
        User user = (User) authentication.getPrincipal();
        
        System.out.println("Auth name: " + authentication.getName());
        // Mark seat booked
        seat.setStatus(SeatStatus.BOOKED);

        // Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(seat.getShow());
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalAmount(seat.getPrice());

        return bookingRepository.save(booking);
    }
}