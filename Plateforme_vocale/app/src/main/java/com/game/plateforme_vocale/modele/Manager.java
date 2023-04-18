package com.game.plateforme_vocale.modele;

import com.game.plateforme_vocale.data.Sauveur;
import com.game.plateforme_vocale.data.Stub;
import com.game.plateforme_vocale.data.ser.SauveurSer;
import com.game.plateforme_vocale.modele.Boucleur.Boucleur;
import com.game.plateforme_vocale.modele.Boucleur.BoucleurSimple;
import com.game.plateforme_vocale.modele.Deplaceur.DeplaceurPlateforme;
import com.game.plateforme_vocale.modele.Deplaceur.DeplaceurJoueur;
import com.game.plateforme_vocale.modele.entites.Personnage;
import com.game.plateforme_vocale.modele.entites.Plateforme;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    private static Manager instanceUnique;

    private ArrayList<Plateforme> lesPlateformes;

    private Boucleur monBoucleur = new BoucleurSimple();;
    private DeplaceurPlateforme monDeplaceur = new DeplaceurPlateforme();
    private DeplaceurJoueur monDeplaceurJoueur = new DeplaceurJoueur();
    private Personnage joueurCourant;
    private Collisionneur monColisionneur;
    private List<Score> lesScores = new ArrayList<Score>();
    private int currentScore = 0;
    private int iteratorScore = 0;
    private final Sauveur leSauveur = new SauveurSer();
    public static final String NAME_FILE = "historique_Score";

    /**
     * constructeur de Manager
     * => appel lancerPartie
     * => charge la collection de plateforme et le joueur
     */
    public Manager() {
        lancerPartie();
        lesPlateformes = Stub.loadPlateform();
        joueurCourant = Stub.loadJoueur();
        monColisionneur = new Collisionneur();
    }

    /**
     * Singleton => permet de d'instancier le manager ou de récupérer l'unique manager si il a déjà été instancier
     * @return l'instance unique de manager
     */
    public static Manager getInstance() {
        if (instanceUnique == null) {
            instanceUnique = new Manager();
        }
        return instanceUnique;
    }

    /**
     * permet d'appeler le collisionneur pour vérifier si le joueur peut aller à droite ou à gauche
     * @return true si il y a collision et false si il n'y a pas de collision
     */
    public boolean isPresentUnderOrForward() {
        return monColisionneur.isPresentUnderOrForward(lesPlateformes, joueurCourant, monDeplaceurJoueur.getRatio() / 2);
    }

    /**
     * permet d'appeler le collisionneur pour vérifier si le joueur peut tomber plus bas
     * @return true si il y a collision et false si il n'y a pas de collision
     */
    public boolean isPresentUnder() {
        return monColisionneur.isPresentUnder(lesPlateformes, joueurCourant, monDeplaceurJoueur.getRatio() / 2);
    }

    /**
     * permet de déplacer une plateforme et d'incrémenter le score en fonction du déplacement
     * @param p => plateforme à déplacer
     * @param z => valeur de l'accéréromètre
     */
    public void avancer(Plateforme p, float z) {
        if (iteratorScore > 100) {
            if (z < 0)
                incrementScore();
            if (z > 0)
                decrementScore();
        } else iteratorScore++;
        monDeplaceur.avancer(p, z);
    }

    /**
     * permet de d'incrémenter le score du joueur courant (si il revient en arrière)
     */
    public void incrementScore() {
        this.currentScore += 1;
        this.iteratorScore = 0;
    }

    /**
     * permet de décrémenter le score du joueur courant (si il revient en arrière)
     */
    public void decrementScore() {
        if (currentScore < 0)
            this.currentScore -= 1;
        this.iteratorScore = 0;
    }

    /**
     * permet d'ajouter le score à la collection de score à la fin d'une partie
     * @param j => nom du joueur courant
     */
    public void addScore(String j) {
        lesScores.add(new Score(currentScore, j));
    }

    /**
     * permet de charger les données à partir d'un fichier
     */
    public void chargerDonnees(FileInputStream file) {
        lesScores = leSauveur.chargerStats(file);
    }

    /**
     * permet de sauvegarder les données dans un fichier
     */
    public void sauvegarderDonnees(FileOutputStream file) {
        leSauveur.sauvegarderStats(lesScores, file);
    }

    /**
     * permet de lancer la  boucle de jeu
     */
    public void lancerPartie(){
        monBoucleur.setRunning(true);
        new Thread(monBoucleur).start();
    }

    /**
     * permet de remettre les éléments à la position initiale pour recommencer une partie et de remettre le score à 0
     */
    public void restart(){
        lesPlateformes = Stub.loadPlateform();
        joueurCourant = Stub.loadJoueur();
        currentScore = 0;
    }

    /**
     * permet d'arreter la boucle de jeu
     */
    public void pausePartie(){
        monBoucleur.setRunning(false);
    }

    /**
     * permet de retourner le score du joueur courant
     * @return le score du joueur courant
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * permet de retourner le boucleur
     * @return le boucleur
     */
    public Boucleur getBoucleur() {
        return monBoucleur;
    }

    /**
     * permet de retourner le deplaceur de la plateforme
     * @return le deplaceur
     */
    public DeplaceurPlateforme getMonDeplaceur() {
        return monDeplaceur;
    }

    /**
     * permet de retourner la collection de plateformes
     * @return la collection de plateformes
     */
    public ArrayList<Plateforme> getLesPlateformes() {
        return lesPlateformes;
    }

    /**
     *  Permet de retourner le joueur courant
     * @return le joueur courant
     */
    public Personnage getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * permet de setter le joueur courant
     * @param joueurCourant => reçoit le joueur à setter
     */
    public void setJoueurCourant(Personnage joueurCourant) {
        this.joueurCourant = joueurCourant;
    }

    /**
     * permet de retourner le déplaceur du joueur
     * @return le déplaceur du joueur
     */
    public DeplaceurJoueur getMonDeplaceurJoueur() {
        return monDeplaceurJoueur;
    }

    /**
     * permet de retourner le collisionneur
     * @return le collisionneur
     */
    public Collisionneur getMonColisionneur() {
        return monColisionneur;
    }

    /**
     * permet de retourner la collection de score
     * @return la collection de score
     */
    public List<Score> getLesScores() {
        return lesScores;
    }
}
