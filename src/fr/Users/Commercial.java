package fr.Users;

import fr.Basket.Product;

import java.util.Scanner;

import static fr.DataBase.DataBase.*;
import static fr.company.Main.disconnect;


public class Commercial extends User {
    private Scanner sc;

    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_PRODUCT("Ajouter un produit à la liste"),
        RESUPPLY_STOCK("Réapprovisionnez le stock produit"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
        SHOW_STOCK("Voir le stock disponible"),
        SHOW_CARTS("Voir les commandes et leur statut"),
        LEAVE("Quittez le programme");

        private String textChoice;

        AllChoices(String textChoice) {
            this.textChoice = textChoice;
        }

        public static Commercial.AllChoices find(int value) {
            return values()[value];
        }
    }


    public Commercial(String login, String password, String status) {
        super(login, password, "commercial");
        sc = new Scanner(System.in);
    }

    public void showMenu() {
        boolean stat = true;
        AllChoices userChoice;

        while (stat) {
            showChoices();

            System.out.println("Votre choix :");

            userChoice = AllChoices.find(sc.nextInt());
            switch (userChoice) {
                case SHOW_PROFILE:
                    showProfile();
                    stat = true;
                    break;
                case MODIF_PROFILE:
                    modifProfile();
                    stat = true;
                    break;
                case ADD_PRODUCT:
                    addNewProduct();
                    stat = true;
                    break;
                case RESUPPLY_STOCK:
                    System.out.println("Veuillez saisir la référence produit : ");
                    int refProduct = sc.nextInt();
                    System.out.println("Saisissez le nombre de produits à ajouter au stock :");
                    int nbItem = sc.nextInt();
                    resupplyStock(refProduct, nbItem);
                    break;
                case SHOW_PRODUCTLIST:
                    showProduct();
                    stat = true;
                    break;
                case SHOW_STOCK:
                    showStock();
                    stat = true;
                    break;
                case SHOW_CARTS:
                    showCarts();
                    stat = true;
                    break;
                case LEAVE:
                    disconnect();
                    stat = false;
                    break;
            }
        }
    }

    public void showChoices() {
        for (Commercial.AllChoices menuChoice : Commercial.AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
    }

    private void addNewProduct() {
        Scanner sc = new Scanner(System.in);
        String nameProduct;
        int refProduct;
        double priceProduct;

        new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez le nom de votre produit :");
        nameProduct = sc.next();
        System.out.println("Saisissez la référence produit :");
        refProduct = sc.nextInt();
        System.out.println("Saisissez le prix de votre produit :");
        priceProduct = sc.nextDouble();
        System.out.println("Saisissez la quantité initiale de produits en stock :");
        int quantity = sc.nextInt();

        newProduct(nameProduct, refProduct, priceProduct, quantity);
    }
}
