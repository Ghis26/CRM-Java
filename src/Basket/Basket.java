package Basket;

import java.util.ArrayList;

/**
 * La classe Basket.Basket définit le panier total du client. Il contient plusieurs BasketItems.
 *
 * @author Ghis
 * @version 1.0
 */


public class Basket {

    /**
     * Ceci est un tableau contenant l'ensemble des BasketItems du Basket.Basket.
     *
     */

    public ArrayList<BasketItem> cart;

    /**
     * L'enumération allSstatus est la liste de tous les statuts possibles du panier.
     */
    public enum allStatus {
        enCours,
        Paye,
        Annule,
    }

    /**
     * La variable status indique le statut actuel du panier.
     *
     */
    public allStatus status;


    /**
     * La variable productId récupère l'ID du produit.
     */

    private float totalPrice;


// --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur permettant d'initier un nouveau panier.
     */

    public Basket() {
        cart = new ArrayList<>(10);
        status = allStatus.enCours;
        totalPrice = 0;
    }


// --------------------------------------------------------------------------------------------------------------


    /**
     * La méthode getStatus permet de retourner le statut actuel du Basket.Basket.
     *
     * @return status;
     */

    public allStatus getStatus() {
        return status;
    }


    /**
     * La méthode getBasketItems permet de retourner le contenu actuel du Basket.Basket.
     *
     * @return basketItems;
     */
    public ArrayList<BasketItem> getBasketItems() {
        return cart;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * La méthode setStatus permet d'affecter le statut actuel du basket à la variable status.
     *
     * @param status;
     */
    public void setStatus(allStatus status) {
        this.status = status;
    }


// --------------------------------------------------------------------------------------------------------------

    /**
     * la méthode sum additionne les prix des produits contenus dans le panier.
     */
    public void sum() {
        totalPrice = 0;
        for (int i = 0; i < cart.size(); i++) {
            totalPrice += cart.get(i).getItemPrice();
        }
    }
}

