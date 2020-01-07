package code.entity;

import java.util.List;

public class Employe {

    private int id;
    private String nom, prenom;
    private Stat saisie, lettrage, affectation, suspens, tva, revision,
            autonomie, partage, delais, objectifs,
            ponctualite, investissement, respect, management;
    private List<Commentaire> commentaires;

    public Employe(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Stat getSaisie() {
        return saisie;
    }

    public void setSaisie(Stat saisie) {
        this.saisie = saisie;
    }

    public Stat getLettrage() {
        return lettrage;
    }

    public void setLettrage(Stat lettrage) {
        this.lettrage = lettrage;
    }

    public Stat getAffectation() {
        return affectation;
    }

    public void setAffectation(Stat affectation) {
        this.affectation = affectation;
    }

    public Stat getSuspens() {
        return suspens;
    }

    public void setSuspens(Stat suspens) {
        this.suspens = suspens;
    }

    public Stat getTva() {
        return tva;
    }

    public void setTva(Stat tva) {
        this.tva = tva;
    }

    public Stat getRevision() {
        return revision;
    }

    public void setRevision(Stat revision) {
        this.revision = revision;
    }

    public Stat getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(Stat autonomie) {
        this.autonomie = autonomie;
    }

    public Stat getPartage() {
        return partage;
    }

    public void setPartage(Stat partage) {
        this.partage = partage;
    }

    public Stat getDelais() {
        return delais;
    }

    public void setDelais(Stat delais) {
        this.delais = delais;
    }

    public Stat getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(Stat objectifs) {
        this.objectifs = objectifs;
    }

    public Stat getPonctualite() {
        return ponctualite;
    }

    public void setPonctualite(Stat ponctualite) {
        this.ponctualite = ponctualite;
    }

    public Stat getInvestissement() {
        return investissement;
    }

    public void setInvestissement(Stat investissement) {
        this.investissement = investissement;
    }

    public Stat getRespect() {
        return respect;
    }

    public void setRespect(Stat respect) {
        this.respect = respect;
    }

    public Stat getManagement() {
        return management;
    }

    public void setManagement(Stat management) {
        this.management = management;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}
