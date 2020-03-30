package com.marcoscouto.entities;

import lombok.Data;
import java.util.Date;

@Data
public class Rental {
    private User user;
    private Movie movie;
    private Date initialDate;
    private Date finalDate;
    private Double price;
}
