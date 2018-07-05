package fr.Users;

import java.util.Scanner;

import static fr.DataBase.DataBase.*;
import static fr.company.Main.disconnect;
import static fr.company.Main.signUp;

public class Administrateur extends User {
    private Scanner sc = new Scanner(System.in);


    private enum AllChoices {
        SHOW_PROFILE("Affichez votre profil"),
        MODIF_PROFILE("Modifiez votre profil"),
        ADD_USER("Ajouter un utilisateur"),
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

    public Administrateur(String login, String password, String status) {
        super(login, password, "administrateur");
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
                    signUp();
                    stat = true;
                    break;
                case SHOW_PRODUCTLIST:
                    showProduct();
                    stat = true;
                    break;
                case SHOW_USERLIST:
                    showUsers();
                    stat = true;
                    break;
                case DELETE_USER:
                    System.out.println("Saisissez l'Id de l'utilisateur à supprimer :");
                    int idUser = sc.nextInt();
                    deleteUser(idUser);
                    break;
                case LEAVE:
                    disconnect();
                    stat = false;
                    break;
            }
        }
    }

    public String toString() {
        return "Nom : " + getLogin() + " - Password : " + getPassword() + " - Statut : " + getStatut();
    }
}
