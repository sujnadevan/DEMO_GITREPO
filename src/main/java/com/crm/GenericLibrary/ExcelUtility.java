package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	/**
	 * This class is used to read and write data from Excel
	 * 
	 */
	/**
	 * This method is used to read the data from Excel
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @return
	 * @throws Throwable
	 */
	public String getDataFromExcel(String sheetName , int rowNum, int celNum) throws Throwable {
		FileInputStream fis  = new FileInputStream(IPathConstant.EPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.getCell(celNum);
		String data = cel.getStringCellValue();
		wb.close();
		return data;
	}
	/**
	 * This method is used  to get row count
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	 public int getRowCount(String sheetName) throws Throwable {
		FileInputStream fis  = new FileInputStream(IPathConstant.EPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		wb.close();
		return sh.getLastRowNum();
	 }
	 /**
	  * 
	  * @param sheetName
	  * @param rowNum
	  * @param celNum
	  * @param data
	  * @throws Throwable
	  */
	   public void setDataExcel(String sheetName , int rowNum, int celNum ,String data) throws Throwable 
	   {
			FileInputStream fis  = new FileInputStream(IPathConstant.EPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cel = row.createCell(celNum);
			cel.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(IPathConstant.FilePath);
			wb.write(fos);
			wb.close();
			
		}
	}




