/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mosad.service;

import com.mosad.dao.StudentJdbcDao;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Eng Mosad EL-sayed
 */
public class StudentService {

    public void importDataFromExcel() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(
                    "src/excelSheets/students.xls"));
            HSSFWorkbook fWorkbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet fSheet = fWorkbook.getSheet("Students");
            for (int i = 0; i < fSheet.getPhysicalNumberOfRows(); i++) {
                HSSFRow fRow = fSheet.getRow(i);
                String name = fRow.getCell(0).getStringCellValue();
                String email = fRow.getCell(1).getStringCellValue();

                StudentJdbcDao studentJdbcDao = new StudentJdbcDao();
                studentJdbcDao.addStudent(name, email);
            }
        } catch (Exception ex) {
            System.err.println("Error in import Excel");
            System.err.println(ex);
        }
    }
    
    public void addStudent(){
        StudentJdbcDao studentJdbcDao = new 
    }
}
