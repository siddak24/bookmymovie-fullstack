package com.bookmymovie.bookmymovie.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.bookmymovie.dto.BookingResponseDTO;
import com.bookmymovie.bookmymovie.entity.Booking;
import com.bookmymovie.bookmymovie.entity.User;
import com.bookmymovie.bookmymovie.repository.BookingRepository;
import com.bookmymovie.bookmymovie.service.BookingService;
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }
    @GetMapping
    public List<BookingResponseDTO> getMyBookings() {

        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return bookingRepository.findByUserId(user.getId())
                .stream()
                .map(booking -> new BookingResponseDTO(
                        booking.getId(),
                        booking.getUser().getEmail(),
                        booking.getBookingTime(),
                        booking.getTotalAmount(),
                        booking.getShow().getId()
                ))
                .toList();
    }
    @PostMapping("/{seatId}")
    public Booking bookSeat(@PathVariable Long seatId) {
            return bookingService.bookSeat(seatId);
        }
    }