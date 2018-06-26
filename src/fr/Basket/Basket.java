package fr.Basket;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe fr.Basket.fr.Basket définit le panier total du client. Il contient plusieurs BasketItems.
 *
 * @author Ghis
 * @version 1.0
 */


public class Basket {

    private Scanner sc = new Scanner(System.in);

    /**
     * Ceci est un tableau contenant l'ensemble des BasketItems du fr.Basket.fr.Basket.
     *
     */

    public ArrayList<BasketItem> cart;

    /**
     * L'enumération allSstatus est la liste de tous les statuts possibles du panier.
     */
    public enum allStatus {
        EN_COURS,
        PAYE,
        ANNULE,
    }

    /**
     * La variable status indique le statut actuel du panier.
     *
     */
    private allStatus status;


    /**
     * La variable productId récupère l'ID du produit.
     */

    private float totalPrice;


// --------------------------------------------------------------------------------------------------------------


    /**
     * Constructeur permettant d'initier un nouveau panier.
     */

    public Basket() {
        cart = new ArrayList<>(10);
        status = allStatus.EN_COURS;
        totalPrice = 0;
    }


// --------------------------------------------------------------------------------------------------------------

     /**
     * La méthode getBasketItems permet de retourner le contenu actuel du fr.Basket.fr.Basket.
     *
     * @return basketItems;
     */

    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * La méthode setStatus permet d'affecter le statut actuel du basket à la variable status.
     *
     * @param status;
     */
    public void setStatus(allStatus status) {
        this.status = status;
    }


// --------------------------------------------------------------------------------------------------------------

    /**
     * la méthode sum additionne les prix des produits contenus dans le panier.
     */
    public void sum() {
        totalPrice = 0;
        for (BasketItem item : cart) {
            totalPrice += item.getItemPrice();
        }
    }

    /**
     * La méthode findProduct permet de récupérer l'objet Product correspondant à l'ID entré dans le basketItem.
     *
     * @param idBasketItem;
     * @return null;
     */
    private Product findProduct(int idBasketItem) {

        for (int i = 0; i < Productlist.getInstance().getProductCatalog().size(); i++) {
            if (Productlist.getInstance().getProductCatalog().get(i).getId() == idBasketItem) {
                return Productlist.getInstance().getProductCatalog().get(i);
            }
        }
        return null;
    }

    public BasketItem createBasketItem(){
        BasketItem basketItem = new BasketItem();
        System.out.println("Saisissez l'ID du produit que vous souhaitez ajouter au panier : ");
        basketItem.setId(sc.nextInt());
        System.out.println("Saisissez la quantité de produits que vous souhaitez ajouter au panier : ");
        basketItem.setQuantity(sc.nextInt());
        Product itemFound = findProduct(basketItem.getId());
        basketItem.multiply(itemFound.getPrice());
        return basketItem;
    }

}

