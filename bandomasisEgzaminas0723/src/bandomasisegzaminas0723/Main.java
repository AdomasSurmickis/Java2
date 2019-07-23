/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bandomasisegzaminas0723;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Dedelis
 */
public class Main {
        static List<Student> s = new ArrayList<>();
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        List<Student> st = s;
        loadData();

        Collections.sort(st, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                if (student1.getLastName().equals(student2.getLastName())) {
                    return student1.getFirstName().compareTo(student2.getFirstName());
                } else {
                    return student1.getLastName().compareTo(student2.getLastName());
                }
            }
        });
        for (Student student : st) {
            System.out.println("Name: " + student.getFirstName() + " Last name: " + student.getLastName()
                    + " Email: " + student.getEmail() + " Pazyiai: " + student.getGrades());
        }

        int year = 2018;
        System.out.println("Kurso " + year + " metu vidurkis: " + courseAverage(year));
    }
    
    private static void loadData() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary", "root", "admin");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select students.* from students");
        while (rs.next()) {
//            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)
//                    + " " + rs.getString(4));
            Student student = new Student();
            student.setFirstName(rs.getString(2));
            student.setLastName(rs.getString(3));
            student.setEmail(rs.getString(4));
            List<Integer> grades = new ArrayList<>();
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery("select * from diary.grades where student_id=" + rs.getInt(1));
            while (rs2.next()) {
                grades.add(rs2.getInt(4));
            }
            rs2.close();
            st2.close();

            student.setGrades(grades);
            s.add(student);
        }
        rs.close();
        st.close();
        conn.close();
    }
    
    private static int courseAverage(int year) throws SQLException {
        int average = 0;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary", "root", "admin");
        PreparedStatement pst = conn.prepareStatement("SELECT AVG(grade) "
                + "from students st join grades gr on st.id = gr.student_id "
                + "where year(grade_date) = ?;");
        pst.setInt(1, year);
        pst.execute();
        ResultSet rs = pst.getResultSet();
        if (rs.next()) {
            average = rs.getInt(1);
        }
        rs.close();
        pst.close();
        conn.close();
        return average;
    }
}
