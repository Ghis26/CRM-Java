package fr.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static String url = "jdbc:mysql://localhost:3306/javadb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static String user = "ghis";
    private static String password = "Ghislain611";
    private static Connection connexion;

    public static Connection getInstance() {

        if (connexion == null) {
            new ConnectDB();
        }
        return connexion;
    }

    private ConnectDB() {
        try {
            testDriver();
            connexion = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode permettant de tester le Driver pour accès à la DB.
     */
    private void testDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver OK");
        } catch (ClassNotFoundException c) {
            System.out.println(c+ "- Driver not found.");
        }
    }
}


