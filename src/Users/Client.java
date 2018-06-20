package Users;


import java.util.Scanner;

/**
 * La classe Users.Client définit les attributs d'un client
 *
 * @author Ghis
 * @version 1.0
 */

public class Client extends User {

    Scanner sc = new Scanner(System.in);
    /**
     * La variable budget définit le montant du portefeuille du client à sa création.
     *
     * @see Client#getBudget;
     * @see Client#setBudget(double);
     */
    private double budget;

    /**
     * La variable basket est un tableau contenant l'ensemble des produits de l'objet Basket.Basket.
     *
     * @see Client#getBasket;
     * @see Client#setBasket(String[]);
     */
    private String basket[];


    private final String statut = "client";

    // --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur permettant d'initier le budget initial du client, ainsi qu'un tableau de 10 cases
     * (admettons qu'un panier ne peut contenir plus de 10 articles)
     *
     */

    public Client() {
        super();
        budget = 100;
        basket = new String[10];
    }

    // --------------------------------------------------------------------------------------------------------------

    public String getStatut() {
        return statut;
    }

    /**
     * Getter permettant de retourner le budget disponible du client.
     *
     * @return budget;
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Getter permettant de retourner un tableau contenant le panier du client.
     *
     * @return basket;
     */

    public String[] getBasket() {
        return basket;
    }



    /**
     * Permet d'insérer le panier actuel dans la variable basket;
     *
     * @param basket;
     */

    public void setBasket(String[] basket) {
        this.basket = basket;
    }


    /**
     * Permet d'actualiser le budget du client.
     *
     * @param budget;
     */

    public void setBudget(double budget) {
        this.budget = budget;
    }

}
