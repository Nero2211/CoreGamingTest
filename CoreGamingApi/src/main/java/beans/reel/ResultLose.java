package beans.reel;

public class ResultLose extends ReelResult{

    private ReelResultResponse result;

    public ResultLose(ReelResultResponse result){
        this.result = result;
    }

    public ReelResultResponse getResult() {
        return result;
    }
}
