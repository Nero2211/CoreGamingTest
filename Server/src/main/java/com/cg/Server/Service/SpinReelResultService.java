package com.cg.Server.Service;

import beans.reel.ReelSymbols;
import beans.reel.ReelBean;
import beans.reel.ResultLose;
import beans.reel.ResultWin;

public class SpinReelResultService {

    private static final String WIN = "YOU WON, GO AGAIN?";
    private static final String LOSE = "YOU LOSE, BETTER LUCK NEXT TIME";
    private static final double ALL_ACES_PRIZE = 15.00;
    private static final double ALL_KINGS_PRIZE = 14.00;
    private static final double ALL_QUEENS_PRIZE = 13.00;
    private static final double ALL_JACKS_PRIZE = 12.00;
    private static final double TWO_ACES_PRIZE = 11.00;

    public static ReelBean getReelResults(ReelBean bean){

        if(isAllAces(bean)){
            bean.setResult(new ResultWin(WIN, ALL_ACES_PRIZE));
        }else if(isAllKings(bean)){
            bean.setResult(new ResultWin(WIN, ALL_KINGS_PRIZE));
        }else if(isAllQueens(bean)){
            bean.setResult(new ResultWin(WIN, ALL_QUEENS_PRIZE));
        }else if(isAllJacks(bean)){
            bean.setResult(new ResultWin(WIN, ALL_JACKS_PRIZE));
        }else if(isTwoAces(bean)){
            bean.setResult(new ResultWin(WIN, TWO_ACES_PRIZE));
        }else{
            bean.setResult(new ResultLose(LOSE));
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
