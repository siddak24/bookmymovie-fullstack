package com.bookmymovie.bookmymovie.service;

public interface AuthService {

    String register(String email, String password);

    String login(String email, String password);
}