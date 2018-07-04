package fr.DataBase;

import fr.Basket.Basket;
import fr.Basket.Product;

import java.sql.*;
import java.util.Scanner;

import static fr.company.Main.disconnect;

public abstract class DataBase {


    // --------------------------------------------------------------------------------------------------------------

//                                              USERS

    /**
     * Méthode permettant l'ajout d'un user dans la DB.
     * @param newLogin;
     * @param newPassword;
     * @param newType;
     */
    public static void addUser(String newLogin, String newPassword, String newType) {
        String sql = "INSERT INTO users(login, password, statut ) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, newType);
            preparedStatement.executeUpdate();
            System.out.println("Votre compte a bien été créé !");
        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
        }
    }

    /**
     * Méthode affichant la liste des users actuellement créés dans la DB.
     */
    public static void showUsers() {
        String sql = "SELECT login, password, statut FROM users";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
            e.printStackTrace();
        }
    }

    /**
     * Méthode recherchant dans la DB le user actuellement connecté.
     * @param login;
     * @return idUser;
     */
    private static int searchUser(String login) {
        String sql = "SELECT id FROM users WHERE login = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            return result.getInt(1);
            } catch (Exception e) {
            e.printStackTrace();
        }return 0;
    }

    /**
     * méthode permettant de supprimer un utilisateur de la base de données.
     *
     * @param idUser;
     */
    public static void deleteUser(int idUser) {
        String sql = "DELETE * FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
            System.out.println("L'utilisateur a bien été supprimé !");
        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
        }
    }

    /**
     * Méthode permettant de vérifier si le login et le password sont bons.
     *
     * @param login;
     * @param password;
     * @return result;
     */
    public static String verifyUser(String login, String password) {
        String sql = "SELECT statut FROM users WHERE login = ? AND password = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (!result.first()) {
                return null;
            }
            return result.getString("statut");

        } catch (Exception e) {
            System.out.println("Mauvais login ou mot de passe");
            disconnect();
        }
        return null;
    }

    public static void addBudget(String login, double budget){
        String sql = "UPDATE users SET budget = ? + budget WHERE login = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)){
            preparedStatement.setDouble(1,budget);
            preparedStatement.setString(2,login);
            preparedStatement.executeUpdate();
            }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static double getCurrentBudget(String login){
        String sql = "SELECT budget FROM users WHERE id = (SELECT id FROM users WHERE login = ?)";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)){
            preparedStatement.setString(1,login);
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            return result.getDouble(1);
        }catch (Exception e){
            e.printStackTrace();
        }return 0;
    }
    // --------------------------------------------------------------------------------------------------------------

