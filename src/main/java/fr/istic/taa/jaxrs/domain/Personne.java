package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

/**
 * La classe Personne est une classe de base qui contient des informations communes
 * sur une personne, telles que le nom et le prénom. Elle est annotée avec
 * @MappedSuperclass, ce qui signifie qu'elle ne sera pas directement persistée
 * en tant qu'entité dans la base de données, mais ses attributs seront hérités
 * par les autres classes d'entités.
 */
//@MappedSuperclass / ne permettrait pas de persister Personne elle-même mais de l'hériter dans d'autres entités.

@Entity
public class Personne implements Serializable {

    /**
     * Le nom de la personne.
     */
    private String nom;

    /**
     * Le prénom de la personne.
     */
    private String prenom;

    private Long id;
    private String email;

    /**
     * Constructeur de la classe Personne pour initialiser les attributs nom et prénom.
     *
     * @param nom Le nom de la personne.
     * @param prenom Le prénom de la personne.
     */

    public Personne(){}
    /**Constructeur sans argument : Il est souvent utile d'avoir un constructeur sans argument
     * pour JPA, si jamais tu utilises des frameworks comme Hibernate qui créent des instances par
     réflexion
     */
    public Personne(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}