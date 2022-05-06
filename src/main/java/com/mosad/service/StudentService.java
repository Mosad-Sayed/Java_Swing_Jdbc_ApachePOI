/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosad.service;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mosad.dao.StudentHibernateDao;
import com.mosad.dao.StudentJdbcDao;

public class StudentService {

	public void importDataFromExcel() {
		try (FileInputStream fileInputStream = new FileInputStream(
				getClass().getClassLoader().getResource("students.xls").getFile());) {

			HSSFWorkbook fWorkbook = new HSSFWorkbook(fileInputStream);
			HSSFSheet fSheet = fWorkbook.getSheet("Students");

			for (int i = 0; i < fSheet.getPhysicalNumberOfRows(); i++) {
				HSSFRow fRow = fSheet.getRow(i);
				String name = fRow.getCell(0).getStringCellValue();
				String email = fRow.getCell(1).getStringCellValue();

				addStudentUsingJDBC(name, email);
			}
		} catch (Exception ex) {
			System.err.println("Error in importDataFromExcel method");
			System.err.println(ex);
		}
	}

	public void addStudentUsingJDBC(String name, String email) {
		System.out.println("StudentService - name: " + name + ", email: " + email);

		StudentJdbcDao studentDao = new StudentJdbcDao();
		studentDao.addStudent(name, email);
	}

	public void addStudentUsingHibernate(String name, String email) {
		System.out.println("StudentService - name: " + name + ", email: " + email);

		StudentHibernateDao studentDao = new StudentHibernateDao();
		studentDao.addStudent(name, email);
	}
}
