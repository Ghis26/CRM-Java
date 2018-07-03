package fr.Basket;

import java.util.ArrayList;
import java.util.Scanner;

import static fr.DataBase.DataBase.addCartItem;

/**
 * La classe fr.Basket.fr.Basket définit le panier total du client. Il contient plusieurs BasketItems.
 *
 * @author Ghis
 * @version 1.0
 */


public class Basket {
    private int id;

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
    public int getId() {
        return id;
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

    public BasketItem createBasketItem(){
        BasketItem basketItem = new BasketItem();
        System.out.println("Saisissez la référence du produit que vous souhaitez ajouter au panier : ");
        basketItem.setRef(sc.nextInt());
        int idProduct = basketItem.getRef();
        System.out.println("Saisissez la quantité de produits que vous souhaitez ajouter au panier : ");
        basketItem.setQuantity(sc.nextInt());
        int quantityProduct = basketItem.getQuantity();
        addCartItem(idProduct,quantityProduct);
//        basketItem.multiply;
        cart.add(basketItem);
        return basketItem;
    }

    public void sum() {
        totalPrice = 0;
        for (BasketItem item : cart) {
            totalPrice += item.getItemPrice();
        }
    }
}

