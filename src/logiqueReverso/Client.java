package logiqueReverso;


public class Client extends Societe{
    private Double chiffreAffaire;
    private int nombreEmployes;

    public Client() {
    }

    public Client(String raisonSociale, String numRue, String nomRue, String codePostal,
                  String ville, String telephone, String adresseMail, String commentaire,
                  Double chiffreAffaire, int nombreEmployes) {
        super(raisonSociale, numRue, nomRue, codePostal, ville, telephone, adresseMail, commentaire);


        this.setChiffreAffaire(chiffreAffaire);
        this.setNombreEmployes(nombreEmployes);
    }

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    public double setChiffreAffaire(double chiffreAffaire) {
        if (chiffreAffaire>200.00){
            this.chiffreAffaire = chiffreAffaire;
        }else {
            System.out.println("Le chiffre d'affaire n'est pas bien renseigné");
        }
        return chiffreAffaire;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public int setNombreEmployes(int nombreEmployes) {
        if (nombreEmployes >0){
            this.nombreEmployes = nombreEmployes;
        }else {
            System.out.println("Le nombre d'employés n'est pas correctement indiqué");
        }
        return nombreEmployes;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +
                ", chiffreAffaire=" + chiffreAffaire +
                ", nombreEmployes=" + nombreEmployes +
                '}';
    }

}
