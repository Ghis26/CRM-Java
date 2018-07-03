package fr.Users;

import fr.Utilities.Choice;
import fr.Utilities.InterfaceUser;

import java.util.Scanner;

/**
 * La classe User est la classe parente des autres utilisateurs.
 *
 * @author Ghis
 * @version 1.0
 */

public abstract class User implements InterfaceUser, Choice {

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

    private String status;
// --------------------------------------------------------------------------------------------------------------

    public User(String uLogin, String uPassword, String uStatus){
        login = uLogin;
        password = uPassword;
        status = uStatus;
    }

    // --------------------------------------------------------------------------------------------------------------
    public String getStatut() {
        return status;
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

    public abstract void showMenu();

    public abstract void showChoices();
}
