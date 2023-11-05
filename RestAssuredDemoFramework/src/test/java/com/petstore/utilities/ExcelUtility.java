package com.petstore.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	String excelPath;
	String sheetName;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell col;
	FileInputStream fis;
	FileOutputStream fos;
	public ExcelUtility(String excelPath, String sheetName) {
		//this.excelPath=excelPath;
		//this.sheetName=sheetName;
		try {
			workBook = new XSSFWorkbook(excelPath);
			sheet=workBook.getSheet(sheetName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public int getRowCount(String sheetName) {
		int rowCount;
		rowCount=sheet.getPhysicalNumberOfRows();
		//rowCount=sheet.getLastRowNum(); -->Second method
		System.out.println(rowCount);
		return rowCount;
	}
	public int getColCount(String sheetName, int rowNum) {
		int colCount;
		colCount=sheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println(colCount);
		//colCount=row.getLastCellNum(); -->Second method
		return colCount;
	}
	public String getCellData(String sheetName, int rowNum, int colNum){
		try {
			row=sheet.getRow(rowNum);
			col=row.getCell(colNum);
			String cellData;
			DataFormatter data = new DataFormatter();
			cellData=data.formatCellValue(col);
			System.out.println("CellData of "+rowNum+"&"+colNum+" is:"+cellData);
			return cellData;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "NuLL";
		}
	}
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File file = new File(excelPath);
		if(!file.exists()) {  //If file is not there, create a new file
			workBook = new XSSFWorkbook();
			fos = new FileOutputStream(excelPath);
			workBook.write(fos);
		}
		fis=new FileInputStream(excelPath);
		workBook = new XSSFWorkbook(fis);
		if(workBook.getSheetIndex(sheetName)==-1) { // If sheet name is not there, create a new sheet
			workBook.createSheet(sheetName);
		}
		sheet=workBook.getSheet(sheetName);
		if(sheet.getRow(rowNum)==null) { // If row is not there, create a new row
			sheet.createRow(rowNum);
		}
		row=sheet.getRow(rowNum);
		col=row.createCell(colNum);
		col.setCellValue(data);
		fos = new FileOutputStream(excelPath);
		workBook.write(fos);
		workBook.close();
		fis.close();
		fos.close();
	}
}
