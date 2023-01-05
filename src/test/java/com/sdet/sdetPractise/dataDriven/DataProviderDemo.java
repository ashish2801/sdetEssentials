package com.sdet.sdetPractise.dataDriven;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataProviderDemo {


    DataFormatter formatter = new DataFormatter();
    @Test(dataProvider ="testData" )
    public void dataDrivenDemo(String firstName, String lastName , String dateOfBirth){

        System.out.println(firstName+" "+lastName +" "+dateOfBirth);

    }
 // difference between running data provider via testNG is for each data we  would have different test case
 // but if we data drive through only excel sheet , then we would have only a single test case in that case and
 //any failure would be very difficult to identify

    @DataProvider(name="testData")
       public Object[][] testDataProvider() throws IOException {

//        Object[][] data = {{"Priti","Kumari",23},{"Ashish", "Kumar", 15},{"Rita", "Das",05}};
//        return data;

        FileInputStream file = new FileInputStream("//Users//ashishk//Downloads//DataProvider.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet= wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        int rowCount =  sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount-1][columnCount];

        for( int i =0 ;i<rowCount-1;i++){

            row= sheet.getRow(i+1);
            for(int j=0;j<columnCount;j++){

                XSSFCell cell =row.getCell(j);
                data[i][j]=formatter.formatCellValue(cell);

            }

        }

        return data;

    }

}
