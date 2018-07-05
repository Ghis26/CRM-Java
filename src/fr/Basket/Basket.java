package fr.Basket;

import fr.DataBase.DataBase;

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

    private Scanner sc = new Scanner(System.in);

    /**
     * Ceci est un tableau contenant l'ensemble des BasketItems du fr.Basket.fr.Basket.
     *
     */

    private ArrayList<BasketItem> cart;

    /**
     * L'enumération allSstatus est la liste de tous les statuts possibles du panier.
     */
    private enum allStatus {
        EN_COURS,
        PAYE,
        ANNULE,
    }

    /**
     * La variable status indique le statut actuel du panier.
     *
     */
    private allStatus status;


    private double totalPrice;


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
     * La méthode setStatus permet d'affecter le statut actuel du basket à la variable status.
     *
     * @param status;
     */
    public void setStatus(allStatus status) {
        this.status = status;
    }

    // --------------------------------------------------------------------------------------------------------------

    /**
     * Méthode permettant de créer un nouvel Item.
     * @return basketItem;
     */
    public BasketItem createBasketItem(){
        BasketItem basketItem = new BasketItem();
        System.out.println("Saisissez la référence du produit que vous souhaitez ajouter au panier : ");
        basketItem.setRef(sc.nextInt());
        System.out.println("Saisissez la quantité de produits que vous souhaitez ajouter au panier : ");
        basketItem.setQuantity(sc.nextInt());
        return basketItem;
    }


    /**
     * Méthode ajoutant l'Item au Cart (DB + objet).
     * @param basketItem;
     * @param idCart;
     */
    public void basketItemToCart(BasketItem basketItem, int idCart){
        int quantityProduct = basketItem.getQuantity();
        int idProduct = basketItem.getRef();
        addCartItem(idProduct,quantityProduct, idCart);
    }
}

