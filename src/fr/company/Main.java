package fr.company;

import fr.DataBase.DataBase;
import fr.Users.User;
import fr.Users.Userlist;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        testDriver();
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
//        try {
//            Statement state = conn.createStatement();
//            ResultSet result = state.executeQuery("SELECT * FROM products");
//            ResultSetMetaData resultMeta = result.getMetaData();
//
//            System.out.println("Il y a " + resultMeta.getColumnCount() + "colonnes dans cette table");
//
//            for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
//                while (result.next()) {
//                    System.out.println(result.getString("name")+ " - " + result.getString("price")+ "€");
//                }
//            }
//            result.close();
//            state.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//        }


        User user = defineUser();
        Userlist.getInstance().getUserCatalog().add(user);
        user.showMenu();
    }

    private static User defineUser() {
        Scanner sc = new Scanner(System.in); // Permet de récupérer les éléments inscrits par l'utilisateur
        User user = chooseUserType(sc);
        defineLogin(sc, user);
        definePassword(sc, user);
        return user;
    }

    private static User chooseUserType(Scanner sc) {
        while (true) {
            System.out.println("Êtes-vous Client, Commercial ou Administrateur ?");
            String userType = sc.next();
            try {
                return (User) Class.forName("fr.Users." + userType).newInstance();
            } catch (ClassNotFoundException e) {
                System.out.println(e + ". La classe n'existe pas");
            } catch (InstantiationException i) {
                System.out.println(i + ". Veuillez réessayer");
            } catch (IllegalAccessException l) {
                System.out.println(l + ". La classe n'est pas accessible");
            } catch (NoClassDefFoundError n) {
                System.out.println(n + "Veuillez réessayer");
            }
        }
    }

    private static void defineLogin(Scanner sc, User user) {
        System.out.println("Veuillez saisir votre nom :");
        user.setLogin(sc.next());
    }

    private static void definePassword(Scanner sc, User user) {
        System.out.println("Veuillez saisir votre mot de passe :");
        user.setPassword(sc.next());
    }

    private static void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException c) {
            System.out.println(c);
        }
    }
}