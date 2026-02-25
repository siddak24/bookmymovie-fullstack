package com.bookmymovie.bookmymovie.dto;

import java.time.LocalDateTime;

public class BookingResponseDTO {

    private Long id;
    private String userName;
    private LocalDateTime bookingTime;
    private Double totalAmount;
    private Long showId;

    public BookingResponseDTO(Long id,
                              String userName,
                              LocalDateTime bookingTime,
                              Double totalAmount,
                              Long showId) {
        this.id = id;
        this.userName = userName;
        this.bookingTime = bookingTime;
        this.totalAmount = totalAmount;
        this.showId = showId;
    }

    public Long getId() { return id; }
    public String getUserName() { return userName; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public Double getTotalAmount() { return totalAmount; }
    public Long getShowId() { return showId; }
}