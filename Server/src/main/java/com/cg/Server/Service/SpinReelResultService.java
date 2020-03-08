package com.cg.Server.Service;


import beans.reel.*;

public class SpinReelResultService {

    private static final double ALL_ACES_PRIZE = 15.00;
    private static final double ALL_KINGS_PRIZE = 14.00;
    private static final double ALL_QUEENS_PRIZE = 13.00;
    private static final double ALL_JACKS_PRIZE = 12.00;
    private static final double TWO_ACES_PRIZE = 11.00;

    public static ReelBean getReelResults(ReelBean bean){

        if(isAllAces(bean)){
            bean.setResultWin(new ResultWin(
                    ReelResult.ReelResultResponse.WIN,
                    ReelResult.ReelResultWinnerReel.ALL_ACES,
                    ALL_ACES_PRIZE));
        }else if(isAllKings(bean)){
            bean.setResultWin(new ResultWin(
                    ReelResult.ReelResultResponse.WIN,
                    ReelResult.ReelResultWinnerReel.ALL_KINGS,
                    ALL_KINGS_PRIZE));
        }else if(isAllQueens(bean)){
            bean.setResultWin(new ResultWin(
                    ReelResult.ReelResultResponse.WIN,
                    ReelResult.ReelResultWinnerReel.ALL_QUEENS,
                    ALL_QUEENS_PRIZE));
        }else if(isAllJacks(bean)){
            bean.setResultWin(new ResultWin(
                    ReelResult.ReelResultResponse.WIN,
                    ReelResult.ReelResultWinnerReel.ALL_JACKS,
                    ALL_JACKS_PRIZE));
        }else if(isTwoAces(bean)){
            bean.setResultWin(new ResultWin(
                    ReelResult.ReelResultResponse.WIN,
                    ReelResult.ReelResultWinnerReel.TWO_ACES,
                    TWO_ACES_PRIZE));
        }else{
            bean.setResultLose(new ResultLose(ReelResult.ReelResultResponse.LOSE));
        }

        return bean;
    }

    private static boolean isAllAces(ReelBean bean){
        return bean.getSymbolA().equals(ReelSymbols.ACE) && bean.getSymbolB().equals(ReelSymbols.ACE) && bean.getSymbolC().equals(ReelSymbols.ACE);
    }

    private static boolean isAllKings(ReelBean bean){
        return bean.getSymbolA().equals(ReelSymbols.KING) && bean.getSymbolB().equals(ReelSymbols.KING) && bean.getSymbolC().equals(ReelSymbols.KING);
    }

    private static boolean isAllQueens(ReelBean bean){
        return bean.getSymbolA().equals(ReelSymbols.QUEEN) && bean.getSymbolB().equals(ReelSymbols.QUEEN) && bean.getSymbolC().equals(ReelSymbols.QUEEN);
    }

    private static boolean isAllJacks(ReelBean bean){
        return bean.getSymbolA().equals(ReelSymbols.JACK) && bean.getSymbolB().equals(ReelSymbols.JACK) && bean.getSymbolC().equals(ReelSymbols.JACK);
    }

    private static boolean isTwoAces(ReelBean bean){
        boolean symbolA = bean.getSymbolA().equals(ReelSymbols.ACE);
        boolean symbolB = bean.getSymbolB().equals(ReelSymbols.ACE);
        boolean symbolC = bean.getSymbolC().equals(ReelSymbols.ACE);

        return symbolA ? (symbolB || symbolC) : (symbolB && symbolC);
    }
}
