package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

public class Student extends Person
{
    private String Program;
    private String Password;
    private String Rollno;
    private Integer semesterNum;
    Vector<Registration> reg = null;
    Transcript trans;
    Vector<Attendance> at;
    public Student ()
    {
        super();
    }
    public Student(String n, String d, String p, String r, String pa, Integer semesterNum)
    {
        super(n,d);
        Program=p;
        Password = pa;
        Rollno=r;
        this.semesterNum = semesterNum;
    }
    public void print()
    {
        super.print();
        System.out.println("Rollno : " + Rollno);
        System.out.println("Password : " + Password);
        System.out.println("Program : " + Program);
    }
    public String getProgram()
    {
        return Program;
    }
    public String getRollNo()
    {
        return Rollno;
    }

    public void register(Vector<Semester> sem) throws ParseException, SQLException, ClassNotFoundException {
        System.out.printf("press 1: register a course\npress 2: drop a course\n");
        String input = null;
        Scanner Usr = new Scanner(System.in);
        while(input != "1" && input != "2" )
            input = Usr.nextLine();

        if(input == "1") // Add Registration
        {
            int i = 0;
            while(sem.get(i).getnum() != semesterNum )
                i++;
            int count = 0;
            while(count != sem.get(i).courses.size()) {
                sem.get(i).courses.get(count).print();
                count++;
            }

            System.out.println("Enter Course Code: ");
            input = Usr.nextLine();
            boolean isCode = false;
            count = 0;
            while(count != sem.get(i).courses.size()) {
                    if (sem.get(i).courses.get(count).getCourseCode() == input) {
                        isCode = true;
                        break;
                    }
                    count++;
            }
            if (isCode)
            {
                int count1 = 0;
                while(count1 != sem.get(i).courses.get(count).sections.size()) {
                    sem.get(i).courses.get(count).sections.get(count1).print();
                    count1++;
                }
                System.out.println("Enter Section: ");
                input = Usr.nextLine();
                count1 = 0;
                boolean isSec = false;
                while(count1 != sem.get(i).courses.get(count).sections.size()) {
                   // sem.get(i).courses.get(count).sections.get(count1);
                    if (sem.get(i).courses.get(count).sections.get(count1).getSectionName() == input)
                    {
                        isSec = true;
                        break;
                    }
                    count1++;
                }
                if (isSec) {
                    String sDate1 = "12-08-2020";
                    Date todayDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
                    Registration r = new Registration();
                    if (!r.getstartDate().after(todayDate) && !r.getendDate().before(todayDate)) {
                        /* historyDate <= todayDate <= futureDate */
                        r.setStu(this);
                        r.setsec(sem.get(i).courses.get(count));
                        r.setsec(sem.get(i).courses.get(count).sections.get(count1));
                        sem.get(i).courses.get(count).sections.get(count1).setReg(r);
                        r.save();
                        reg.add(r);
                    }
                }
            }
        }
        else { //Drop Registration
            String sDate1 = "12-08-2020";
            Date todayDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);

                this.fetchStudentRegs();
                System.out.println("Enter Registration No.: ");
                input = Usr.nextLine();

                Connection con = DBConnection.connect();
                PreparedStatement ps = null;

                PreparedStatement ps1 = null;
                ResultSet rs1 = null;


            String sql1 = "SELECT Start_time,End_time,Course_code,S_ID FROM Registration where Student_rno = ?";
            ps1 = con.prepareStatement(sql1);
            ps1.setString(1, Rollno);
            rs1 = ps1.executeQuery();
            String s = rs1.getString("Start_time");
            String e = rs1.getString("End_time");
            String code = rs1.getString("Course_code");
            String sect = rs1.getString("S_ID");
            Date start = new SimpleDateFormat("dd-MM-yyyy").parse(s);
            Date end = new SimpleDateFormat("dd-MM-yyyy").parse(e);
            ps1.close();
            rs1.close();


            if(!start.after(todayDate) && !end.before(todayDate)) {

                String sql = "delete from Registration WHERE No = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, input);
                ps.execute();
                System.out.println("Registration Deleted!");


                int x = 0;
                while(x != reg.size())
                {
                    if (reg.get(x).getregNo() == input)
                    {
                        int j=0,k=0,l=0;
                        while(semesterNum != sem.get(j).getnum())
                        j++;
                        boolean isSec = false, isCo = false;
                        while(k != sem.get(j).courses.size()) {
                            if (code != sem.get(j).courses.get(k).getCourseCode()) {
                                isCo = true;
                                break;
                            }
                            k++;
                        }
                        if (isCo) {
                            while (l != sem.get(j).courses.get(k).sections.size())
                                if (sect != sem.get(j).courses.get(k).sections.get(l).getSectionName()) {
                                    isSec = true;
                                    break;
                                }
                            l++;
                        }
                        sem.get(j).courses.get(k).sections.get(l).cancelReg(reg.get(x));
                        reg.remove(x);
                        break;
                    }
                    x++;
                }


                ps.close();
            }
            else
            {
                System.out.println("Registration Period Ended!\n");
            }
            con.close();
        }

    }

    public void fetchStudentRegs() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT S_ID,No,Course_code FROM Registration where Student_rno = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, Rollno);
        rs = ps.executeQuery();
        System.out.println("ALL Registrations of " + Rollno + "\n");
        while (rs.next()) {
            String s = rs.getString("S_ID");
            String r = rs.getString("No");
            String w = rs.getString("Course_code");
            System.out.println("RegNo: " + r + "Course Code: " + w + "Section: " + s + "\n");
            rs.close();
            ps.close();
            con.close();
        }
    }

    public void withdraw(Vector<Semester> sem) throws ParseException, SQLException, ClassNotFoundException {

        String sDate1 = "12-08-2020";
        Date todayDate = new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);
        String input = null;
        this.fetchStudentRegs();
        System.out.println("Enter Registration No.: ");

        Scanner Usr = new Scanner(System.in);
        input = Usr.nextLine();

        Connection con = DBConnection.connect();
        PreparedStatement ps = null;

        PreparedStatement ps1 = null;
        ResultSet rs1 = null;


        String sql1 = "SELECT Withdraw_time,Course_code,S_ID FROM Registration where Student_rno = ?";
        ps1 = con.prepareStatement(sql1);
        ps1.setString(1, Rollno);
        rs1 = ps1.executeQuery();
        String s = rs1.getString("Withdraw_time");
        String code = rs1.getString("Course_code");
        String sect = rs1.getString("S_ID");
        Date withdraw_time = new SimpleDateFormat("dd-MM-yyyy").parse(s);

        ps1.close();
        rs1.close();


        if (!withdraw_time.before(todayDate)) {

            String sql = "UPDATE Registration set Grade = ? WHERE No = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, "W");
            ps.setString(2, input);
            ps.execute();
            System.out.println("Registration Deleted!");


            int x = 0;
            while (x != reg.size()) {
                if (reg.get(x).getregNo() == input) {
                    String g = new String("W");
                   reg.get(x).setGrade(g);
                    break;
                }
                x++;
            }
            ps.close();
        } else {
            System.out.println("Withdraw Period Ended!\n");
        }
        con.close();
    }

    public void viewOfferedCourses(Vector<Semester> sem)
    {
        int i = 0;
        while(sem.get(i).getnum() != semesterNum )
            i++;
        int count = 0;
        while(count != sem.get(i).courses.size()) {
            sem.get(i).courses.get(count).print();
            int j = 0;
            while(j != sem.get(i).courses.get(count).sections.size())
            {
                System.out.println("Available Sections: " );
                sem.get(i).courses.get(count).sections.get(j).print();
                j++;
            }
            count++;
        }
    }

    public void loadAttendance(Vector<Teacher> te) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT Present,Date,Sec_id,T_id FROM Attendance WHERE Stu_rno = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, Rollno);
        rs = ps.executeQuery();
        System.out.println("ALL Teachers\n");

        while (rs.next()) {
            String p = rs.getString("Present");
            String d = rs.getString("Date");
            String s = rs.getString("Sec_id");
            String t = rs.getString("T_id");

            int i =0,j=0;
            for (i=0;i<te.size();i++)
            {
                if(te.get(i).getID() == t)
                    break;
            }
            for (j=0;j<te.get(i).sec.size();j++)
            {
                if(te.get(i).sec.get(j).getSectionName() == s)
                    break;
            }
            boolean isPresent = false;
            if (p == "1")
                isPresent = true;
            else if (p == "0")
                isPresent = false;

            Attendance a = new Attendance(this,d,te.get(i).sec.get(j),te.get(i),isPresent);
            at.add(a);


            }
        rs.close();
        ps.close();
        con.close();


    }
    public void viewAttendance()
    {
        for (int i = 0; i<at.size();i++)
        {
            at.get(i).print();
        }
    }

    public void loadTranscript() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.connect();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String sql = "SELECT Present,Date,Sec_id,T_id FROM Transcript WHERE Stu_rno = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, Rollno);
        rs = ps.executeQuery();
        System.out.println("Transcript\n");
        Vector<String> grade = null;
        Vector<String> Course = null;
        Vector<String> Semester = null;
        while (rs.next()) {
            String c = rs.getString("Course");
            String g = rs.getString("Grade");
            String s = rs.getString("Semester");
            grade.add(g);
            Course.add(c);
            Semester.add(s);

        }
        trans = new Transcript(Semester,Course,grade);
        rs.close();
        ps.close();
        con.close();


    }

    public void viewTranscript(Vector<Course> c) throws SQLException, ClassNotFoundException {
        loadTranscript();
        trans.view(c);
    }

}