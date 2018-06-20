package Users;

public class Administrateur extends User{
    final String statut;

    public Administrateur(){
        super();
        statut = "administrateur";
    }
    public String getStatut() {
        return statut;
    }
}
