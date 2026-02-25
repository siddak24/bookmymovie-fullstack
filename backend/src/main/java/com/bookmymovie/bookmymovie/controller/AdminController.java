package com.bookmymovie.bookmymovie.controller;

import com.bookmymovie.bookmymovie.entity.*;
import com.bookmymovie.bookmymovie.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;

    public AdminController(UserRepository userRepository,
                           BookingRepository bookingRepository,
                           SeatRepository seatRepository,
                           ShowRepository showRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/seats")
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    @GetMapping("/shows")
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }
}