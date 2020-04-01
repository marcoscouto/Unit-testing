package com.marcoscouto.exceptions;

public class MovieWithoutStockException extends Exception {

    public MovieWithoutStockException(String message){
        super(message);
    }
}
