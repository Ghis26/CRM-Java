package fr.company;

import fr.DataBase.DataBase;
import fr.Users.User;
import fr.Users.Userlist;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        testDriver();
        DataBase database = new DataBase();
        database.connectToDataBase();
        System.out.println("Avez-vous déjà un compte ? (O/N)");
        char choice = sc.next().charAt(0);
        User user;
        switch (choice) {
            case 'O':
                user = defineUser();
                Userlist.getInstance().getUserCatalog().add(user);
                user.showMenu();
                break;
            case 'N':
                Signup();
                user = defineUser();
                Userlist.getInstance().getUserCatalog().add(user);
                user.showMenu();
        }
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

    /**
     * Méthode permettant de tester le Driver piur accès à la DB.
     */
    public static void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException c) {
            System.out.println(c);
        }
    }

    /**
     * Méthode permettant de définir ce que souhaite faire l'utilisateur. Se connecter ou créer un compte
     */
    private static void Signup() {
        DataBase database = new DataBase();
        Connection conn = database.connectToDataBase();
        Scanner sc = new Scanner(System.in);
        System.out.println("Création de votre compte ");
        System.out.println("Veuillez saisir votre login : ");
        String newLogin = sc.next();
        System.out.println("Veuillez saisir votre password : ");
        String newPassword = sc.next();
        try {
            Statement state = conn.createStatement();
            state.executeUpdate("INSERT INTO users(login, password, statut )VALUES ('" + newLogin + "','" + newPassword + "', 'Client')");
            System.out.println("Votre compte a bien été créé !");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


//Window window = new Window(); Si besoin d'afficher l'interface graphique basique