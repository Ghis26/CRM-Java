package fr.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private String url;
    private String utilisateur;
    private String password;
    private Connection connexion;


    public DataBase() {
        url = "jdbc:mysql://localhost:3306/javadb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        utilisateur = "ghis";
        password = "Ghislain611";
        connexion = null;
    }

    public Connection connectToDataBase() {
        try {
            connexion = DriverManager.getConnection(url, utilisateur, password);
            System.out.println("Connexion établie");
        } catch (SQLException s) {
            System.out.println(s);
        }
        return connexion;
    }

}