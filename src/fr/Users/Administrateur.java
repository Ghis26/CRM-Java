package fr.Users;

import fr.DataBase.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class Administrateur extends User {
    private final String status;
    private Scanner sc = new Scanner(System.in);


    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_USER("Ajouter un utilisateur"),
        ADD_PRODUCT("Ajouter un produit à la liste"),
        SHOW_PRODUCTLIST("Voir la liste des produits"),
        SHOW_USERLIST("Voir la liste des utilisateurs"),
        DELETE_USER("Supprimez un utilisateur"),
        LEAVE("Quittez le programme");

        private String textChoice;

        AllChoices(String textChoice) {
            this.textChoice = textChoice;
        }

        public static Administrateur.AllChoices find(int value) {
            return values()[value];
        }
    }

    public Administrateur() {
        super();
        status = "administrateur";
    }

    public String getStatut() {
        return status;
    }

    public void showChoices() {
        for (Administrateur.AllChoices menuChoice : Administrateur.AllChoices.values()) {
            System.out.println(menuChoice.ordinal() + " - " + menuChoice.textChoice);
        }
    }

    public void showMenu() {
        boolean stat = true;
        AllChoices userChoice;

        while (stat) {
            showChoices();

            System.out.println("Votre choix :");

            userChoice = AllChoices.find(sc.nextInt());
            switch (userChoice) {
                case SHOW_PROFILE:
                    showProfile();
                    stat = true;
                    break;
                case MODIF_PROFILE:
                    modifProfile();
                    stat = true;
                    break;
                case ADD_USER:
                    newUser();
                    stat = true;
                    break;
                case SHOW_PRODUCTLIST:
                    showProduct();
                    stat = true;
                    break;
                case ADD_PRODUCT:
                    newProduct();
                    stat = true;
                    break;
                case SHOW_USERLIST:
                    showUsers();
                    stat = true;
                    break;
                case DELETE_USER:
                    deleteUser();
                    break;
                case LEAVE:
                    disconnect();
                    stat = false;
                    break;
            }
        }
    }

    /**
     * Méthode permettant d'afficher les utilisateurs présents dans la base de données.
     */
    private void showUsers() {
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
        try {
            Statement state = conn.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM users");
            ResultSetMetaData resultMeta = result.getMetaData();

            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
                while (result.next()) {
                    System.out.println(result.getString("login") + " - " + result.getString("password") + " - " + result.getString("statut"));
                }
            }
            result.close();
            state.close();

        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de créer un utilisateur dans la base de données
     */
    private void newUser() {
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
        System.out.println("Saisissez le login de l'utilisateur à créer :");
        String loginUser = sc.next();
        System.out.println("Saisissez le password de l'utilisateur à créer :");
        String passwordUser = sc.next();
        System.out.println("Saisissez le statut de l'utilisateur à créer :");
        String statusUser = sc.next();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate("INSERT INTO users ( login, password, statut) VALUES ('" + loginUser + "','" + passwordUser + "','" + statusUser + "')");
            System.out.println("L'utilisateur " + loginUser + " a bien été créé !");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * méthode permettant de supprimer un utilisateur de la base de données.
     */
        private void deleteUser(){
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
        System.out.println("Saisissez l'Id de l'utilisateur à supprimer :");
        int idUser = sc.nextInt();
        try{
            Statement state = conn.createStatement();
            state.executeUpdate("DELETE FROM users WHERE id ="+ idUser);
            System.out.println("L'utilisateur a bien été supprimé !");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String toString() {
        return "Nom : " + getLogin() + " - Password : " + getPassword() + " - Statut : " + getStatut();
    }
}
