package com.bookmymovie.bookmymovie.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.bookmymovie.entity.Movie;
import com.bookmymovie.bookmymovie.entity.Seat;
import com.bookmymovie.bookmymovie.entity.SeatStatus;
import com.bookmymovie.bookmymovie.entity.Show;
import com.bookmymovie.bookmymovie.repository.MovieRepository;
import com.bookmymovie.bookmymovie.repository.SeatRepository;
import com.bookmymovie.bookmymovie.repository.ShowRepository;
import com.bookmymovie.bookmymovie.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    // 🎬 Create Movie
    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }
    @GetMapping("/revenue/{showId}")
    public Map<String, Object> getRevenue(@PathVariable Long showId) {

        Double revenue = bookingRepository.getRevenueByShowId(showId);
        Long bookings = bookingRepository.getBookingCountByShowId(showId);

        Map<String, Object> response = new HashMap<>();
        response.put("totalRevenue", revenue == null ? 0 : revenue);
        response.put("totalBookings", bookings);

        return response;
    }

    // 🎭 Create Show
   @PostMapping("/show/{movieId}")
public Show createShow(@PathVariable Long movieId,
                       @RequestBody Map<String, String> body) {

    Movie movie = movieRepository.findById(movieId)
            .orElseThrow();

    LocalDateTime showTime = LocalDateTime.parse(body.get("showTime"));

    Show show = new Show();
    show.setMovie(movie);
    show.setShowTime(showTime);

    Show savedShow = showRepository.save(show);

    // 🔥 Auto-generate seats
    for (char row = 'A'; row <= 'E'; row++) {
        for (int i = 1; i <= 10; i++) {

            Seat seat = new Seat();
            seat.setSeatNumber(row + String.valueOf(i));
            seat.setPrice(500.0);
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setShow(savedShow);

            seatRepository.save(seat);
        }
        
    }

    return savedShow;
}

    // 🎟 Generate Seats Automatically
    @PostMapping("/generate-seats/{showId}")
    public String generateSeats(@PathVariable Long showId) {

        Show show = showRepository.findById(showId)
                .orElseThrow();

        for (char row = 'A'; row <= 'E'; row++) {
            for (int i = 1; i <= 10; i++) {

                Seat seat = new Seat();
                seat.setSeatNumber(row + String.valueOf(i));
                seat.setPrice(500.0);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setShow(show);

                seatRepository.save(seat);
            }
        }
        

        return "Seats generated successfully!";
    }
}