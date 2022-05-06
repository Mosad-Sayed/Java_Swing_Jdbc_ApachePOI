/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mosad.constants.Constants;

/**
 *
 * @author Eng Mosad EL-sayed
 */
public class StudentJdbcDao {

	public boolean addStudent(String name, String email) {

		try (Connection con = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USERNAME, "");) {
			PreparedStatement ps = con.prepareStatement("insert into student(name,email)values(?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.executeUpdate();
			System.out.println("StudentJdbcDao - student Added : " + name);
			return true;
		} catch (Exception ex) {
			System.err.println("Error in addStudent method");
			ex.printStackTrace();
		}

		return false;
	}
}
