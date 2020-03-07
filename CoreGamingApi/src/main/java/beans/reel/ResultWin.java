package beans.reel;

public class ResultWin extends ReelResult{

    private String result;
    private double amount;

    public ResultWin(String result, double amount){
        this.result = result;
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public double getAmount() {
        return amount;
    }
}
