package com.marcoscouto.builders;

import com.marcoscouto.entities.Movie;

public class MovieBuilder {

    private Movie movie;

    private MovieBuilder(){}

    public static MovieBuilder oneMovie(){
        MovieBuilder mb = new MovieBuilder();
        mb.movie = new Movie("Movie 1", 2, 4.0);
        return mb;
    }

    public static MovieBuilder movieWithoutStock(){
        MovieBuilder mb = new MovieBuilder();
        mb.movie = new Movie("Movie 1", 0, 4.0);
        return mb;
    }

    public MovieBuilder noStock(){
        movie.setStock(0);
        return this;
    }

    public MovieBuilder price(Double price){
        movie.setPrice(price);
        return this;
    }

    public Movie now(){
        return movie;
    }
}
