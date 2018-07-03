package fr.company;

import fr.Basket.Basket;
import fr.Users.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static fr.DataBase.DataBase.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                break;
            default:
                disconnect();
        }
    }

    private static User defineUser() {
        String login;
        String password;
        String status;
        Scanner sc = new Scanner(System.in); // Permet de récupérer les éléments inscrits par l'utilisateur

        login = defineLogin(sc);
        password = definePassword(sc);
        status = verifyUser(login, password);
        User user = chooseUserType(status, login, password);
       if (status .equals("Client")){
           Basket basket = createNewCart();
       }
        return user;
    }

    private static User chooseUserType(String status, String login, String password) {
        try {
            Class<?> aClass = Class.forName("fr.Users." + status);
            Constructor<?> constructor = aClass.getConstructor(String.class, String.class, String.class);
            return (User) constructor.newInstance(login, password, status);
        } catch (ClassNotFoundException e) {
            System.out.println(e + ". La classe n'existe pas");
        } catch (InstantiationException i) {
            System.out.println(i + ". Veuillez réessayer");
        } catch (IllegalAccessException l) {
            System.out.println(l + ". La classe n'est pas accessible");
        } catch (NoClassDefFoundError n) {
            System.out.println(n + "Veuillez réessayer");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String defineLogin(Scanner sc) {
        System.out.println("Veuillez saisir votre nom :");
        return sc.next();
    }

    private static String definePassword(Scanner sc) {
        System.out.println("Veuillez saisir votre mot de passe :");
        return sc.next();
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

    public static void disconnect() {
        System.out.println("A bientôt !");
    }

}