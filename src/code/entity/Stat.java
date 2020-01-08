package code.entity;

public class Stat {

    private int nb = 0;
    private double total;

    public Stat() {}

    public void add(double value) {
        if (value == -1) return;

        this.total += value;
        this.nb++;
    }

    public double getMoyenne() {
        return nb != 0 ? total / nb : -1;
    }
}
