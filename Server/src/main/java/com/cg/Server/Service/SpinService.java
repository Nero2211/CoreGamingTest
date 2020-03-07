package com.cg.Server.Service;

import beans.reel.ReelSymbols;
import beans.reel.ReelBean;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinService {

    private static final String REELS_SHEET = "Desirable Tasks";
    private static final String EXCEL_FILE_NAME = "profile.xlsx";
    private static final int DRAW_REEL_COUNT = 3;
    private static final int DRAW_SYMBOL_COUNT = 3;

    public static ReelBean initSpinService(){
        ReelBean ret = null;
        List<ReelBean> matrix = generateReels();
        if(matrix != null && !matrix.isEmpty()) {
            int midIndex = matrix.size() / 2;
            ret = generateReelsResult(matrix.get(midIndex));
        }

        return ret;
    }

    private static List<ReelBean> generateReels(){
        int reelCounter = 0;

        List<ReelBean> beans = new ArrayList<>();
        while(reelCounter < DRAW_REEL_COUNT) {
            if(beans.size() == 3){
                break;
            }else {

                int cell = 4;
                int row = 3;
                int symbolCounter = 0;
                List<ReelSymbols> randomSymbols = new ArrayList<>();

                while (symbolCounter < DRAW_SYMBOL_COUNT) {

                    if (randomSymbols.size() == 3) {
                        break;
                    } else {
                        try {
                            int randomRow = getRandomNumberInRange(0, 17) + row;
                            randomSymbols.add(getRandomSymbol(randomRow, cell));
                            cell++;
                            symbolCounter++;
                        } catch (IOException e) {
                            System.out.println("Exception: " + e);
                            return null;
                        }
                    }
                }
                beans.add(new ReelBean(randomSymbols.get(0), randomSymbols.get(1), randomSymbols.get(2)));
            }
            reelCounter++;
        }

        return beans;
    }

    private static ReelSymbols getRandomSymbol(int rowVal, int cell) throws IOException {
        ReelSymbols ret = null;

        XSSFWorkbook excelFile = new XSSFWorkbook(new FileInputStream(EXCEL_FILE_NAME));
        XSSFSheet tableSheet = excelFile.getSheet(REELS_SHEET);
        XSSFRow row = tableSheet.getRow(rowVal);

        if(row.getCell(cell).getCellType() == HSSFCell.CELL_TYPE_STRING && isReelSymbolValid(row.getCell(cell).getRichStringCellValue().getString())){
            ret = ReelSymbols.valueOf(row.getCell(cell).getRichStringCellValue().getString());
        }
        return ret;
    }

    private static boolean isReelSymbolValid(String value){
        boolean isValid = false;
        for(ReelSymbols symbol: ReelSymbols.values()){
           if(symbol.name().equals(value)){
               isValid = true;
               break;
           }
        }
        return isValid;
    }

    private static ReelBean generateReelsResult(ReelBean bean){
        SpinReelResultService.getReelResults(bean);
        return bean;
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
