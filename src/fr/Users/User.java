package fr.Users;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * La classe User est la classe parente des autres utilisateurs.
 *
 * @author Ghis
 * @version 1.0
 */

public abstract class User implements InterfaceUser{

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

    private Map<Integer, Choice> choices;


// --------------------------------------------------------------------------------------------------------------

    /**
     * Le constructeur permet d'initier les variables.
     */

    public User() {
        this.login = "";
        this.password = "";
        choices = new HashMap<Integer, Choice>();
        this.initChoices();
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

   public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    // --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showProfile permet d'afficher les données utilisateur.
     */
    public void showProfile() {
        System.out.println("Votre profil : \n");
        System.out.println("Votre nom d'utilisateur : " + getLogin() + "\n");
        System.out.println("Votre mot de passe : " + getPassword() + "\n");
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


    public void showMenu() {

        System.out.println(getLogin() + ", veuillez choisir parmi les propositions suivantes :\n");
        System.out.println("1 - Affichez votre profil");
        System.out.println("2 - Modifiez votre profil");
    }

     public abstract void initChoices();
}
