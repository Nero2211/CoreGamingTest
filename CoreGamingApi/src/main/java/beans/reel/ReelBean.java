package beans.reel;

public class ReelBean {
    private ReelSymbols symbolA;
    private ReelSymbols symbolB;
    private ReelSymbols symbolC;
    private ResultWin resultWin;
    private ResultLose resultLose;

    public ReelBean(ReelSymbols symbolA, ReelSymbols symbolB, ReelSymbols symbolC){
        this.symbolA = symbolA;
        this.symbolB = symbolB;
        this.symbolC = symbolC;
    }

    public ReelSymbols getSymbolA() {
        return symbolA;
    }

    public ReelSymbols getSymbolB() {
        return symbolB;
    }

    public ReelSymbols getSymbolC() {
        return symbolC;
    }

    public ResultWin getResultWin() {
        return resultWin;
    }

    public void setResultWin(ResultWin resultWin) {
        this.resultWin = resultWin;
    }

    public ResultLose getResultLose() {
        return resultLose;
    }

    public void setResultLose(ResultLose resultLose) {
        this.resultLose = resultLose;
    }
}
