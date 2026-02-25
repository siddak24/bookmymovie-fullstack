package com.bookmymovie.bookmymovie.controller;

import com.bookmymovie.bookmymovie.entity.Seat;
import com.bookmymovie.bookmymovie.repository.SeatRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatRepository seatRepository;

    public SeatController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    // 🔹 Get seats for a show
    @GetMapping("/show/{showId}")
    public List<Seat> getSeatsByShow(@PathVariable Long showId) {
        return seatRepository.findByShowId(showId);
    }

    // 🔹 Get single seat
    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }
}