package com.bookmymovie.bookmymovie.dto;

import java.time.LocalDateTime;

public class ShowResponseDTO {

    private Long id;
    private LocalDateTime showTime;

    public ShowResponseDTO(Long id, LocalDateTime showTime) {
        this.id = id;
        this.showTime = showTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }
}