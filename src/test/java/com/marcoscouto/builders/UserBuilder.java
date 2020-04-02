package com.marcoscouto.builders;

import com.marcoscouto.entities.User;

public class UserBuilder {

    private User user;

    private UserBuilder(){

    }

    public static UserBuilder oneUser(){
        UserBuilder ub = new UserBuilder();
        ub.user = new User("Marcos");
        return ub;
    }

    public User now(){
        return user;
    }

}
