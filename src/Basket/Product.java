package Basket;

import java.util.Scanner;

/**
 * La classe Basket.Product définit les attributs d'un produit
 *
 * @author Ghis
 * @version 1.0
 */

public class Product {

    private Scanner sc = new Scanner(System.in);
    /**
     * l'id est l'identifiant du produit
     *
     * @see Product#getId();
     * @see Product#setId(int) ;
     */
    private int id;


    /**
     * name est le nom du produit
     */
    private String name;

    /**
     * quantity indique la quantité de produits en stock.
     *
     * @see Product#getStock();
     * @see Product#setStock(int) ;
     */

    private int stock;

    /**
     * price indique le prix d'un produit.
     *
     * @see Product#getPrice();
     * @see Product#setPrice(float);
     */
    private float price;


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
        name = "";
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
     * Getter qui retourne le nom du produit.
     *
     * @return le nom du produit.
     */

    public String getName() {
        return name;
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
     * Permet d'affecter le nom du produit à la variable name.
     *
     * @param name;
     */
    public void setName(String name) {
        this.name = name;
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

// --------------------------------------------------------------------------------------------------------------

    /**
     * Méthode toString permet de retourner une String avec l'ensemble des éléments du produit créé
     *
     * @return les éléments du produit sous forme de String.
     */
    public String toString() {
        return "Id du Produit : " + this.getId() + ", Nom du produit : " + this.getName() + " : stock : " + this.getStock() + ", prix : " + this.getPrice() + " €";
    }
}
