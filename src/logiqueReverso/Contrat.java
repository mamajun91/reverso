package logiqueReverso;

public class Contrat {

    private int id_contrat;
    private int id_client;
    private String libelleContrat;
    private double montantContrat;

    public Contrat() {}

    public Contrat(int id_contrat, int id_client, String libelleContrat, double montantContrat) {
        this.id_contrat = id_contrat;
        this.setId_client(id_client);
        this.libelleContrat = libelleContrat;
        this.montantContrat = montantContrat;
    }

    public int getId_contrat() {
        return id_contrat;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        if (id_client != 0) {
            this.id_client = id_client;
        }else{

            System.out.println("id_client doit être différent de 0");
        }
    }

    public String getLibelleContrat() {
        return libelleContrat;
    }

    public void setLibelleContrat(String libelleContrat) {
        if (libelleContrat.isEmpty() | libelleContrat.isBlank()){
            System.out.println("le libellé doit être indiqué");
        }else{
            this.libelleContrat = libelleContrat;
        }
    }

    public double getMontantContrat() {
        return montantContrat;
    }

    public void setMontantContrat(double montantContrat) {
        if (montantContrat > 0) {
            this.montantContrat = montantContrat;
        } else {
            System.out.println("le montant doit être supérieur à 0");
        }
    }
}
