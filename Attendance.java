package com.company;

import java.util.Date;

public class Attendance {
    private boolean present;
    private String date;
    Student stu;
    Teacher teacher;
    Section sec;
    public Attendance(){}
    public Attendance(Student s, String d,Section se, Teacher t, boolean p)
    {
        stu = s; sec = se; teacher = t; present = p; date = d;
    }
    public void print()
    {
        System.out.println("Course: " + sec.course.getName() + " | Section: " + sec.getSectionName() + " | Date: " + date);
        if(present)
            System.out.println(" | Present\n");
        else
            System.out.println(" | Absent\n");
    }

}
