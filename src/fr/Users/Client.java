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
        REMOVE_PRODUCT("Retirez un produit du panier"),
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
        String login = getLogin();
        int idCart = searchCart(login);

        while (stat) {
            showChoices();

            System.out.println("Votre choix :");

            userChoice = AllChoices.find(sc.nextInt());
            switch (userChoice) {
                case SHOW_PROFILE:
                    showProfile(login);
                    stat = true;
                    break;
                case MODIF_PROFILE:
                    modifProfile();
                    stat = true;
                    break;
                case SHOW_PRODUCTLIST:
                    System.out.println("Voici les produits disponibles : \n");
                    showProduct();
                    stat = true;
                    break;
                case ADD_PRODUCT_TO_CART:
                    addtoCart(idCart);
                    stat = true;
                    break;
                case REMOVE_PRODUCT:
                    deleteProduct(idCart);
                    stat = true;
                    break;
                case SHOW_CART:
                    showCart(idCart);
                    stat = true;
                    break;
                case PAYMENT:
                    cartPayment(login, idCart);
                    stat = true;
                    break;
                case RESUPPLY:
                    resupplyBudget(login);
                    stat = true;
                    break;
                case LEAVE:
                    disconnect(idCart);
                    stat = false;
                    break;
            }
        }
    }

    // --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showChoices permet d'afficher les différents choix identifiés dans l'enum.
     */
    public void showChoices() {
        for (AllChoices menuChoice : AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
    }


    /**
     * Méthode permettant d'afficher le profil utilisateur.
     * @param login;
     */
    public void showProfile(String login) {
        super.showProfile();
        double budget = getCurrentBudget(login);
        System.out.println("Votre porte-monnaie actuel s'élève à " + budget + "€\n");
    }


    /**
     * La méthode addToCart permet de créer un basketItem (id + quantité produit) + l'ajouter au panier.
     * @param idCart;
     */
    private void addtoCart(int idCart) {
        System.out.println("Voici les produits disponibles : \n");
        showProduct();
        BasketItem basketItem = basket.createBasketItem();
        basket.basketItemToCart(basketItem, idCart);
    }

    /**
     * La méthode showCart permet d'afficher le panier en cours.
     */
    private void showCart(int idCart) {
        System.out.println("Voici la liste des produits dans votre panier : \n");
        double totalPrice = showAllCart(idCart);
        System.out.println("Le montant total de votre panier s'élève à " + totalPrice + " €\n");
    }

    /**
     * La méthode cartPayment permet de régler le panier ainsi que de changer son statut.
     */
    private void cartPayment(String login, int idCart) {
        System.out.println("Voici le récapitulatif de votre panier : ");
        double totalPrice = showAllCart(idCart);
        System.out.println("Total de votre panier : "+totalPrice +"€\n");
        double budget = getCurrentBudget(login);
        if (budget > totalPrice) {
            payment(idCart, budget, totalPrice);
            System.out.println("Votre panier a bien été reglé. Merci !");
        } else {
            System.out.println("Solde insuffisant, veuillez réapprovisionner votre porte-monnaie.");
        }
    }

    /**
     * Méthode permettant de retirer un produit du panier.
     */
    private void deleteProduct(int idCart) {
        showCart(idCart);
        System.out.println("Saisissez la référence du produit que vous souhaitez retirer du panier : ");
        int refProduct = sc.nextInt();
        removeProduct(refProduct);
    }


    /**
     * La méthode permet de réapprovisionner le porte-monnaie du client.
     */
    private void resupplyBudget(String login) {
        double amount;
        System.out.println("Saisissez le montant que vous souhaitez ajouter à votre porte-monnaie : ");
        amount = sc.nextDouble();
        addBudget(login, amount);
        double currentBudget = getCurrentBudget(login);
        System.out.println("Votre porte-monnaie est maintenant de " + currentBudget + " €.\n");
    }


    /**
     * Méthode permettant la sortie du programme.
     * @param idCart;
     */
    public void disconnect(int idCart) {
        cancelCart(idCart);
        System.out.println("A bientôt !");
    }
}

