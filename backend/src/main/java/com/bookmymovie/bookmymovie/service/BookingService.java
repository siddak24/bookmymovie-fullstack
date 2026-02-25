package com.bookmymovie.bookmymovie.service;

import com.bookmymovie.bookmymovie.entity.Booking;

public interface BookingService {

    Booking bookSeat(Long seatId);
    
}