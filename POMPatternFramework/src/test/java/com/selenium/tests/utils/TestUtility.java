package com.selenium.tests.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtility {

	private static Logger log = Logger.getLogger(TestUtility.class);

	
	// lets we consider the file with format xls / xlsx
	public static Object[][] readExcel(String filePath, String sheetName) {
		// define the file with object less
		// create file input stream

		Object[][] data = null;

		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e1) {
			log.debug(e1.getStackTrace());
		}

		Workbook workBook = null;

		if (filePath.contains(".xls")) {
			try {
				workBook = new HSSFWorkbook(fileInputStream);
			} catch (IOException e) {
				log.debug(e.getStackTrace());
			}
		} else {
			try {
				workBook = new XSSFWorkbook(fileInputStream);
			} catch (IOException e) {
				log.debug(e.getStackTrace());
			}
		}

		Sheet workSheet = workBook.getSheet(sheetName);
		// getting the values from xls and storing into object array 
		data = new Object[workSheet.getLastRowNum()][workSheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < workSheet.getLastRowNum(); i++) {
			for (int j = 0; j < workSheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = workSheet.getRow(i + 1).getCell(j).toString();
			}
		}

		return data;

	}

}
