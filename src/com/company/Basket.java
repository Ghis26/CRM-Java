package com.company;

import java.util.ArrayList;
/**
 * La classe com.company.Basket définit le panier total du client. Il contient plusieurs BasketItems.
 *
 * @author Ghis
 * @version 1.0
 */


public class Basket {


    /**
     * Ceci est un tableau contenant l'ensemble des BasketItems du com.company.Basket.
     *
     * @see Basket#getBasketItems();
     * @see Basket#addToCart() ;
     */

    protected ArrayList<BasketItem> basketItems;

    /**
     * L'enumération allSstatus est la liste de tous les statuts possibles du panier.
     */
    protected enum allStatus {
        enCours,
        Paye,
        Annule;
    }

    /**
     * La variable status indique le statut actuel du panier.
     *
     * @see Basket#getStatus();
     * @see Basket#setStatus(String);
     */
    protected String status;


    /**
     * La variable productId récupère l'ID du produit.
     * @see Basket#addToCart();
     */

    protected String productId;



// --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur permettant d'initier un nouveau panier.
     *
     * @see Basket#basketItems;
     * @see Basket#status;
     */

    public Basket() {
        basketItems = new ArrayList<>(10);
        status = "";
//        productId = com.company.Product.getId();

    }


// --------------------------------------------------------------------------------------------------------------



    /**
     * La méthode getStatus permet de retourner le statut actuel du com.company.Basket.
     * @return status;
     */

    public String getStatus() {
        return status;
    }


    /**
     * La méthode getBasketItems permet de retourner le contenu actuel du com.company.Basket.
     * @return basketItems;
     */
    public ArrayList<BasketItem> getBasketItems() {
        return basketItems;
    }

    /**
     * La méthode setStatus permet d'affecter le statut actuel du basket à la variable status.
     * @param status;
     */
    public void setStatus(String status) {
        this.status = status;
    }

//    /**
//     * La méthode addToCart permet d'ajouter des basketItems à la variable.
//     */
//    public BasketItem addToCart(productId) {
//        basketItems.add(productId);
//    }
}