//                                              PRODUCTS

    /**
     * méthode permettant de créer un produit
     */
    public static void newProduct() {
        Scanner sc = new Scanner(System.in);
        String nameProduct;
        int refProduct;
        double priceProduct;

        new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez le nom de votre produit :");
        nameProduct = sc.next();
        System.out.println("Saisissez la référence produit :");
        refProduct = sc.nextInt();
        System.out.println("Saisissez le prix de votre produit :");
        priceProduct = sc.nextDouble();
        System.out.println("Saisissez la quantité initiale de produits en stock :");
        int quantity = sc.nextInt();

        try {
            String sql1 = "INSERT INTO products (name,reference, price) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql1)) {
                preparedStatement.setString(1, nameProduct);
                preparedStatement.setInt(2, refProduct);
                preparedStatement.setDouble(3, priceProduct);
                preparedStatement.executeUpdate();
                System.out.println("Produit bien ajouté à la liste de produits !");
            }

            String sql2 = "INSERT INTO stock(name, reference, stock) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql2)) {
                preparedStatement.setString(1, nameProduct);
                preparedStatement.setInt(2, refProduct);
                preparedStatement.setInt(3, quantity);
                preparedStatement.executeUpdate();
                System.out.println("Stock bien ajouté !");
            }

        } catch (Exception e) {
            System.out.println(e + " - Annulation de la requête");
        }
    }

    /**
     * Méthode permettant d'afficher la liste des produits
     */
    public static void showProduct() {
        String sql = "SELECT name,reference, price FROM products";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            ResultSetMetaData resultMeta = result.getMetaData();

            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                while (result.next()) {
                    System.out.println(result.getString("name") + " - référence produit : " + result.getInt("reference") + " - prix unitaire : " + result.getInt("price") + "€");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * méthode retrouvant dans la DB le prix du produit en donnant sa référence en paramètre.
     * @param refProduct;
     * @return int price;
     */
    public static double productPrice(int refProduct) {
        String sql = "SELECT price FROM products WHERE reference = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, refProduct);
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            return result.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    // --------------------------------------------------------------------------------------------------------------

//                                              STOCK

    /**
     * Méthode permettant d'afficher le stock.
     */
    public static void showStock() {
        String sql = "SELECT name, reference, stock FROM stock";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            System.out.println();
            while (result.next()) {
                System.out.println(result.getString(1) + " - référence produit : " + result.getInt(2) + " - stock : " + result.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de réapprovisionner le stock
     *
     * @param refProduct;
     * @param nbItem;
     */
    public static void resupplyStock(int refProduct, int nbItem) {

        String sql = "UPDATE stock SET stock = ? + stock WHERE reference = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(2, refProduct);
            preparedStatement.setInt(1, nbItem);
            preparedStatement.executeUpdate();
            System.out.println("Le stock a bien été mis à jour !");

        } catch (Exception e) {
            System.out.println(e + "- Votre produit n'est pas dans la base de données. Veuillez d'abord le créer");
        }
    }

    // --------------------------------------------------------------------------------------------------------------

//                                              CART

    /**
     * Méthode permettant de créer un nouveau panier.
     * @param login;
     * @return basket;
     */
    public static Basket createNewCart(String login) {
        Basket basket = new Basket();
        int idUser = searchUser(login);
        String sql2 = "INSERT INTO cart (status, id_user) VALUES (?,?)";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql2)) {
            preparedStatement.setString(1, "EN_COURS");
            preparedStatement.setInt(2,idUser);
            preparedStatement.executeUpdate();
            System.out.println("Votre panier a bien été initialisé !");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return basket;
    }

    /**
     * Méthode permettant de rechercher le panier actuel dans la DB.
     * @param login;
     * @return idCart.
     */
    public static int searchCart(String login) {
        String sql = "SELECT id FROM cart WHERE id_user = ? AND status = 'EN_COURS'";
        int idUser = searchUser(login);
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, idUser);
            ResultSet result = preparedStatement.executeQuery();
            result.first();
            return result.getInt(1);
        } catch (Exception e) {
            System.out.println("Votre panier a bien été reglé.");
        }
        return 0;
    }

    /**
     * Méthode ajoutant un Item dans le panier.
     * @param idProduct;
     * @param quantityProduct;
     */
    public static void addCartItem(int idProduct, int quantityProduct) {
        String sql = "INSERT INTO cart_item (id_product, quantity) VALUES (?,?)";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, quantityProduct);
            preparedStatement.executeUpdate();
            System.out.println("L'ajout a bien été effectué !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant le paiement du panier.
     * @param idCart;
     * @param totalPrice;
     */
    public static void payment(int idCart, double budget, double totalPrice) {
        String sql = "UPDATE cart,users  SET cart.status ='PAYE',cart.total_price = ?,users.budget = ? WHERE cart.id = ?";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setDouble(1, totalPrice);
            preparedStatement.setDouble(2,budget - totalPrice);
            preparedStatement.setInt(3, idCart);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant l'annulation du panier.
     * @param idCart;
     */
    public static void cancelCart(int idCart) {
        String sql = "UPDATE cart SET status = 'ANNULE' WHERE id = ? AND status = 'EN_COURS'";
        try (PreparedStatement preparedStatement = ConnectDB.getInstance().prepareStatement(sql)) {
            preparedStatement.setInt(1, idCart);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Votre panier a déjà été réglé.");
        }
    }
}