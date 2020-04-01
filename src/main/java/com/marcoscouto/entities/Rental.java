package com.marcoscouto.entities;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Rental {
    private User user;
    private List<Movie> movies;
    private Date initialDate;
    private Date finalDate;
    private Double price;
}
