package com.marcoscouto.dao;

import com.marcoscouto.entities.Rental;

import java.util.List;

public interface RentalDAO {

    void save(Rental rental);

    List<Rental> findRentalPending();
}
