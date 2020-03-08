package com.cg.Client;

import beans.weight.BasicWeightBean;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicWeightTableTest extends AbstractTest{

    private static final String BASIC_TABLE_SHEET = "Required Tasks";

    @Test
    public void testBasicWeightValueChance() throws IOException {
        List<BasicWeightBean> weightBeans = new ArrayList<>();
        int currentCall = 0;
        System.out.println("Making total of ["+HUNDRED_K_TEST+"] API calls to Weight_Service in total");
        while(currentCall < HUNDRED_K_TEST){
            weightBeans.add(Client.getBasicWeightValue());
            System.out.println("API call: #"+currentCall);
            currentCall++;
        }
        Assert.assertTrue("Failed to retrieve for BasicWeightTable", !weightBeans.isEmpty());
        Assert.assertEquals("Number of API calls made do not match", HUNDRED_K_TEST, weightBeans.size());

        double valueZero = 0, valueOne = 0, valueTwo = 0, valueThree = 0;
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
        return getCellValue(BASIC_TABLE_SHEET, rowCounter, percentageColumn, rowVal);
    }
}
