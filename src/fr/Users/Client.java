package fr.Users;

import fr.Basket.Basket;
import fr.Basket.BasketItem;
import fr.Basket.Product;
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
        System.out.println("Votre porte-monnaie : " + getBudget() + " €.\n");
    }


    public void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : \n");
        for (Product product : Productlist.getInstance().getProductCatalog()) {
            System.out.println(product + "\n");
        }
    }

    /**
     * La méthode addToCart permet de créer un basketItem (id + quantité produit) + l'ajouter au panier.
     */
    private void addtoCart() {
        showProduct();
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


    /**
     * La méthode showChoices permet d'afficher les différents choix identifiés dans l'enum.
     */
    public void showChoices() {
        for (AllChoices menuChoice : AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
    }
}

