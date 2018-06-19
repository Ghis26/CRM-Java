package com.company;

import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);
    protected int choice = 0;

    protected void showMenu(User user, Product product) {
        do {
            System.out.println("Bonjour " + user.login + ", veuillez choisir parmi les propositions suivantes :");
            System.out.println("1 - Affichez votre profil");
            System.out.println("2 - Modifiez votre profil");
            System.out.println("3 - Créez un produit");
            System.out.println("4 - Affichez la liste des produits");
            System.out.println("5 - Quittez le programme");

            System.out.println("Votre choix :");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.showProfile(user);
                    break;
                case 2:
                    user.modifProfile();
                    break;
                case 3:
                    product.newProduct();
                    break;
                case 4:
                    product.showProduct();
                    break;
                case 5:
                    user.disconnect();
                    break;
                default:
                    user.disconnect();
                    break;
            }
        } while (choice < 5 && choice >0);
    }
}
