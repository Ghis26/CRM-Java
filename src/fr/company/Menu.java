package fr.company;

import fr.Basket.Basket;
import fr.Basket.BasketItem;
import fr.Basket.Product;
import fr.Users.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * La classe Menu identifie le menu principal du programme.
 *
 * @author Ghis
 * @version 1.0
 */


public class Menu {

    private Scanner sc = new Scanner(System.in);

    /**
     * la variable choice stocke le choix de l'utilisateur.
     */
    private int choice;


    /**
     * la variable exitProject définit la sortie du programme ou non.
     */
    private boolean exitProject;

    /**
     * La variable productList définit la liste de tous les produits créés.
     */
    private ArrayList<Product> productList = new ArrayList<>(20);

    /**
     * La variable basket initie le basket a null.
     */
    private Basket basket = null;

    private double amount = 0;
// --------------------------------------------------------------------------------------------------------------

    /**
     * Constructeur permettant d'initier les variables du Menu
     */

    public Menu() {
        choice = 0;
        exitProject = false;
    }

// --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showMenu permet d'afficher le menu des choix
     *
     * @param user;
     */

    public void showMenu(User user) {
        do {
            System.out.println(user.getLogin() + ", veuillez choisir parmi les propositions suivantes :\n");
            System.out.println("1 - Affichez votre profil");
            System.out.println("2 - Modifiez votre profil");
            System.out.println("3 - Créez un produit");
            if (user.getStatut().equals("client")) {
                System.out.println("4 - Affichez la liste des produits");
                System.out.println("5 - Ajouter un produit au panier");
                System.out.println("6 - Voir votre panier");
                System.out.println("7 - Payez vos achats");
                System.out.println("8 - Réapprovisionnez votre porte-monnaie");
            }
            System.out.println("9 - Quittez le programme\n");

            System.out.println("Votre choix :");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.showProfile(user);
                    exitProject = false;
                    break;
                case 2:
                    user.modifProfile();
                    exitProject = false;
                    break;
                case 3:
                    newProduct();
                    exitProject = false;
                    break;
                case 4:
                    if (user.getStatut().equals("client")) {
                        showProduct();
                        exitProject = false;
                        break;
                    } else {
                        System.out.println("Vous n'avez pas l'autorisation");
                        exitProject = false;
                        break;
                    }
                case 5:
                    if (user.getStatut().equals("client")) {
                        showProduct();
                        addtoCart();
                        exitProject = false;
                        break;
                    } else {
                        System.out.println("Vous n'avez pas l'autorisation");
                        exitProject = false;
                        break;
                    }
                case 6:
                    showCart();
                    exitProject = false;
                    break;
                case 7:
                    cartPayment(user);
                    exitProject = true;
                    break;
                case 8:
                    resupplyBudget(user);
                    exitProject = false;
                    break;
                default:
                    user.disconnect();
                    exitProject = true;
            }
        } while (!exitProject);
    }


// --------------------------------------------------------------------------------------------------------------


    /**
     * La méthode newProduct permet d'instancier un nouveau produit
     */
    private void newProduct() {
        Product product = new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez l'ID de votre produit :");
        try {
            product.setId(sc.nextInt());
        } catch (InputMismatchException i) {
            System.out.print(i + "\n");
            product.setId(1);
        }
        System.out.println("Saisissez le nom de votre produit :");
        product.setName(sc.next());
        System.out.println("Saisissez le nombre de produits en stock :");
        product.setStock(sc.nextInt());
        System.out.println("Saisissez le prix de votre produit :");
        product.setPrice(sc.nextFloat());
        productList.add(product);
        System.out.println("Votre produit a bien été créé !\n");
    }

    /**
     * La méthode showProduct permet d'afficher l'ensemble des produits créés
     */
    private void showProduct() {
        System.out.println("Voici la liste des produits créés à ce jour : \n");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString() + "\n");
        }
    }

    /**
     * La méthode addToCart permet de créer un basketItem (id + quantité produit) + l'ajouter au panier.
     */
    private void addtoCart() {
        if(basket == null){
            basket = new Basket();
        }
        BasketItem basketItem = new BasketItem();
        System.out.println("Saisissez l'ID du produit que vous souhaitez ajouter au panier : ");
        basketItem.setId(sc.nextInt());
        System.out.println("Saisissez la quantité de produits que vous souhaitez ajouter au panier : ");
        basketItem.setQuantity(sc.nextInt());
        Product itemFound = findProduct(basketItem.getId());
        basketItem.multiply(itemFound.getPrice());
        basket.cart.add(basketItem);
        basket.sum();
        System.out.println("l'ajout au panier a bien été fait ! Le montant total du panier s'élève à : " + basket.getTotalPrice() + " €\n");
    }

    /**
     * La méthode findProduct permet de récupérer l'objet Product correspondant à l'ID entré dans le basketItem.
     *
     * @param idBasketItem;
     * @return null;
     */
    private Product findProduct(int idBasketItem) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == idBasketItem) {
                return productList.get(i);
            }
        }
        return null;
    }

    /**
     * La méthode showCart permet d'afficher le panier en cours.
     */
    private void showCart() {
        System.out.println("Voici la liste des produits dans votre panier : \n");
        if (basket != null) {
            for (int i = 0; i < basket.cart.size(); i++) {
                System.out.println(basket.cart.get(i).toString() + "\n");
            }
        }
    }

    /**
     * La méthode cartPayment permet de régler le panier ainsi que de changer son statut.
     */
    private void cartPayment(User user) {
        if (basket.getTotalPrice() <= user.getBudget()) {
            System.out.println("Votre panier a bien été reglé ! Merci !\n");
            basket.setStatus(Basket.allStatus.PAYE);
        } else {
            System.out.println("Veuillez réapprovisionner votre compte merci !\n");
            basket.setStatus(Basket.allStatus.ANNULE);

        }
    }

    /**
     * La méthode ressuplyBudget permet de réapprovisionner le porte-monnaie du client.
     */
    private void resupplyBudget(User user) {
        System.out.println("Saisissez le montant que vous souhaitez ajouter à votre porte-monnaie : ");
        amount = sc.nextDouble();
        user.setBudget(amount + user.getBudget());
        System.out.println("Votre porte-monnaie est maintenant de " + user.getBudget() + " €.\n");
    }
}
