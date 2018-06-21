package fr.Users;

import fr.Basket.Basket;
import fr.Basket.BasketItem;
import fr.Basket.Productlist;

import java.util.Scanner;

/**
 * La classe fr.Users.Client définit les attributs d'un client
 *
 * @author Ghis
 * @version 1.0
 */

public class Client extends User {

    private Scanner sc = new Scanner(System.in);
    /**
     * La variable budget définit le montant du portefeuille du client à sa création.
     *
     * @see Client#getBudget;
     * @see Client#setBudget(double);
     */
    private double budget;

    /**
     * La variable basket est un tableau contenant l'ensemble des produits de l'objet fr.Basket.
     */
    private Basket basket;

    // --------------------------------------------------------------------------------------------------------------

    /**
     * Constructeur permettant d'initier le budget initial du client, ainsi qu'un tableau de 10 cases
     * (admettons qu'un panier ne peut contenir plus de 10 articles)
     */

    public Client() {
        super();
        budget = 100;
        basket = new Basket();
    }

    // --------------------------------------------------------------------------------------------------------------

    /**
     * Getter permettant de retourner le budget disponible du client.
     *
     * @return budget;
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Permet d'actualiser le budget du client.
     *
     * @param budget;
     */

    public void setBudget(double budget) {
        this.budget = budget;
    }


    public void showMenu() {
        boolean exitProject = false;
        int choice;

        do {
            super.showMenu();
            System.out.println("3 - Affichez la liste des produits");
            System.out.println("4 - Ajouter un produit au panier");
            System.out.println("5 - Voir votre panier");
            System.out.println("6 - Payez vos achats");
            System.out.println("7 - Réapprovisionnez votre porte-monnaie");
            System.out.println("8 - Quittez le programme\n");

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
                    showProduct();
                    exitProject = false;
                    break;
                case 4:
                    showProduct();
                    addtoCart();
                    exitProject = false;
                    break;
                case 5:
                    showCart();
                    exitProject = false;
                    break;
                case 6:
                    cartPayment();
                    exitProject = true;
                    break;
                case 7:
                    resupplyBudget();
                    exitProject = false;
                    break;
                default:
                    disconnect();
                    exitProject = true;
            }
        } while (!exitProject);
    }


    public void showProfile() {
        super.showProfile();
        System.out.println("Votre porte-monnaie : " + getBudget() + " €.\n");
    }


    private void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : \n");
        for (int i = 0; i < Productlist.getInstance().getProductCatalog().size(); i++) {
            System.out.println(Productlist.getInstance().getProductCatalog().get(i).toString() + "\n");
        }
    }

    /**
     * La méthode addToCart permet de créer un basketItem (id + quantité produit) + l'ajouter au panier.
     */
    private void addtoCart() {
         BasketItem basketItem = basket.createBasketItem();
        basket.cart.add(basketItem);
        basket.sum();
        System.out.println("l'ajout au panier a bien été fait ! Le montant total du panier s'élève à : " + basket.getTotalPrice() + " €\n");
    }


    /**
     * La méthode showCart permet d'afficher le panier en cours.
     */
    private void showCart() {
        System.out.println("Voici la liste des produits dans votre panier : \n");
        if (basket != null) {
            for (int i = 0; i < basket.cart.size(); i++) {
                System.out.println(basket.cart.get(i).toString() + "\n");
            }
        }
    }

    /**
     * La méthode cartPayment permet de régler le panier ainsi que de changer son statut.
     */
    private void cartPayment() {
        if (basket.getTotalPrice() <= getBudget()) {
            System.out.println("Votre panier a bien été reglé ! Merci !\n");
            basket.setStatus(Basket.allStatus.PAYE);
        } else {
            System.out.println("Veuillez réapprovisionner votre compte merci !\n");
            basket.setStatus(Basket.allStatus.ANNULE);

        }
    }

    /**
     * La méthode ressuplyBudget permet de réapprovisionner le porte-monnaie du client.
     */
    private void resupplyBudget() {
        double amount;
        System.out.println("Saisissez le montant que vous souhaitez ajouter à votre porte-monnaie : ");
        amount = sc.nextDouble();
        setBudget(amount + getBudget());
        System.out.println("Votre porte-monnaie est maintenant de " + getBudget() + " €.\n");
    }
}
