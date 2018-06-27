package fr.Basket;

/**
 * La classe fr.Basket.Product définit les attributs d'un produit
 *
 * @author Ghis
 * @version 1.0
 */

public class Product {

    /**
     * l'id est l'identifiant du produit
     *
     * @see Product#getId;
     * @see Product#setId(int) ;
     */
    private int id;


    /**
     * name est le nom du produit
     */
    private String name;


    /**
     * price indique le prix d'un produit.
     *
     * @see Product#getPrice;
     * @see Product#setPrice(float);
     */
    private float price;


// --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur qui initie les variables du produit créé.
     *
     */
    public Product() {
        this(0, "", 0);
    }

    public Product(int pId, String pName, int pPrice){
        id = pId;
        name = pName;
        price = pPrice;
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
     * Getter qui retourne le prix initial d'un produit.
     *
     * @return price;
     */
     float getPrice() {
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
        return "Id du Produit : " + this.getId() + ", Nom du produit : " + this.getName() + ", prix : " + this.getPrice() + " €\n";
    }
}
