package Basket;

/**
 * La classe Basket.BasketItem permet de créer le produit ajouté dans le panier (Basket.Basket)
 *
 * @author Ghis
 * @version 1.0
 */


public class BasketItem {

    /**
     * l'id permet d'identifier un produit ajouté dans le panier sous un numéro unique.
     *
     * @see BasketItem#getId;
     * @see BasketItem#setId(int);
     */
    private int id;

    /**
     * La quantity idnetifie la quantité de produits ajoutée dans le panier.
     *
     * @see BasketItem#getQuantity;
     * @see BasketItem#setQuantity(int);
     */

    private int quantity;

    /**
     * L'itemPrice définit le prix unitaire de l'item x quantité commandée.
     */

    private float itemPrice;

// --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur qui permet d'initier les variables id et quantity lors de la création d'un nouvel objet.
     */

    public BasketItem() {
        id = 0;
        quantity = 0;
        itemPrice = 0;
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


    public float getItemPrice() {
        return itemPrice;
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

// --------------------------------------------------------------------------------------------------------------

    /**
     * Permet de retourner les données de l'objet contenues dans le tableau sous forme de phrase.
     *
     * @return String;
     */
    public String toString() {
        return "Id du Produit : " + this.getId() + ", quantité : " + getQuantity() + " pour un montant de : " + getItemPrice() + " €\n";
    }

    /**
     * Permet de calculer le montant du BasketItem.
     *
     * @param productPrice;
     */

    public void multiply(float productPrice) {
        itemPrice = productPrice * getQuantity();
    }
}
