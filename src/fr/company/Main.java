package fr.company;

import fr.Users.User;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Permet de récupérer les éléments inscrits par l'utilisateur
        User user = null;
        boolean status = false;


        do{
            System.out.println("Êtes-vous Client, Commercial ou Administrateur ?");
            String userType = sc.next();
            try {
                user =(User)Class.forName("fr.Users."+userType).newInstance();
                status = true;
            } catch (ClassNotFoundException e) {
                System.out.println(e+ ". La classe n'existe pas");
            } catch (InstantiationException i) {
                System.out.println(i+ ". Veuillez réessayer");
            } catch (IllegalAccessException l) {
                System.out.println(l+ ". La classe n'est pas accessible");
            }catch (NoClassDefFoundError n) {
                System.out.println(n+ "Veuillez réessayer");
            }
        } while(!status);


        System.out.println("Veuillez saisir votre nom :");

        user.setLogin(sc.next());

        System.out.println("Veuillez saisir votre mot de passe :");

        user.setPassword(sc.next());

        user.showMenu();
    }
}
