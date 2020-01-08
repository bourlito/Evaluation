package code.entity;

import java.util.ArrayList;
import java.util.List;

public class Employe {

    private int id;
    private String nom = "", prenom = "", surnom = "";
    private Stat saisie, lettrage, affectation, suspens, tva, revision,
            autonomie, partage, delais, objectifs,
            ponctualite, investissement, respect, management;
    private List<Commentaire> commentaires;
    private boolean erreur = false;

    public Employe(int id, String nom, String prenom, String surnom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.surnom = surnom;

        this.saisie = new Stat();
        this.lettrage = new Stat();
        this.affectation = new Stat();
        this.suspens = new Stat();
        this.tva = new Stat();
        this.revision = new Stat();
        this.autonomie = new Stat();
        this.partage = new Stat();
        this.delais = new Stat();
        this.objectifs = new Stat();
        this.ponctualite = new Stat();
        this.investissement = new Stat();
        this.respect = new Stat();
        this.management = new Stat();

        this.commentaires = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nom +" "+ prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSurnom() {
        return surnom;
    }

    public Stat getSaisie() {
        return saisie;
    }

    public Stat getLettrage() {
        return lettrage;
    }

    public Stat getAffectation() {
        return affectation;
    }

    public Stat getSuspens() {
        return suspens;
    }

    public Stat getTva() {
        return tva;
    }

    public Stat getRevision() {
        return revision;
    }

    public Stat getAutonomie() {
        return autonomie;
    }

    public Stat getPartage() {
        return partage;
    }

    public Stat getDelais() {
        return delais;
    }

    public Stat getObjectifs() {
        return objectifs;
    }

    public Stat getPonctualite() {
        return ponctualite;
    }

    public Stat getInvestissement() {
        return investissement;
    }

    public Stat getRespect() {
        return respect;
    }

    public Stat getManagement() {
        return management;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public boolean isErreur() {
        return erreur;
    }

    public void setErreur(boolean erreur) {
        this.erreur = erreur;
    }
}
