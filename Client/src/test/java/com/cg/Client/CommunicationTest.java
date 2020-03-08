package com.cg.Client;

import beans.reel.ReelBean;
import beans.weight.BasicWeightBean;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommunicationTest extends AbstractTest{

    private static final List<Double> BASIC_WEIGHT_VALUE = Arrays.asList(0.0, 1.0, 2.0, 3.0);

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
    public void testCommunicationWithSpinApi() throws IOException {
        ReelBean data = Client.getReelResultData();

        Assert.assertNotNull("Failed to retrieve data from ReelSpinService", data);
        Assert.assertTrue("Encountered unexpected symbols from data for SymbolA", isReelSymbolValid(data.getSymbolA().name()));
        Assert.assertTrue("Encountered unexpected symbols from data for SymbolB", isReelSymbolValid(data.getSymbolB().name()));
        Assert.assertTrue("Encountered unexpected symbols from data for SymbolC", isReelSymbolValid(data.getSymbolC().name()));

        boolean isResultSet = data.getResultWin() != null || data.getResultLose() != null;
        Assert.assertTrue("Results has not been initialized in for data", isResultSet);
    }
}
