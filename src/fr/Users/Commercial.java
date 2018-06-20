package fr.Users;

public class Commercial extends User{
    private final String statut;

    public Commercial(){
        super();
        statut = "commercial";
    }
    public String getStatut() {
        return statut;
    }
}

