package beans.reel;

public class ResultWin extends ReelResult{

    private ReelResultResponse result;
    private ReelResultWinnerReel winnerReel;
    private double amount;

    public ResultWin(ReelResultResponse result, ReelResultWinnerReel winnerReel, double amount){
        this.result = result;
        this.winnerReel = winnerReel;
        this.amount = amount;
    }

    public ReelResultResponse getResult() {
        return result;
    }

    public double getAmount() {
        return amount;
    }

    public ReelResultWinnerReel getWinnerReel() {
        return winnerReel;
    }
}
