package com.cg.Client;

import bean.BasicWeightBean;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommunicationTest extends AbstractTest{

    private static final List<Double> BASIC_WEIGHT_VALUE = Arrays.asList(0.0, 1.0, 2.0, 3.0);
    private static final int basicWeightApiCallsToMake = 100001;
    /*private static final int basicWeightApiCallsToMake = 101;*/
    private static final String BASIC_TABLE_SHEET = "Required Tasks";
    private static final String REELS_SHEET = "Desirable Tasks";
    private static final String EXCEL_FILE_NAME = "profile.xlsx";
    private static final String CONFIDENCE_LEVEL_COLUMN_TITLE = "100k runs error margin";

    @Test
    public void testHelloRequestCorrect() throws Exception {
        String res = Client.postRequest("Hello");
        assert (res.equals("Hello stranger!"));
    }

    @Test
    public void testHelloRequestIncorrect() throws Exception {
        String res = Client.postRequest("hello");
        assert (res.equals("Error! No or invalid request name specified! (hello)"));
    }

    @Test
    public void testCommunicationWithTableApi() throws IOException {
        BasicWeightBean data = Client.getBasicWeightValue();
        Assert.assertNotNull("Failed to retrieve data from BasicWeightTable", data);
        Assert.assertTrue("Unexpected value provided by Server", BASIC_WEIGHT_VALUE.contains(data.getValue()));
    }

    @Test
    public void testBasicWeightValueChance() throws IOException {
        List<BasicWeightBean> weightBeans = new ArrayList<>();
        int currentCall = 0;
        System.out.println("Making total of ["+basicWeightApiCallsToMake+"] API calls in total");
        while(currentCall < basicWeightApiCallsToMake){
            weightBeans.add(Client.getBasicWeightValue());
            System.out.println("API call: #"+currentCall);
            currentCall++;
        }
        Assert.assertTrue("Failed to retrieve for BasicWeightTable", !weightBeans.isEmpty());
        Assert.assertEquals("Number of API calls made do not match", basicWeightApiCallsToMake, weightBeans.size());

        double valueZero = 0, valueOne = 0, valueTwo = 0, valueThree = 0;
        double incrementValue = 0.00001;
        for(BasicWeightBean bean : weightBeans){
            if(bean.getValue() == 0.0){
                valueZero = valueZero + incrementValue;
            }else if(bean.getValue() == 1.0){
                valueOne = valueOne + incrementValue;
            }else if(bean.getValue() == 2.0){
                valueTwo = valueTwo + incrementValue;
            }else if(bean.getValue() == 3.0){
                valueThree = valueThree + incrementValue;
            }
        }

        Assert.assertEquals("All value occurrence total is not equal to expected value", 1, (int)(valueZero + valueOne + valueTwo + valueThree));
        Assert.assertEquals( "Failed to match the confidence level for value £0.00", getConfidenceLevel(3), valueZero, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for value £1.00", getConfidenceLevel(4), valueOne, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for value £2.00", getConfidenceLevel(5), valueTwo, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for value £3.00", getConfidenceLevel(6), valueThree, 0.20);

        System.out.println("valueZero occurrence: "+valueZero);
        System.out.println("valueOne occurrence: "+valueOne);
        System.out.println("valueTwo occurrence: "+valueTwo);
        System.out.println("valueThree occurrence: "+valueThree);
    }

    private double getConfidenceLevel(int rowVal) throws IOException {
        int rowCounter = 2;
        int percentageColumn = 3;
        return getCellValue(EXCEL_FILE_NAME, BASIC_TABLE_SHEET, rowCounter, percentageColumn, rowVal, CONFIDENCE_LEVEL_COLUMN_TITLE);
    }
}
