package com.cg.Client;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractTest {

    protected double getCellValue(String fileName, String sheetName, int rowCounter, int column, int rowVal, String rowValidationTitle) throws IOException {
        double ret = 0;

        String filePath = getClass().getClassLoader().getResource(fileName).getFile();
        if(filePath != null) {
            XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet tableSheet = excelFile.getSheet(sheetName);
            XSSFRow row = tableSheet.getRow(rowCounter);

            if (row.getCell(column).getCellType() == HSSFCell.CELL_TYPE_STRING &&
                    row.getCell(column).getRichStringCellValue().getString().equalsIgnoreCase(rowValidationTitle)) {
                XSSFRow tableRow = tableSheet.getRow(rowVal);
                if (tableRow.getCell(column).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    ret = tableRow.getCell(column).getNumericCellValue() * 100;
                }
            }
        }

        return ret;
    }

}
