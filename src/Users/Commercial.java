package Users;

public class Commercial extends User{
    final String statut;

    public Commercial(){
        super();
        statut = "commercial";
    }
    public String getStatut() {
        return statut;
    }
}

