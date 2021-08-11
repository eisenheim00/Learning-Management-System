package com.company;

import java.util.Vector;

public class Semester
{
    private Integer num;
    Vector<Course> courses = null;
    public Semester()
    { }
    public Semester(Integer n)
    {
        num = n;
        //courses = c;
    }
    public void setnum(int n)
    {
        num = n;
    }
    public void addCourse(Course c)
    {
        courses.insertElementAt(c, courses.size());
    }
    public int getnum()
    {
        return num;
    }
    public Vector<Course> getCourses()
    {
        return courses;
    }
}
