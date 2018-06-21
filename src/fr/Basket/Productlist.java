package fr.Basket;

import java.util.ArrayList;

public class Productlist {


    private static Productlist productList;
    private ArrayList<Product> productCatalog;

    /**
     * Constructeur privé permettant d'initier en private l'objet Productlist
     */
    private Productlist() {
        productCatalog = new ArrayList<>(20);
        addGenericProducts();
    }

    /**
     * La méthode getInstance est le seul et unique point d'entrée pour accéder à l'objet.
     * @return productList si créé; Sinon on le créée;
     */
    public static synchronized Productlist getInstance() {
        if (productList == null) {
            productList = new Productlist();
        }
        return productList;
    }

    public ArrayList<Product> getProductCatalog() {
        return productCatalog;
    }

    /**
     * Méthode permettant d'ajouter au départ 3 produits génériques.
     */
    private void addGenericProducts() {
        productCatalog.add(new Product(1, "produit1", 10, 2));
        productCatalog.add(new Product(2, "produit2", 20, 5));
        productCatalog.add(new Product(3, "produit3", 40, 10));
    }

    /**
     * Méthode permettant d'ajouter un produit à la productList.
     * @param product;
     */
    public void addProduct(Product product){
        productCatalog.add(product);
    }
}
