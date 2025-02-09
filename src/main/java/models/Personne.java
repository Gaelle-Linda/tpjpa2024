package models;

import jakarta.persistence.MappedSuperclass;

/**
 * La classe Personne est une classe de base qui contient des informations communes
 * sur une personne, telles que le nom et le prénom. Elle est annotée avec
 * @MappedSuperclass, ce qui signifie qu'elle ne sera pas directement persistée
 * en tant qu'entité dans la base de données, mais ses attributs seront hérités
 * par les autres classes d'entités.
 */
//@MappedSuperclass
public class Personne {

    /**
     * Le nom de la personne.
     */
    private String nom;

    /**
     * Le prénom de la personne.
     */
    private String prenom;

    /**
     * Constructeur de la classe Personne pour initialiser les attributs nom et prénom.
     *
     * @param nom Le nom de la personne.
     * @param prenom Le prénom de la personne.
     */

    //public Personne(){}
    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    /**
     * Retourne le nom de la personne.
     *
     * @return Le nom de la personne.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom de la personne.
     *
     * @param nom Le nouveau nom de la personne.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom de la personne.
     *
     * @return Le prénom de la personne.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Modifie le prénom de la personne.
     *
     * @param prenom Le nouveau prénom de la personne.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}