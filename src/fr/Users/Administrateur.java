package fr.Users;

import fr.Basket.Product;
import fr.Basket.Productlist;
import fr.DataBase.DataBase;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrateur extends User {
    private final String status;
    private Scanner sc = new Scanner(System.in);


    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_PRODUCT("Ajouter un produit à la liste"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
        SHOW_USERLIST("Voir la liste des utilisateurs"),
        LEAVE("Quittez le programme");

        private String textChoice;

        AllChoices(String textChoice) {
            this.textChoice = textChoice;
        }

        public static Administrateur.AllChoices find(int value) {
            return values()[value];
        }
    }

    public Administrateur() {
        super();
        status = "administrateur";
    }

    public String getStatut() {
        return status;
    }

    public void showChoices() {
        for (Administrateur.AllChoices menuChoice : Administrateur.AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
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
                case SHOW_PRODUCTLIST:
                    showProduct();
                    stat = true;
                    break;
                case ADD_PRODUCT:
                    newProduct();
                    stat = true;
                    break;
                case SHOW_USERLIST:
                    showUsers();
                    stat = true;
                    break;
                case LEAVE:
                    disconnect();
                    stat = false;
                    break;
            }
        }
    }

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
        System.out.println("Saisissez le prix de votre produit :");
        product.setPrice(sc.nextFloat());
        System.out.println("Saisissez la quantité initiale de produits en stock :");
        int quantity = sc.nextInt();
        Productlist.getInstance().addProduct(product, quantity);
        System.out.println("Votre produit a bien été créé !\n");
    }

    public void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : \n");
        for (Product product : Productlist.getInstance().getProductCatalog()) {
            System.out.println(product + "\n");
        }
    }

    private void showUsers(){
        System.out.println("Voici la liste des utilisateurs à jour : \n");
        for (User user : Userlist.getInstance().getUserCatalog()) {
            System.out.println(user.toString() +"\n");
        }
    }

    public String toString(){
        return "Nom : " +getLogin()+" - Password : " +getPassword()+ " - Statut : " +getStatut();
    }
}
