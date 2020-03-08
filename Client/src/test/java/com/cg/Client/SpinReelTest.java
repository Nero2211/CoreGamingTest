package com.cg.Client;

import beans.reel.ReelBean;
import beans.reel.ResultWin;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpinReelTest extends AbstractTest{

    private static final String REELS_SHEET = "Desirable Tasks";

    @Test
    public void testSpinReelAggregatedResponse() throws IOException {
        List<ReelBean> reelBeans = new ArrayList<>();
        int currentCall = 0;
        System.out.println("Making total of ["+HUNDRED_K_TEST+"] API calls to Reel_Service in total");
        while(currentCall < HUNDRED_K_TEST){
            reelBeans.add(Client.getReelResultData());
            System.out.println("API Call: #"+currentCall);
            currentCall++;
        }

        Assert.assertTrue("Failed to retrieve for BasicWeightTable", !reelBeans.isEmpty());
        Assert.assertEquals("Number of API calls made do not match", HUNDRED_K_TEST, reelBeans.size());

        double allAces = 0, allKings = 0, allQueens = 0, allJacks = 0, twoAces = 0, lose = 0;
        for(ReelBean bean : reelBeans){
            if(bean.getResultWin() != null){
                ResultWin result = bean.getResultWin();
                switch(result.getWinnerReel()){
                    case ALL_ACES:
                        allAces = allAces + incrementValue;
                        break;
                    case ALL_KINGS:
                        allKings = allKings + incrementValue;
                        break;
                    case ALL_QUEENS:
                        allQueens = allQueens + incrementValue;
                        break;
                    case ALL_JACKS:
                        allJacks = allJacks + incrementValue;
                        break;
                    case TWO_ACES:
                        twoAces = twoAces + incrementValue;
                        break;
                    default:
                        Assert.assertTrue("Encountered else statement as it was not expected nor designed", false);
                }
            }else if(bean.getResultLose() != null){
                lose = lose + incrementValue;
            }else{
                Assert.assertTrue("Encountered else statement as it was not expected nor designed", false);
                break;
            }
        }

        Assert.assertEquals( "Failed to match the confidence level for 3 x ACE", getConfidenceLevel(3), allAces, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for 3 x KING", getConfidenceLevel(4), allKings, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for 3 x QUEEN", getConfidenceLevel(5), allQueens, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for 3 x JACK", getConfidenceLevel(6), allJacks, 0.20);
        Assert.assertEquals( "Failed to match the confidence level for 2 x ACE", getConfidenceLevel(7), twoAces, 0.20);

        System.out.println("3 x Ace won percentage: "+allAces);
        System.out.println("3 x King won percentage: "+allKings);
        System.out.println("3 x Queen won percentage: "+allQueens);
        System.out.println("3 x Jack won percentage: "+allJacks);
        System.out.println("2 x Ace won percentage: "+twoAces);
        System.out.println("Lose percentage: "+lose);

    }

    private double getConfidenceLevel(int rowVal) throws IOException {
        int rowCounter = 2;
        int percentageColumn = 11;
        return getCellValue(REELS_SHEET, rowCounter, percentageColumn, rowVal);
    }

}
