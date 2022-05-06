package com.mosad.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mosad.service.StudentService;

public class StudentFrame {
	JTextField studentNameText = createTextField();
	JTextField studentEmailText = createTextField();

	public void createFrame() {
		JFrame frame = new JFrame();

		JPanel panel = new JPanel(new GridLayout(4, 2));
		panel.setSize(400, 100);

		panel.add(new JLabel("Student Name"));
		panel.add(studentNameText);
		panel.add(new JLabel("Student Email"));
		panel.add(studentEmailText);

		panel.add(createAddUsingJDBCButton());
		panel.add(createAddUsingHibernateButton());
		panel.add(createImportButton());
		frame.add(panel);

		setFrameProperties(frame);
	}

	private void setFrameProperties(JFrame jframe) {
		jframe.setResizable(true);
		jframe.setTitle("Students");
		jframe.setSize(500, 500);
		jframe.setLayout(null);
		jframe.setVisible(true);
	}

	private JTextField createTextField() {
		JTextField textField = new JTextField();
		textField.setSize(300, 30);
		textField.setColumns(10);

		return textField;
	}

	private JButton createAddUsingJDBCButton() {
		JButton addButton = new JButton("Add Using JDBC");

		// anonymous class
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentService studentService = new StudentService();
				studentService.addStudentUsingJDBC(studentNameText.getText(), studentEmailText.getText());
			}
		});

		return addButton;

	}

	private JButton createAddUsingHibernateButton() {
		JButton addButton = new JButton("Add Using Hibernate");

		// anonymous class
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentService studentService = new StudentService();
				studentService.addStudentUsingHibernate(studentNameText.getText(), studentEmailText.getText());
			}
		});

		return addButton;

	}
	
	private JButton createImportButton() {
		JButton importDataFromExcel = new JButton("Import Data From Excel");

		importDataFromExcel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				StudentService studentService = new StudentService();
				studentService.importDataFromExcel();

			}
		});

		return importDataFromExcel;

	}
}