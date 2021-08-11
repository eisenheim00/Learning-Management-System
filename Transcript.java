package com.company;

import java.util.Vector;

public class Transcript
{
    Vector<String> Semester = null;
    Vector<String> courses = null;
    Vector<String> grades = null;
    private int cgpa;
    public Transcript() { }
    public Transcript(Vector<String> s, Vector<String> crs, Vector<String> grds)
    {
    for(int i=0;i<crs.size();i++)
      courses.add(crs.get(i));
        for(int i=0;i<s.size();i++)
            Semester.add(s.get(i));
    for(int i=0;i<grds.size();i++)
      grades.add(grds.get(i));

    }
    public double returnScore(String g)
    {
        if(g == "A")
            return 4;
        if(g == "A-")
            return 3.67;
        if(g == "B+")
            return 3.33;
        if(g == "B-")
            return 2.67;
        if(g == "C+")
            return 2.33;
        if(g == "C")
            return 2;
        if(g == "C-")
            return 1.67;
        if(g == "D")
            return 1;
        if(g == "F")
            return 0;
        if(g == "W")
            return -1;
        else
            return -1;
    }

    public void view( Vector<Course> c )
    {
        double sgpa[] = new double[8];
        int crhrs[] = new int[8];

        for(int i=0;i<8;i++) {
            sgpa[i] = 0; crhrs[i] = 0;
        }
        for (int i = 0; i<Semester.size();i++)
        {
            if(Semester.get(i) == "1")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[1] = crhrs[1]+c.get(j).getCreditHour();
                if (score != -1)
                sgpa[1] = sgpa[1] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "2")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[2] = crhrs[2]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[2] = sgpa[2] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "3")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[3] = crhrs[3]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[3] = sgpa[3] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "4")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[4] = crhrs[4]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[4] = sgpa[4] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "5")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[5] = crhrs[5]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[5] = sgpa[5] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "6")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[6] = crhrs[6]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[6] = sgpa[6] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "7")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[7] = crhrs[7]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[7] = sgpa[7] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }
            if(Semester.get(i) == "8")
            {
                int j = 0;
                while (courses.get(i) != c.get(j).getCourseCode())
                    j++;
                double score = returnScore(grades.get(i));
                crhrs[8] = crhrs[8]+c.get(j).getCreditHour();
                if (score != -1)
                    sgpa[8] = sgpa[8] + c.get(i).getCreditHour()*score;
                System.out.println("Semester: " + Semester.get(i) + " Course: " + courses.get(i) + " Score: " + score +"\n");

            }

        }
        System.out.println("SGPAs\n");
        int count = 0;
        double total = 0;
        for(int x=0;x<8;x++)
        {
            System.out.println("Semester " + (x+1) + " : " + sgpa[x]/crhrs[x] );
            if(sgpa[x]!=0)
            {
                total =  (total + (sgpa[x]/crhrs[x])); count++;
            }
        }
        System.out.println("\nCGPA : " + total/count + "\n");

    }
}
