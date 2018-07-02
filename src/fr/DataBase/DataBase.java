package fr.DataBase;

import fr.Basket.Product;
import fr.Basket.Productlist;

import java.sql.*;
import java.util.Scanner;

public class DataBase {
    private static String url;
    private static String user;
    private static String password;
    private static Connection connexion;


    // --------------------------------------------------------------------------------------------------------------

    public DataBase() {
        url = "jdbc:mysql://localhost:3306/javadb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
        user = "ghis";
        password = "Ghislain611";
        connexion = null;
    }

    // --------------------------------------------------------------------------------------------------------------
//                                          DB CONNECTION

    private static Connection connectToDataBase() {
        new DataBase();
        try {
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException s) {
            System.out.println(s);
        }
        return connexion;
    }

    // --------------------------------------------------------------------------------------------------------------

//                                              USERS


    public static void addUser(String newLogin, String newPassword, String newType) {
        try {
            String sql = "INSERT INTO users(login, password, statut ) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql);
            preparedStatement.setString(1, newLogin);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, newType);
            preparedStatement.executeUpdate();
            System.out.println("Votre compte a bien été créé !");
        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
        }
    }

    public static void showUsers() {
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql);
            preparedStatement.executeQuery();

        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
            e.printStackTrace();
        }
    }

    /**
     * méthode permettant de supprimer un utilisateur de la base de données.
     * @param idUser;
     */
    public static void deleteUser(int idUser) {
        try {
            String sql = "DELETE * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
            System.out.println("L'utilisateur a bien été supprimé !");
        } catch (Exception e) {
            System.out.println(e + "- Problème de base de données, veuillez réessayer.");
        }
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

        Product product = new Product();
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
            try (Connection connection = connectToDataBase();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql1)) {
                preparedStatement.setString(1, nameProduct);
                preparedStatement.setInt(2, refProduct);
                preparedStatement.setDouble(3, priceProduct);
                preparedStatement.executeUpdate();
                System.out.println("Produit bien ajouté à la liste de produits !");
            }

            String sql2 = "INSERT INTO stock(name, stock) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql2)) {
                preparedStatement.setString(1, nameProduct);
                preparedStatement.setInt(2, refProduct);
                preparedStatement.setInt(3, quantity);
                preparedStatement.executeUpdate();
                System.out.println("Stock bien ajouté !");
            }

        } catch (Exception e) {
            System.out.println(e + " - Annulation de la requête");
        }
        Productlist.getInstance().addProduct(product, quantity);
        System.out.println("Votre produit a bien été créé !\n");
    }

    /**
     * Méthode permettant d'afficher la liste des produits
     */
    public static void showProduct() {
        try {
            String sql = "SELECT name,price FROM products";
            PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();
            ResultSetMetaData resultMeta = result.getMetaData();

            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                while (result.next()) {
                    System.out.println(result.getString("name") + " - " + result.getString("price") + "€");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
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
        try {
            String sql = "SELECT * FROM stock WHERE reference = ?";
            PreparedStatement preparedStatement = connectToDataBase().prepareStatement(sql);
            preparedStatement.setInt(1, refProduct);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int stockInit = result.getInt(3);
            System.out.println("stock initial = " + stockInit);
            int updateStock = stockInit + nbItem;
            String sql2 = "INSERT INTO stock(stock) VALUES (?)";
            PreparedStatement preparedStatement1 = connectToDataBase().prepareStatement(sql2);
            preparedStatement1.setInt(1, updateStock);
            preparedStatement1.executeUpdate();
            System.out.println("Le stock a bien été mis à jour !");

        } catch (Exception e) {
            System.out.println(e+ "- Votre produit n'est pas dans la base de données. Veuillez d'abord le créer");
        }
    }
}