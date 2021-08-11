package com.company;

import java.util.Vector;

public class Section
{

    private String ID;
    private int totalSeats;
    private int seatsOccupied;
    Vector<Registration> reg = null;
    Course course;
    Teacher teacher;
    Vector<Attendance> at;
    public Section() {}
    public Section(String i, int t, int s,Course c)
    {
        ID = i;
        totalSeats = t;
        seatsOccupied = s;
        course=c;
    }
    public void setReg(Registration r)
    {
        reg.add(r);
        seatsOccupied++;
    }
    public String getSectionName()
    {
        return ID;
    }
    public int availableSeats()
    {
        return totalSeats-seatsOccupied;
    }
    public boolean cancelReg(Registration r)
    {
        if (reg.remove(r))
        {
            seatsOccupied--;
            return true;
        }
        return false;
    }
    public void print()
    {
        if (totalSeats-seatsOccupied != 0)
        {
            System.out.println( "Section: " + ID + "\n" );
        }
    }
    public void setTeacher(Teacher e)
    {
        teacher = e;
    }
}
