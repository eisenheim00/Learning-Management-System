package com.company;

import java.util.Date;
import java.util.Vector;

public class Teacher extends Person
{
    private String ID;
    private String Password;
    Vector<evaluation> eva = null;
    Vector<Section> sec = null;
    public Teacher(){super();}
    public Teacher(String n, String d, String i, String p)
    {
        super(n,d); ID = i; Password = p;
    }
    public void addSec(Section c)
    {
        sec.add(c);
    }
    public String getID()
    {
        return ID;
    }
    Vector<Attendance> at;
}
