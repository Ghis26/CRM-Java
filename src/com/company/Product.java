package com.company;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe com.company.Product définit les attributs d'un produit
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

    /**
     * Cette liste crée une nouvelle liste de produits.
     */

    private  ArrayList<String> productList = new ArrayList<>(20);


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
     * @return le nom du produit.
     */

    public String getName(){
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

    public String toString (){
        return "Nom du produit : "+this.getName()+ ": stock : "+this.getStock()+", prix : "+ this.getPrice()+" €";
    }

    public void newProduct(){
        Product product = new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez l'ID de votre produit :");
        product.setId(sc.nextInt());
        System.out.println("Saisissez le nom de votre produit :");
        product.setName(sc.next());
        System.out.println("Saisissez le nombre de produits en stock :");
        product.setStock(sc.nextInt());
        System.out.println("Saisissez le prix de votre produit :");
        product.setPrice(sc.nextFloat());
        productList.add(product.toString());
        System.out.println("Votre produit a bien été créé !");
    }


    public void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : ");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i));
        }
    }
}
