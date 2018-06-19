package Users;

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

    /**
     * la variable statut permet de définir le statut utilisateur.
     */
    private String statut;


// --------------------------------------------------------------------------------------------------------------

    /**
     * Le constructeur permet d'initier les variables.
     *
     * @see User#login;
     * @see User#password;
     */

    public User() {
        login = "";
        password = "";
    }

// --------------------------------------------------------------------------------------------------------------


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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * La méthode showProfile permet d'afficher les données utilisateur.
     *
     * @param user;
     */
    public void showProfile(User user) {
        System.out.println("Votre profil : ");
        System.out.println("Votre nom d'utilisateur : " + user.getLogin());
        System.out.println("Votre mot de passe : " + user.getPassword());
    }

    /**
     * La méthode modifProfile permet à l'utilisateur de modifier son profil.
     */
    public void modifProfile() {
        System.out.println("Modifiez votre profil :");
        System.out.println("Modifiez votre nom :");
        setLogin(sc.nextLine());
        System.out.println("Votre nouveau nom : " + getLogin());
        System.out.print("Modifiez votre mot de passe :");
        setPassword(sc.next());
        System.out.print("Votre nouveau mot de passe : " + getPassword());
    }

    /**
     * La méthode disconnect termine le programme.
     */
    public void disconnect() {
        System.out.println("A bientôt !");
    }
}
