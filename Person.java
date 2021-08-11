package com.company;

import java.util.Date;

public class Person {
    private String name;
    private String DOB;

    public Person(String n, String d)
    {
        name = n;
        DOB = d;
    }
    public Person()
    {
        name = null;
    }

    public void print()
    {
        System.out.println("Name : " + name);
        System.out.println("Date : " + DOB);
    }


}
