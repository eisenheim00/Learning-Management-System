package com.company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;


public class Main {

    Vector<Course> courses = null;
    Vector<Section> Sec = null;
    Vector<Semester> Sem = null;
    Vector<Teacher> teacher = null;


    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        System.out.println("Press s/S for STUDENT\nPress t/T for Teacher:\n");
        Scanner Usr = new Scanner(System.in);
        String user = Usr.nextLine();

        Person p = createStu();//create student

        System.out.println("\t\t\tFLEX\n\n1) --> Registration\n2) --> Attendance\n3) --> Marks\n4) --> Transcript\n\n\nMaKe your choice : ");
        String opt = Usr.nextLine();

        if (opt=="1")
        {

        }


    }

    private void readAllTeachers() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT * FROM Teacher";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        System.out.println("ALL Teachers\n");

        while (rs.next()) {
            String n = rs.getString("Name");
            String d = rs.getString("DOB");
            String i = rs.getString("ID");
            String p = rs.getString("Password");
            String c = rs.getString("Course_Code");
            String s = rs.getString("Section");


            Teacher te = new Teacher(n, d, i, p);
            int x = 0;
            int y = 0;
            boolean f1 = false, f2 = false;
            for (x = 0; x < courses.size(); x++) {
                if (courses.get(x).getCourseCode() == c) {
                    for (y = 0; y < courses.get(x).sections.size(); y++) {
                        if (courses.get(x).sections.get(y).getSectionName() == s) {
                            te.addSec(courses.get(x).sections.get(y));
                            f1 = true;
                            break;
                        }
                    }
                    if (f1 == true)
                        break;
                }
            }

            teacher.add(te);
            courses.get(x).sections.get(y).setTeacher(te);
        }
            rs.close();
            ps.close();
            con.close();


    }

    private void readAllSection() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT * FROM Section";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        System.out.println("ALL Sections\n");

        while (rs.next()) {
            String i = rs.getString("ID");
            String t = rs.getString("Total_seats");
            String se = rs.getString("Seats_occupied");
            String c = rs.getString("course_code");
            Integer in=Integer.valueOf(t);
            Integer in1=Integer.valueOf(se);



            int count = courses.size() -1;
            Course co = null;
            while(count>-1)
            {
                if (courses.get(count).getCourseCode() == c)
                {
                    co = courses.get(count);
                    break;
                }
                count--;
            }

            Section s = new Section(i, in, in1, co);
            courses.get(count).addSection(s);
            Sec.add(s);
            rs.close();
            ps.close();
            con.close();

        }
    }

    private void readAllCourse() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT * FROM Course";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        System.out.println("ALL Sections\n");

        while (rs.next()) {
            String i = rs.getString("Name");
            String t = rs.getString("Course");
            String se = rs.getString("Credit_hr");
            String c = rs.getString("S_NUM");
            int count = Sem.size() -1;
            Integer in=Integer.valueOf(c);
            Semester s = null;
            while(count>-1)
            {
                if (Sem.get(count).getnum() == in)
                {
                    s = Sem.get(count);
                    break;
                }
                    count--;
            }
            Integer in1=Integer.valueOf(i);
            Course co = new Course(in1, t, se,s);
            Sem.get(count).addCourse(co);
            courses.add(co);
            rs.close();
            ps.close();
            con.close();

        }
    }

    private void readAllSemester () throws SQLException, ClassNotFoundException
    {
            Connection con = DBConnection.connect();
            PreparedStatement ps = null;
            ResultSet rs = null;


            String sql = "SELECT * FROM Semester";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("ALL Sections\n");
            while (rs.next()) {
                String i = rs.getString("num");

                Integer in=Integer.valueOf(i);
                Semester s = new Semester(in);

                Sem.add(s);


                rs.close();
                ps.close();
                con.close();
            }

    }

    private static Person createStu() throws SQLException, ClassNotFoundException {
        System.out.println("Enter Rollno(18XXXX): ");
        Scanner Usr = new Scanner(System.in);
        String rollno = Usr.nextLine();
        System.out.println("Password: ");
        //Scanner Usr2 = new Scanner(System.in);
        String password = Usr.nextLine();

        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "Select Name,DOB,Program,semesterNum  from Student where Rollno = ? AND Password = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, rollno);
        ps.setString(2, password);
        rs = ps.executeQuery();

        // we are reading one row, so   no need to loop
        String n = rs.getString(1);
        String d = rs.getString(2);
        //String r = rs.getString(3);
        String pr = rs.getString(3);
        String semesterNum = rs.getString(4);
        //String pass = rs.getString(5);

        Integer in = Integer.valueOf(semesterNum);
        System.out.println(n);// it should give us eunice
        System.out.println(d);// it should give us eunice
        System.out.println(pr);// it should give us eunice
        Person p = new Student(n,d,rollno,pr,password, in);



        rs.close();
        ps.close();
        con.close();
        p.print();
        return p;

    }

    private static void insertStu(String firstName, String Program, String DOB, String Rollno) throws SQLException, ClassNotFoundException
    {
        Connection con = DBConnection.connect();
        PreparedStatement ps;
        ps = null;

        String sql = "INSERT INTO Student(Name, DOB, Rollno, Program) VALUES(?,?,?,?) ";
        ps = con.prepareStatement(sql);
        ps.setString(1, firstName);
        ps.setString(2, DOB);
        ps.setString(3, Rollno);
        ps.setString(4, Program);
        ps.execute();
        System.out.println("Data has been inserted!");

        ps.close();
        con.close();
    }

}


