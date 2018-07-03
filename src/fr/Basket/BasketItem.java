package fr.Basket;

/**
 * La classe fr.Basket.BasketItem permet de créer le produit ajouté dans le panier (fr.Basket.fr.Basket)
 *
 * @author Ghis
 * @version 1.0
 */


public class BasketItem {

    /**
     * l'ref permet d'identifier un produit ajouté dans le panier sous un numéro unique.
     *
     * @see BasketItem#getRef ;
     * @see BasketItem#setRef(int);
     */
    private int ref;

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

    private double itemPrice;

// --------------------------------------------------------------------------------------------------------------

    /**
     * Constructeur qui permet d'initier les variables ref et quantity lors de la création d'un nouvel objet.
     */

    public BasketItem() {
        ref = 0;
        quantity = 0;
        itemPrice = 0;
    }


// --------------------------------------------------------------------------------------------------------------


    /**
     * Getter qui permet de retourner l'ref du produit ajouté.
     *
     * @return ref;
     */
    public int getRef() {
        return ref;
    }


    /**
     * Getter qui permet de retourner la quantité de produits ajoutés dans le panier.
     *
     * @return quantity;
     */
    int getQuantity() {
        return quantity;
    }


    double getItemPrice() {
        return itemPrice;
    }

    /**
     * Permet d'affecter la ref du produit à la variable.
     *
     * @param ref;
     */

    public void setRef(int ref) {
        this.ref = ref;
    }

    /**
     * Permet d'affecter la quantité de produits ajoutés au panier à la variable.
     *
     * @param quantity;
     */
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

// --------------------------------------------------------------------------------------------------------------

    /**
     * Permet de retourner les données de l'objet contenues dans le tableau sous forme de phrase.
     *
     * @return String;
     */
    public String toString() {
        return "Id du Produit : " + this.getRef() + ", quantité : " + getQuantity() + " pour un montant de : " + getItemPrice() + " €\n";
    }

    void multiply(double productPrice) {
        itemPrice = productPrice * getQuantity();
    }
}
