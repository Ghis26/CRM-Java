package fr.Users;

import fr.Basket.Basket;
import fr.Basket.BasketItem;

import java.util.Scanner;

import static fr.DataBase.DataBase.*;

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
     * @see Client#setBudget(double);
     */
    private double budget;

    /**
     * La variable basket est un tableau contenant l'ensemble des produits de l'objet fr.Basket.
     */
    private Basket basket;

    /**
     * L'enum liste l'ensemble des choix possibles
     */
    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
        ADD_PRODUCT_TO_CART("Ajoutez un produit au panier"),
        SHOW_CART("Voir votre panier"),
        PAYMENT("Payez votre panier"),
        RESUPPLY("Réapprovisionnez votre porte-monnaie"),
        LEAVE("Quittez le programme");

        private String textChoice;

        AllChoices(String textChoice) {
            this.textChoice = textChoice;
        }

        public static AllChoices find(int value) {
            return values()[value];
        }

    }

    // --------------------------------------------------------------------------------------------------------------

    /**
     * Constructeur permettant d'initier le budget initial du client, ainsi qu'un tableau de 10 cases
     * (admettons qu'un panier ne peut contenir plus de 10 articles)
     *
     * @param login;
     * @param password;
     * @param status;
     */

    public Client(String login, String password, String status) {
        super(login, password, "client");
        basket = new Basket();

    }

    // --------------------------------------------------------------------------------------------------------------

    /**
     * Permet d'actualiser le budget du client.
     *
     * @param budget;
     */

    public void setBudget(double budget) {
        this.budget = budget;
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
                case ADD_PRODUCT_TO_CART:
                    addtoCart();
                    stat = true;
                    break;
                case SHOW_CART:
                    showCart();
                    stat = true;
                    break;
                case PAYMENT:
                    cartPayment();
                    stat = true;
                    break;
                case RESUPPLY:
                    resupplyBudget();
                    stat = true;
                    break;
                case LEAVE:
                    disconnect();
                    stat = false;
                    break;
            }
        }
    }

    public void showProfile() {
        super.showProfile();
        double budget = getCurrentBudget(getLogin());
        System.out.println("Votre porte-monnaie actuel s'élève à "+budget+ "€\n");
    }


    /**
     * La méthode addToCart permet de créer un basketItem (id + quantité produit) + l'ajouter au panier.
     */
    private void addtoCart() {
        showProduct();
        BasketItem basketItem = basket.createBasketItem();
        basket.basketItemToCart(basketItem);
    }

    /**
     * La méthode showCart permet d'afficher le panier en cours.
     */
    private void showCart() {
        System.out.println("Voici la liste des produits dans votre panier : \n");
        double total = basket.sum();
        System.out.println("Le montant de votre panier s'élève à : " + total + " €\n");
    }

    /**
     * La méthode cartPayment permet de régler le panier ainsi que de changer son statut.
     */
    private void cartPayment() {
        String login = getLogin();
        int idCart = searchCart(login);
        double totalPrice = basket.getTotalPrice();
        double budget = getCurrentBudget(login);
        if (budget > totalPrice) {
            payment(idCart, budget, totalPrice);
            System.out.println("Votre panier a bien été reglé. Merci !");
        } else {
            System.out.println("Solde insuffisant, veuillez réapprovisionner votre porte-monnaie.");
        }
    }

    /**
     * La méthode ressuplyBudget permet de réapprovisionner le porte-monnaie du client.
     */
    private void resupplyBudget() {
        double amount;
        String login = getLogin();
        System.out.println("Saisissez le montant que vous souhaitez ajouter à votre porte-monnaie : ");
        amount = sc.nextDouble();
        addBudget(getLogin(), amount);
        double currentBudget = getCurrentBudget(login);
        System.out.println("Votre porte-monnaie est maintenant de " + currentBudget + " €.\n");
    }


    /**
     * La méthode showChoices permet d'afficher les différents choix identifiés dans l'enum.
     */
    public void showChoices() {
        for (AllChoices menuChoice : AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
    }

    public void disconnect() {
        String login = getLogin();
        int idCart = searchCart(login);
        cancelCart(idCart);
        System.out.println("A bientôt !");
    }
}

