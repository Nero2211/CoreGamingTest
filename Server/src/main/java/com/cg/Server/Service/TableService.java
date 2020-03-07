package com.cg.Server.Service;

import beans.weight.BasicWeightBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TableService {

    private static final String BASIC_TABLE_SHEET = "Required Tasks";
    private static final String EXCEL_FILE_NAME = "profile.xlsx";

    public static BasicWeightBean initBasicWeightTable(){
        BasicWeightBean randomlySelected = null;
        try {
            List<BasicWeightBean> data = getBasicWeightData();
            if(data!= null && !data.isEmpty()) {
                double random = Math.random();
                double chanceVal = 0;
                data.sort(Comparator.comparingDouble(BasicWeightBean::getChance));

                for (BasicWeightBean weight : data) {
                    chanceVal = chanceVal + weight.getChance();
                    if (random <= chanceVal) {
                        randomlySelected = new BasicWeightBean(weight.getValue());
                        break;
                    }
                }
            }
        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        return randomlySelected;
    }

    private static List<BasicWeightBean> getBasicWeightData() throws IOException {
        List<BasicWeightBean> data = new ArrayList<>();
        int rowCounter = 2;
        int value = 1;
        int chance = 2;

        XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream(EXCEL_FILE_NAME));
        XSSFSheet tableSheet = excelFile.getSheet(BASIC_TABLE_SHEET);
        XSSFRow row = tableSheet.getRow(rowCounter);

        if((row.getCell(value).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(value).getRichStringCellValue().getString().equalsIgnoreCase("value")) &&
                (row.getCell(chance).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(chance).getRichStringCellValue().getString().equalsIgnoreCase("chance"))){
            while(rowCounter < 6){
                rowCounter++;
                XSSFRow tableRow = tableSheet.getRow(rowCounter);
                data.add(new BasicWeightBean(
                        tableRow.getCell(value).getNumericCellValue(),
                        tableRow.getCell(chance).getNumericCellValue()
                ));
            }
        }
        return data;
    }
}
