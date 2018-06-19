package com.company;

import java.util.Scanner;

/**
 * La classe User est la classe parente des autres utilisateurs.
 *
 * @author Ghis
 * @version 1.0
 */

public class User {
    private Scanner sc = new Scanner(System.in);
    private String login;
    private String password;

    public User(){
        login   = "";
        password = "";
    }

    public String getLogin()
    {
        return login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void disconnect(){
        System.out.println("A bientôt !");
    }

    public void showProfile(User user){
        System.out.println("Votre profil : ");
        System.out.println("Votre nom d'utilisateur : "+user.getLogin());
        System.out.println("Votre mot de passe : "+user.getPassword());
    }

    public void modifProfile(){
        System.out.println("Modifiez votre profil :");
        System.out.println("Modifiez votre nom :");
        setLogin(sc.nextLine());
        System.out.println("Votre nouveau nom : "+getLogin());
        System.out.print("Modifiez votre mot de passe :");
        setPassword(sc.next());
        System.out.print("Votre nouveau mot de passe : "+getPassword());
    }
}
