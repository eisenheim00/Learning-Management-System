package com.company;

import java.util.Vector;

public class Course
{

    private int credit_hr;
    private String name;
    private String code;
    Semester sem;
    Vector<Section> sections = null;
    Vector<evaluation> eva;
    public Course()
    {}
    public Course(int c, String n, String co, Semester s)
    {
        credit_hr = c;
        name = n;
        code = co;
        sem = s;

    }
    public Vector<Section> getSec()
    {
        return sections;
    }
    public void addSection(Section s)
    {
        sections.insertElementAt(s, sections.size());
    }
    public void setSem(Semester s)
    {
        sem =s;
    }
    public void setEva(Vector<evaluation> s)
    {
        eva = s;
    }
    public Semester getSem()
    {
        return sem;
    }
    public String getName()
    {
        return name;
    }
    public String getCourseCode()
    {
        return code;
    }
    public int getCreditHour()
    {
        return credit_hr;
    }

    public void print()
    {
        System.out.println("Code: " + code + " Name: " + name + "Credit Hour: " + credit_hr);
    }


}
