/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosad.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Main {

    JTextField studentNameText = new JTextField();
    JTextField studentEmailText = new JTextField();

    public static void main(String[] args) {

        Main m = new Main();
        m.createFrame();

    }

    public void addStudent() {

        String name = studentNameText.getText();
        String email = studentEmailText.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Student2", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into student(name,email)values(?,?)");
            ps.setString(1, name);
            ps.setString(2, email);

            ps.executeUpdate();
            con.close();
            studentNameText.setText("");
            studentEmailText.setText("");
            System.out.println("Student Added");

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    public void createFrame() {

        JFrame frame = new JFrame();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setSize(400, 100);

        JLabel studentNameLabel = new JLabel("Student Name");
        panel.add(studentNameLabel);

        studentNameText.setSize(300, 30);
        studentNameText.setColumns(10);
        panel.add(studentNameText);

        JLabel studentEmailLabel = new JLabel("Student Email");
        panel.add(studentEmailLabel);

        studentEmailText.setSize(300, 30);
        studentEmailText.setColumns(10);
        panel.add(studentEmailText);

        JButton addButton = new JButton("Add");
        JButton importDataFromExcel = new JButton("Import Data From Excel");
        importDataFromExcel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                importDataFromExcel();
            }

        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        panel.add(addButton);
        panel.add(importDataFromExcel);
        frame.add(panel);

        frame.setResizable(true);
        frame.setTitle("Students");
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    private void importDataFromExcel() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(
                    "D:\\students.xls"));
            HSSFWorkbook fWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet fSheet = fWorkbook.getSheet("Students");
            for (int i = 0; i < fSheet.getPhysicalNumberOfRows(); i++) {
                HSSFRow fRow = fSheet.getRow(i);
                String name = fRow.getCell(0).getStringCellValue();
                String email = fRow.getCell(1).getStringCellValue();

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Student2", "root", "");
                PreparedStatement ps = con.prepareStatement("insert into student(name,email)values(?,?)");
                ps.setString(1, name);
                ps.setString(2, email);

                ps.executeUpdate();
                con.close();
                System.out.println("Student Added");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
