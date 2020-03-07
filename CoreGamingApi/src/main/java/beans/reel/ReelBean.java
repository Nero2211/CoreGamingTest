package beans.reel;

public class ReelBean {
    private ReelSymbols symbolA;
    private ReelSymbols symbolB;
    private ReelSymbols symbolC;
    private ReelResult result;

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

    public ReelResult getResult() {
        return result;
    }

    public void setResult(ReelResult result) {
        this.result = result;
    }
}
