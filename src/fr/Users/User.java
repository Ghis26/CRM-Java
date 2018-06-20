package fr.Users;

import java.util.Scanner;

/**
 * La classe User est la classe parente des autres utilisateurs.
 *
 * @author Ghis
 * @version 1.0
 */

public class User {

    /**
     * La variable sc permet de capter les entrées utilisateur.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * La variable login correspond au login utilisateur.
     */
    private String login;

    /**
     * La variable password correspond au password utilisateur.
     */
    private String password;

    public double budget;

// --------------------------------------------------------------------------------------------------------------

    /**
     * Le constructeur permet d'initier les variables.
     */

    public User() {
        this.login = "";
        this.password = "";
        this.budget = 0;
    }

    // --------------------------------------------------------------------------------------------------------------
    public String getStatut() {
        return "";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getBudget() {
        return 0;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    // --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showProfile permet d'afficher les données utilisateur.
     *
     * @param user;
     */
    public void showProfile(User user) {
        System.out.println("Votre profil : \n");
        System.out.println("Votre nom d'utilisateur : " + user.getLogin() + "\n");
        System.out.println("Votre mot de passe : " + user.getPassword() + "\n");
        System.out.println("Statut : " + user.getStatut() + "\n");
        System.out.println("Votre porte-monnaie : " + user.getBudget() + " €.\n");
    }

    /**
     * La méthode modifProfile permet à l'utilisateur de modifier son profil.
     */
    public void modifProfile() {
        System.out.println("Modifiez votre profil :\n");
        System.out.println("Modifiez votre nom :");
        setLogin(sc.nextLine());
        System.out.println("Votre nouveau nom : " + getLogin() + "\n");
        System.out.print("Modifiez votre mot de passe :");
        setPassword(sc.next());
        System.out.print("Votre nouveau mot de passe : " + getPassword() + "\n");
    }

    /**
     * La méthode disconnect termine le programme.
     */
    public void disconnect() {
        System.out.println("A bientôt !");
    }
}
