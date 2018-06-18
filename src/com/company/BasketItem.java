package com.company;

/**
 * La classe com.company.BasketItem permet de créer le produit ajouté dans le panier (com.company.Basket)
 *
 * @author Ghis
 * @version 1.0
 */


public class BasketItem {

    /**
     * l'id permet d'identifier un produit ajouté dans le panier sous un numéro unique.
     *
     * @see BasketItem#getId();
     * @see BasketItem#setId(int);
     */
    protected int id;

    /**
     * La quantity idnetifie la quantité de produits ajoutée dans le panier.
     *
     * @see BasketItem#getQuantity();
     * @see BasketItem#setQuantity(int);
     */

    protected int quantity;



// --------------------------------------------------------------------------------------------------------------




    /**
     * Constructeur qui permet d'initier les variables id et quantity lors de la création d'un nouvel objet.
     *
     * @see BasketItem#id;
     * @see BasketItem#quantity;
     */

    public BasketItem() {
        id = 0;
        quantity = 0;
    }



// --------------------------------------------------------------------------------------------------------------



    /**
     * Getter qui permet de retourner l'id du produit ajouté.
     *
     * @return id;
     */
    public int getId() {
        return id;
    }


    /**
     * Getter qui permet de retourner la quantité de produits ajoutés dans le panier.
     *
     * @return quantity;
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Permet d'affecter l'id du produit à la variable.
     *
     * @param id;
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet d'affecter la quantité de produits ajoutés au panier à la variable.
     *
     * @param quantity;
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
