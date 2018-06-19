package Users;

public class Administrateur extends User{
    private String statut;

    public Administrateur(){
        super();
        statut = "administrateur";
    }
    public String getStatut() {
        statut = super.getStatut();
        return statut;
    }
}
