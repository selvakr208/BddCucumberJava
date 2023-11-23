package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadExcelFile {
	
	//public static void main(String[] args) {

	
	@DataProvider(name="testdata")
    public String[][] getTestDataFromExcel() throws Exception {
        
          
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/testdata/TestData.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("fromDepartureDate " + "returnDepartureDate ");
            int totalRows = sheet.getLastRowNum();
            Row rowcells= sheet.getRow(0);           
            int totalCols = rowcells.getLastCellNum();
            System.out.println(totalRows +", "+totalCols);
            
            String testData[][]= new String[totalRows][totalCols];
            
            DataFormatter dataFormatter = new DataFormatter();
            
            System.out.println(dataFormatter.formatCellValue(rowcells.getCell(0)));
            System.out.println(dataFormatter.formatCellValue(rowcells.getCell(1)));
            
            for(int i=1; i<=totalRows; i++) {
            	for(int j=0; j<totalCols; j++) {
            		
            		testData[i-1][j]= dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
            		System.out.print(testData[i-1][j]);
            		System.out.print(" ");
            	}
            	System.out.println();
            }
            
            fis.close();
            return testData;
            
         
        
        
         
        
    }


}
