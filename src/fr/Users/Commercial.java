package fr.Users;

import fr.Basket.Product;
import fr.Basket.Productlist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Commercial extends User {
    private final String statut;

    private Scanner sc = new Scanner(System.in);

    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_PRODUCT("Ajouter un produit à la liste"),
        RESUPPLY_STOCK("Réapprovisionnez le stock produit"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
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
        statut = "commercial";
    }

    public String getStatut() {
        return statut;
    }

    /**
     * La méthode newProduct permet d'instancier un nouveau produit
     */
    private void newProduct() {
        Product product = new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez l'ID de votre produit :");
        try {
            product.setId(sc.nextInt());
        } catch (InputMismatchException i) {
            System.out.print(i + "\n");
            product.setId(1);
        }
        System.out.println("Saisissez le nom de votre produit :");
        product.setName(sc.next());
        System.out.println("Saisissez le nombre de produits en stock :");
        product.setStock(sc.nextInt());
        System.out.println("Saisissez le prix de votre produit :");
        product.setPrice(sc.nextFloat());
        Productlist.getInstance().addProduct(product);
        System.out.println("Votre produit a bien été créé !\n");
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

    public void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : \n");
        for (Product product : Productlist.getInstance().getProductCatalog()) {
            System.out.println(product + "\n");
        }
    }

    private void resupplyStock() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir l'ID du produit : ");
        int idProduct = sc.nextInt();
        Product product = findProduct(idProduct);
        System.out.println("Saisissez le nombre de produits à ajouter au stock :");
        product.setStock(product.getStock() + sc.nextInt());
        System.out.println("Le nouveau stock du produit " + product.getName() + " est de " + product.getStock());
    }

    private Product findProduct(int idProduct) {
        for (int i = 0; i < Productlist.getInstance().getProductCatalog().size(); i++) {
            if (Productlist.getInstance().getProductCatalog().get(i).getId() == idProduct) {
                return Productlist.getInstance().getProductCatalog().get(i);
            }
        }
        return null;
    }
}

