package code.entity;

public class Commentaire {

    private String evaluateur, value;

    public Commentaire(String evaluateur, String value) {
        this.evaluateur = evaluateur;
        this.value = value;
    }

    @Override
    public String toString() {
        return evaluateur + ": " + value;
    }
}
