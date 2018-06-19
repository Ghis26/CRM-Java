package com.company;

import Basket.Product;
import Users.User;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe Menu identifie le menu principal du programme.
 *
 * @author Ghis
 * @version 1.0
 */


public class Menu {

    private Scanner sc = new Scanner(System.in);

    /**
     * la variable choice stocke le choix de l'utilisateur.
     */
    private int choice;


    /**
     * la variable exitProject définit la sortie du programme ou non.
     */
    private boolean exitProject;

    /**
     * La variable productList définit la liste de tous les produits créés.
     */
    private ArrayList<Product> productList = new ArrayList<>(20);

// --------------------------------------------------------------------------------------------------------------

    /**
     * Constructeur permettant d'initier les variables du Menu
     *
     * @see Menu#choice;
     * @see Menu#exitProject;
     */

    public Menu() {
        choice = 0;
        exitProject = false;
    }

// --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showMenu permet d'afficher le menu des choix
     *
     * @param user;
     */

    public void showMenu(User user) {
        do {
            System.out.println(user.getLogin() + ", veuillez choisir parmi les propositions suivantes :");
            System.out.println("1 - Affichez votre profil");
            System.out.println("2 - Modifiez votre profil");
            if (user.getStatut().equals("commercial")) {
                System.out.println("3 - Créez un produit");
            }
            if (user.getStatut().equals("client")) {
                System.out.println("4 - Affichez la liste des produits");
            }
            System.out.println("5 - Quittez le programme");

            System.out.println("Votre choix :");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.showProfile(user);
                    exitProject = false;
                    break;
                case 2:
                    user.modifProfile();
                    exitProject = false;
                    break;
                case 3:
                    if (user.getStatut().equals("commercial")) {
                        newProduct();
                        exitProject = false;
                        break;
                    }else{
                        System.out.println("Vous n'avez pas l'autorisation");
                        exitProject = false;
                        break;
                    }
                case 4:
                    if (user.getStatut().equals("client")) {
                        showProduct();
                        exitProject = false;
                        break;
                    }else{
                        System.out.println("Vous n'avez pas l'autorisation");
                        exitProject = false;
                        break;
                    }
                default:
                    user.disconnect();
                    exitProject = true;
            }
        } while (!exitProject);
    }

    /**
     * La méthode newProduct permet d'instancier un nouveau produit
     */
    private void newProduct() {
        Product product = new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez l'ID de votre produit :");
        product.setId(sc.nextInt());
        System.out.println("Saisissez le nom de votre produit :");
        product.setName(sc.next());
        System.out.println("Saisissez le nombre de produits en stock :");
        product.setStock(sc.nextInt());
        System.out.println("Saisissez le prix de votre produit :");
        product.setPrice(sc.nextFloat());
        productList.add(product);
        System.out.println("Votre produit a bien été créé !");
    }

    /**
     *La méthode showProduct permet d'afficher l'ensemble des produits créés
     */
    private void showProduct() {
        String newLine = System.getProperty("line.separator");
        System.out.println("Voici la liste des produits créés à ce jour : " + newLine);
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString() + newLine);
        }
    }
}
