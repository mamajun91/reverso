package logiqueReverso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prospect extends Societe{
    private String dateProspect;
    private String prospectInteresse;

    public Prospect() {
    }

    public Prospect(int id, String raisonSociale, String numRue, String nomRue, String codePostal,
                    String ville, String telephone, String adresseMail, String commentaire,
                    String dateProspect, String prospectInteresse) {
        super(id, raisonSociale, numRue, nomRue, codePostal, ville, telephone, adresseMail, commentaire);
        this.setDateProspect(dateProspect);
        this.setProspectInteresse(prospectInteresse);
    }

    public String getDateProspect() {

        return dateProspect;
    }

    public String setDateProspect(String dateProspect) {
        String dat="^[0-9]{2}/[0-9]{2}/[0-9]{4}$";
        Pattern pt = Pattern.compile(dat);
        Matcher mt = pt.matcher(dateProspect);
        boolean dtprospect=mt.matches();
        if (!((dateProspect).isEmpty()) && dtprospect){
        this.dateProspect = dateProspect;
        } else {
            System.out.println("la date n'est pas bien renseignée");
        }
        return dateProspect;
    }

    public String getProspectInteresse() {
        return prospectInteresse;
    }

    public void setProspectInteresse(String prospectInteresse) {
        this.prospectInteresse = prospectInteresse;
    }

    @Override
    public String toString() {
        return "Prospect{" + super.toString() +
                "dateProspect='" + dateProspect + '\'' +
                ", prospectInteresse='" + prospectInteresse + '\'' +
                '}';
    }
}
