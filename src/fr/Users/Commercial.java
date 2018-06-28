package fr.Users;

import fr.Basket.Product;
import fr.Basket.Productlist;
import fr.DataBase.DataBase;
import fr.Stock.Stock;
import fr.company.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Commercial extends User {
    private final String status;
    private Scanner sc;

    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_PRODUCT("Ajouter un produit à la liste"),
        RESUPPLY_STOCK("Réapprovisionnez le stock produit"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
        SHOW_STOCK("Voir le stock disponible"),
        LEAVE("Quittez le programme");

        private String textChoice;

        AllChoices(String textChoice) {
            this.textChoice = textChoice;
        }

        public static Commercial.AllChoices find(int value) {
            return values()[value];
        }
    }


    public Commercial() {
        super();
        status = "commercial";
        sc = new Scanner(System.in);
    }

    public String getStatut() {
        return status;
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
                    newProduct();
                    stat = true;
                    break;
                case RESUPPLY_STOCK:
                    resupplyStock();
                    break;
                case SHOW_PRODUCTLIST:
                    showProduct();
                    stat = true;
                    break;
                case SHOW_STOCK:
                    Productlist.getInstance().displayStock();
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

    /**
     * La méthode permet de réapprovisionner le stock
     */
    private void resupplyStock() {
        Scanner sc = new Scanner(System.in);
        Stock stockProducts = Productlist.getInstance().getStockProducts();
        System.out.println("Veuillez saisir l'ID du produit : ");
        int idProduct = sc.nextInt();
        Product product = findProduct(idProduct);
        System.out.println("Saisissez le nombre de produits à ajouter au stock :");
        int nbItem = sc.nextInt();
        stockProducts.addToStock(product.getName(), nbItem);
    }

    /**
     * La méthode permet de trouver un produit dans le catalogue produit
     *
     * @param idProduct;
     * @return product;
     */
    private Product findProduct(int idProduct) {
        for (Product product : Productlist.getInstance().getProductCatalog()) {
            if (product.getId() == idProduct) {
                return product;
            }
        }
        return null;
    }
}
