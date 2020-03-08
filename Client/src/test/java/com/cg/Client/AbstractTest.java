package com.cg.Client;

import beans.reel.ReelSymbols;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractTest {

    /*
    To run 100k Tests, use the pre-defined data and comment out the duplicate data which is only used for running 100 tests
    Do note that the SpinReelTest can be time consuming
     */
    /*protected static final int HUNDRED_K_TEST = 100001;
    protected static final double INCREMENT_VALUE = 0.00001;*/
    protected static final int HUNDRED_K_TEST = 101;
    protected static final double INCREMENT_VALUE = 0.01;

    protected static final double REQUEST_COST = 3.50;
    protected static final String EXCEL_FILE_NAME = "profile.xlsx";
    protected static final String CONFIDENCE_LEVEL_COLUMN_TITLE = "100k runs error margin";

    protected double getCellValue(String sheetName, int rowCounter, int column, int rowVal) throws IOException {
        double ret = 0;

        String filePath = getClass().getClassLoader().getResource(EXCEL_FILE_NAME).getFile();
        if(filePath != null) {
            XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet tableSheet = excelFile.getSheet(sheetName);
            XSSFRow row = tableSheet.getRow(rowCounter);

            if (row.getCell(column).getCellType() == HSSFCell.CELL_TYPE_STRING &&
                    row.getCell(column).getRichStringCellValue().getString().equalsIgnoreCase(CONFIDENCE_LEVEL_COLUMN_TITLE)) {
                XSSFRow tableRow = tableSheet.getRow(rowVal);
                if (tableRow.getCell(column).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    ret = tableRow.getCell(column).getNumericCellValue() * 100;
                }
            }
        }

        return ret;
    }

    protected boolean isReelSymbolValid(String value){
        boolean isValid = false;
        for(ReelSymbols symbol: ReelSymbols.values()){
            if(symbol.name().equals(value)){
                isValid = true;
                break;
            }
        }
        return isValid;
    }

}
