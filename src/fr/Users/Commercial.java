package fr.Users;

import fr.Basket.Product;
import fr.Basket.Productlist;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Commercial extends User {
    private final String statut;

    private Scanner sc = new Scanner(System.in);


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
        boolean exitProject = false;
        int choice;

        do {
            super.showMenu();
            System.out.println("3 - Créée un nouveau produit");
            System.out.println("4 - Modifiez les stocks des produits");
            System.out.println("5 - Quittez le programme");
            System.out.println("Votre choix :");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showProfile();
                    exitProject = false;
                    break;
                case 2:
                    modifProfile();
                    exitProject = false;
                    break;
                case 3:
                    newProduct();
                    exitProject = false;
                    break;
                case 4:
                    exitProject = false;
                    break;
                default:
                    disconnect();
                    exitProject = true;
            }
        } while (!exitProject);
    }
}

