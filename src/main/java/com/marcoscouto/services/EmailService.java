package com.marcoscouto.services;

import com.marcoscouto.entities.User;

public interface EmailService {

    void notifyDelay(User user);
}
