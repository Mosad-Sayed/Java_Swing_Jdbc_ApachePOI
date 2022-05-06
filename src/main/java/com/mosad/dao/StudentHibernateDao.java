package com.mosad.dao;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.mosad.model.Student;

public class StudentHibernateDao {

	public boolean addStudent(String name, String email) {
		System.out.println("StudentHibernateDao - name: " + name + ", email: " + email);
		
		Configuration c = loadConfiguration();
		Session session = c.buildSessionFactory().openSession();

		try {
			Student student = new Student();
			student.setName(name);
			student.setEmail(email);

			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			System.out.println("StudentHibernateDao - Student Added : " + name);
			return true;

		} catch (Exception e) {
			System.err.println("Error in addStudent method");
		} finally {
			session.close();
		}

		return false;
	}

	private Configuration loadConfiguration() {

		Configuration c = new Configuration();
		c.configure("hibernate.cfg2.xml");

		return c;
	}
}