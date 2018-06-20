package com.company;

import Users.Administrateur;
import Users.Client;
import Users.Commercial;
import Users.User;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Permet de récupérer les éléments inscrits par l'utilisateur
        User user = new User(); // Instancie un nouvel utilisateur
        Menu menu = new Menu(); // Instancie le menu
        String statut = " ";
        boolean statutOk;

        do {
            System.out.println("Êtes-vous client, commercial ou administrateur ?");
            statut = sc.nextLine();

            switch (statut) {
                case "client":
                    user = new Client();
                    user.setStatut(statut);
                    statutOk = true;
                    break;
                case "commercial":
                    user = new Commercial();
                    user.setStatut(statut);
                    statutOk = true;
                    break;
                case "administrateur":
                    user = new Administrateur();
                    user.setStatut(statut);
                    statutOk = true;
                    break;
                default:
                    System.out.println("Vous n'avez pas le bon statut");
                    statutOk = false;
            }
        } while (!statutOk);

        System.out.println("Veuillez saisir votre nom :");

        user.setLogin(sc.nextLine());

        System.out.println("Veuillez saisir votre mot de passe :");

        user.setPassword(sc.nextLine());

        menu.showMenu(user);
    }
}
