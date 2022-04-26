/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosad.frame;

import com.mosad.dao.StudentJdbcDao;
import com.mosad.service.StudentService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Eng Mosad EL-sayed
 */
public class StudentFrame {

    public void createFrame() {
        JFrame frame = new JFrame();

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setSize(400, 100);

        JLabel studentNameLabel = new JLabel("Student Name");
        panel.add(studentNameLabel);

        JTextField studentNameText = new JTextField();

        studentNameText.setSize(300, 30);
        studentNameText.setColumns(10);
        panel.add(studentNameText);

        JLabel studentEmailLabel = new JLabel("Student Email");
        panel.add(studentEmailLabel);

        JTextField studentEmailText = new JTextField();

        studentEmailText.setSize(300, 30);
        studentEmailText.setColumns(10);
        panel.add(studentEmailText);

        JButton importDataFromExcel = new JButton("Import Data From Excel");

        importDataFromExcel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                StudentService studentService = new StudentService();
                studentService.importDataFromExcel();
            }
        });

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentJdbcDao studentJdbcDao = new StudentJdbcDao();
                studentJdbcDao.addStudent(studentNameText.getText(), studentEmailText.getText());
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
}
