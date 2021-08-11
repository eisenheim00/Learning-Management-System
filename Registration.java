package com.company;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Registration
{
    private static int total = 0;
    private String No;
    private String grade;
    private String start = "10-08-2020";
    private String end = "14-08-2020";
    private String withdraw = "1-12-2020";
    Section sec;
    Course course;
    Student stu;
    Teacher tea;
    Marks mark;
    private boolean isSave = false;
    public Registration()
    {
        total++;
        grade = "I";
    }
    public Registration( Section se, Student st)
    {
        total++;
        sec = se;
        stu = st;
    }
    public void setStu(Student s)
    {
        stu =s;
    }
    public void setsec(Section s)
    {
        sec = s;
    }
    public void setsec(Course s)
    {
        course = s;
    }
    public void setRegno()
    {
        No = String.valueOf(total);
    }

    public Date getstartDate() throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(start);
    }
    public Date getendDate() throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(end);
    }
    public Date getwithdrawDate() throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(withdraw);
    }
    public String getregNo()
    {
        return No;
    }

    public void save() throws SQLException, ClassNotFoundException {
        if (!isSave)
        {
            setRegno();
            Connection con = DBConnection.connect();
            PreparedStatement ps;
            ps = null;

            String sql = "INSERT INTO Registration(Grade, Withdraw_time, Start_time, S_ID, Student_no,No,End_time) VALUES(?,?,?,?,?,?,?) ";
            ps = con.prepareStatement(sql);
            ps.setString(1, grade);
            ps.setString(2, withdraw);
            ps.setString(3, start);
            ps.setString(4,sec.getSectionName() );
            ps.setString(5,stu.getRollNo() );
            ps.setString(6,No );
            ps.setString(7,end);
            ps.setString(8,course.getCourseCode());
            ps.execute();
            System.out.println("Registration Saved!");

            ps.close();
            con.close();
            isSave = true;
        }
    }


    public void print()
    {
        System.out.println("RegNo: " + No + "Course Name: " + sec.course.getName() + "Section: " + sec.getSectionName());
    }



    public void setGrade(String g)
    {
         grade = g;
    }
    public String getGrade()
    {
        return grade;
    }
}
