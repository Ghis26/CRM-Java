package fr.company;

import fr.Users.User;

import java.util.Scanner;

import static fr.DataBase.DataBase.addUser;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        testDriver();
        System.out.println("Avez-vous déjà un compte ? (O/N)");
        char choice = sc.next().charAt(0);
        User user;
        switch (choice) {
            case 'O':
                user = defineUser();
                user.showMenu();
                break;
            case 'N':
                signUp();
                user = defineUser();
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
    private static void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException c) {
            System.out.println(c+ "- Driver not found.");
        }
    }

    /**
     * Méthode permettant de définir ce que souhaite faire l'utilisateur. Se connecter ou créer un compte
     */
    public static void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Création de compte ");
        System.out.println("Veuillez saisir le login : ");
        String newLogin = sc.next();
        System.out.println("Veuillez saisir le password : ");
        String newPassword = sc.next();
        System.out.println("Veuillez saisir le type d'utilisateur : ");
        String newType = sc.next();
        addUser(newLogin, newPassword, newType);
    }
}