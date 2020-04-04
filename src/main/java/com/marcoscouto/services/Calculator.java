package com.marcoscouto.services;

import com.marcoscouto.exceptions.DivisionNotSupportedException;

public class Calculator {

    public int sum(int a, int b) {
        //System.out.println("Executing method sum...");
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int division(int a, int b) throws DivisionNotSupportedException {
        if(b == 0) throw new DivisionNotSupportedException();
        return a / b;
    }

    public void print(){
        System.out.println("Print Here");
    }
}
