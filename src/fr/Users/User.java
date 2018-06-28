package fr.Users;

import fr.Basket.Product;
import fr.Basket.Productlist;
import fr.DataBase.DataBase;
import fr.Utilities.Choice;
import fr.Utilities.InterfaceUser;
import fr.company.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

/**
 * La classe User est la classe parente des autres utilisateurs.
 *
 * @author Ghis
 * @version 1.0
 */

public abstract class User implements InterfaceUser, Choice {

    /**
     * La variable sc permet de capter les entrées utilisateur.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * La variable login correspond au login utilisateur.
     */
    private String login;

    /**
     * La variable password correspond au password utilisateur.
     */
    private String password;

    private String status;
// --------------------------------------------------------------------------------------------------------------

    /**
     * Le constructeur permet d'initier les variables.
     */

    public User() {
        this.login = "";
        this.password = "";
        }

    public User(String uLogin, String uPassword, String uStatus){
        login = uLogin;
        password = uPassword;
        status = uStatus;
    }

    // --------------------------------------------------------------------------------------------------------------
    public String getStatut() {
        return "";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // --------------------------------------------------------------------------------------------------------------

    /**
     * La méthode showProfile permet d'afficher les données utilisateur.
     */
    public void showProfile() {
        System.out.println("Votre profil : \n");
        System.out.println("Votre nom d'utilisateur : " + getLogin() + "\n");
        System.out.println("Votre mot de passe : " + getPassword() + "\n");
    }

    /**
     * La méthode modifProfile permet à l'utilisateur de modifier son profil.
     */
    public void modifProfile() {
        System.out.println("Modifiez votre profil :\n");
        System.out.println("Modifiez votre nom :");
        setLogin(sc.nextLine());
        System.out.println("Votre nouveau nom : " + getLogin() + "\n");
        System.out.print("Modifiez votre mot de passe :");
        setPassword(sc.next());
        System.out.print("Votre nouveau mot de passe : " + getPassword() + "\n");
    }

    /**
     * La méthode disconnect termine le programme.
     */
    public void disconnect() {
        System.out.println("A bientôt !");
    }


    public void showProduct() {
        Main.testDriver();
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
        try {
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM products");
            ResultSetMetaData resultMeta = result.getMetaData();

            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                while (result.next()) {
                    System.out.println(result.getString("name") + " - " + result.getString("price") + "€");
                }
            }
            result.close();
            state.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    void newProduct() {
        int idProduct;
        String nameProduct;
        float priceProduct;
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();

        Product product = new Product();
        System.out.println("Création de votre produit :");
        System.out.println("Saisissez l'ID de votre produit :");
        idProduct = sc.nextInt();
        product.setId(idProduct);
        System.out.println("Saisissez le nom de votre produit :");
        nameProduct = sc.next();
        product.setName(nameProduct);
        System.out.println("Saisissez le prix de votre produit :");
        priceProduct = sc.nextFloat();
        product.setPrice(priceProduct);
        System.out.println("Saisissez la quantité initiale de produits en stock :");
        int quantity = sc.nextInt();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate("INSERT INTO products (product_id, name, price) VALUES ('" + idProduct + "','" + nameProduct + "','" + priceProduct + "')");
            System.out.println("Produit bien ajouté à la base !");
        } catch (Exception e) {
            System.out.println(e + " - Annulation de la requête");
        }
        Productlist.getInstance().addProduct(product, quantity);
        System.out.println("Votre produit a bien été créé !\n");
    }

    public abstract void showMenu();

    public abstract void showChoices();
}
