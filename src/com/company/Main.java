package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Permet de récupérer les éléments inscrits par l'utilisateur
        User user = new User(); // Instancie un nouvel user
        Menu menu = new Menu(); // Instancie le menu


        System.out.println("Veuillez saisir votre nom :");

        user.setLogin(sc.nextLine());

        System.out.println("Veuillez saisir votre mot de passe :");

        user.setPassword(sc.nextLine());

        menu.showMenu(user);

    }
}
