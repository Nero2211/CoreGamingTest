package bean;

public class BasicWeightBean {

    private double value;
    private double chance;

    public BasicWeightBean(double value){
        this.value = value;
    }

    public BasicWeightBean(double value, double chance){
        this.value = value;
        this.chance = chance;
    }

    public double getValue() {
        return value;
    }

    public double getChance() {
        return chance;
    }

}
