package code.entity;

public class Stat {

    private int nb;
    private double total;

    public Stat() {}

    public double getMoyenne() {
        return total / nb;
    }

    public void add(double value) {
        this.total += value;
        this.nb++;
    }
}
