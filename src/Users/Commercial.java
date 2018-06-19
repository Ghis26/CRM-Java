package Users;

public class Commercial extends User{
    private String statut;

    public Commercial(){
        super();
        statut = "commercial";
    }
    public String getStatut() {
        statut = super.getStatut();
        return statut;
    }
}
