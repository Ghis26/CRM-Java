package Users;

/**
 * La classe Users.Client définit les attributs d'un client
 *
 * @author Ghis
 * @version 1.0
 */

public class Client extends User{

    /**
     *La variable budget définit le montant du portefeuille du client à sa création.
     * @see Client#getBudget();
     * @see Client#setBudget(double);
     */
    private double budget;

    /**
     * La variable basket est un tableau contenant l'ensemble des produits de l'objet Basket.Basket.
     * @see Client#getBasket();
     * @see Client#setBasket(String[]);
     */
    private String basket[];


    private String statut;

    // --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur permettant d'initier le budget initial du client, ainsi qu'un tableau de 10 cases
     * (admettons qu'un panier ne peut contenir plus de 10 articles)
     * @see Client#budget;
     * @see Client#basket;
     */

    public Client(){
        super();
        budget = 10000;
        basket = new String[10];
        statut = "client";
    }

    // --------------------------------------------------------------------------------------------------------------


    public String getStatut() {
        statut = super.getStatut();
        return statut;
    }

    /**
     * Getter permettant de retourner le budget disponible du client.
     * @return budget;
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Getter permettant de retourner un tableau contenant le panier du client.
     * @return basket;
     */

    public String[] getBasket() {
        return basket;
    }


    /**
     * Permet d'insérer le panier actuel dans la variable basket;
     * @param basket;
     */

    public void setBasket(String[] basket) {
        this.basket = basket;
    }


    /**
     * Permet d'actualiser le budget du client.
     * @param budget;
     */

    public void setBudget(double budget) {
        this.budget = budget;
    }

}


