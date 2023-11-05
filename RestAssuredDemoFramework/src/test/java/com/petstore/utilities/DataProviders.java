package com.petstore.utilities;

import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataProviders {
	String excelPath=System.getProperty("user.dir")+".\\TestData\\APIDataDrivenTestData.xlsx";
	String sheetName="APITestData";
	ExcelUtility utility;
	@DataProvider(name="Data")
	public Object[][] getAllData(){
		utility = new ExcelUtility(excelPath, sheetName);
		int rows=utility.getRowCount(sheetName);
		int colomns = utility.getColCount(sheetName, 0);
		Object[][] apiData = new Object[rows-1][1];
		Hashtable<String, String> table=null;
		for(int rownum=1; rownum<rows; rownum++ ) {
			table = new Hashtable<String, String>();
			for(int colnum=0; colnum<colomns; colnum++) {
				table.put(utility.getCellData(sheetName, 0, colnum), utility.getCellData(sheetName, rownum, colnum));
				System.out.println(utility.getCellData(sheetName, 0, colnum)+"=="+utility.getCellData(sheetName, rownum, colnum));
				apiData[rownum-1][0]=table;
				//System.out.println("Data storing in:"+(rownum-1)+colnum);
			}
		}
		return apiData;
	}
	@DataProvider(name="UserNames")
	public String[] getUserNames() {
		utility = new ExcelUtility(excelPath, sheetName);
		int rows = utility.getRowCount(sheetName);
		String userNames[] = new String[rows-1];
		for(int rowNum=1; rowNum<rows; rowNum++) {
			userNames[rowNum-1]=utility.getCellData(sheetName, rowNum, 1);
		}
		return userNames;
	}

}
