package fr.Basket;

import fr.Stock.Stock;

import java.util.ArrayList;

public class Productlist {


    private static fr.Basket.Productlist productList;
    private ArrayList<Product> productCatalog;
    private Stock stockProducts;

    /**
     * Constructeur privé permettant d'initier en private l'objet Productlist
     */
    private Productlist() {
        productCatalog = new ArrayList<>();
        stockProducts = new Stock();
        addGenericProducts();
    }

    /**
     * La méthode getInstance est le seul et unique point d'entrée pour accéder à l'objet.
     *
     * @return productList si créé; Sinon on le créée;
     */
    public static synchronized fr.Basket.Productlist getInstance() {
        if (productList == null) {
            productList = new fr.Basket.Productlist();
        }
        return productList;
    }

    public ArrayList<Product> getProductCatalog() {
        return productCatalog;
    }

    /**
     * Méthode permettant d'ajouter au départ 3 produits génériques et ajouter au stock
     */
    private void addGenericProducts() {
        productCatalog.add(new Product(1, "produit1", 2));
        productCatalog.add(new Product(2, "produit2", 5));
        productCatalog.add(new Product(3, "produit3", 10));
        for (Product product : productCatalog) {
            stockProducts.addToStock(product.getName(), 10);
        }
    }

    /**
     * Méthode permettant d'ajouter un produit à la productList.
     *
     * @param product;
     */
    public void addProduct(Product product, int quantity) {
        productCatalog.add(product);
        stockProducts.addToStock(product.getName(), quantity);
    }

    public void displayStock() {
        for (String productName : stockProducts.stockList.keySet()) {
            System.out.println("Nous avons actuellement " + stockProducts.stockList.get(productName) +" " +productName+ " en stock.\n");
        }
    }

    public Stock getStockProducts() {
        return stockProducts;
    }

}



