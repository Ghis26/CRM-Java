package com.company;

/**
 * La classe com.company.Product définit les attributs d'un produit
 *
 * @author Ghis
 * @version 1.0
 */

public class Product {
    /**
     * l'id est l'identifiant du produit
     *
     * @see Product#getId();
     * @see Product#setId(int) ;
     */
    protected int id;

    /**
     * quantity indique la quantité de produits en stock.
     *
     * @see Product#getStock();
     * @see Product#setStock(int) ;
     */

    protected int stock;

    /**
     * price indique le prix d'un produit.
     *
     * @see Product#getPrice();
     * @see Product#setPrice(float);
     */
    protected float price;



// --------------------------------------------------------------------------------------------------------------




    /**
     * Constructeur qui initie les variables du produit créé.
     *
     * @see Product#id;
     * @see Product#stock;
     * @see Product#price;
     */
    public Product() {
        id = 0;
        stock = 0;
        price = 0;

    }



// --------------------------------------------------------------------------------------------------------------



    /**
     * Getter qui retourne l'id du produit.
     *
     * @return l'id du produit;
     */
    public int getId() {
        return id;
    }

    /**
     * Getter qui retourne la quantité de produits.
     *
     * @return le nombre de produits en stock;
     */
    public int getStock() {
        return stock;
    }

    /**
     * Getter qui retourne le prix initial d'un produit.
     *
     * @return price;
     */
    public float getPrice() {
        return price;
    }

    /**
     * Permet d'affecter l'id inscrit à la variable id;
     *
     * @param id;
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet d'affecter la quantité actuelle de produits en stock à la variable quantity.
     *
     * @param stock;
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Permet d'affecter le prix à la variable price.
     *
     * @param price;
     */
    public void setPrice(float price) {
        this.price = price;
    }
}
