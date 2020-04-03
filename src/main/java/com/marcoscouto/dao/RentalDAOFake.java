package com.marcoscouto.dao;

import com.marcoscouto.entities.Rental;

import java.util.List;

public class RentalDAOFake implements RentalDAO {
    @Override
    public void save(Rental rental) {

    }

    @Override
    public List<Rental> findRentalPending() {
        return null;
    }
}
