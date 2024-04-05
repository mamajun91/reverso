package logiqueReverso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Societe {

    private int id;
    private String raisonSociale;
    private String numRue;
    private String nomRue;
    private String codePostal;
    private String ville;
    private String telephone;
    private String adresseMail;
    private String commentaire;



    public Societe() {}

    public Societe( String raisonSociale, String numRue, String nomRue, String codePostal, String ville,
                   String telephone,
                   String adresseMail,
                   String commentaire) {


        this.setRaisonSociale(raisonSociale);
        this.setNumRue(numRue);
        this.setNomRue(nomRue);
        this.setCodePostal(codePostal);
        this.setVille(ville);
        this.setTelephone(telephone);
        this.setAdresseMail(adresseMail);
        this.setCommentaire(commentaire);
    }





    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {

        if (raisonSociale.isEmpty() | raisonSociale.isBlank()){
                System.out.println("La raison sociale est vide ");
            }
         else {this.raisonSociale = raisonSociale;}
    }

    public String getNumRue() {

        return numRue;
    }

    public void setNumRue(String numRue) {
        if (numRue.isEmpty() | numRue.isBlank()){
            System.out.println("Le numéro de la rue est vide ");
        }
        else {
        this.numRue = numRue;}
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        if (nomRue.isEmpty() | nomRue.isBlank()){
            System.out.println("Le nom de la rue est vide ");
        }
        else {
        this.nomRue = nomRue;}
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        String reg ="\\d{5}";
        Pattern pat = Pattern.compile(reg);
        Matcher mat = pat.matcher(codePostal);
        boolean boo = mat.matches();
        if (!(codePostal.isEmpty() | codePostal.isBlank()) && boo){
            this.codePostal = codePostal;
    } else {
            System.out.println("Le code postal est vide ou n'est pas bien renseigneé ");}
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if (ville.isEmpty() | ville.isBlank())
        {
            System.out.println("la ville n'est pas renseignée");
        } else {
            this.ville = ville;
        }
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        String rg ="\\d{10}";
        Pattern pt = Pattern.compile(rg);
        Matcher mt = pt.matcher(telephone);
        boolean tel = mt.matches();
        if (!(telephone.isEmpty() | telephone.isBlank()) && tel){
            this.telephone = telephone;
        }else{
            System.out.println("le téléphone n'est pas bien renseigné");
        }
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        String mai="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(mai);
        Matcher m = p.matcher(adresseMail);
        boolean emai = m.matches();

        if (!(adresseMail.isEmpty() | adresseMail.isBlank()) && emai){
        this.adresseMail = adresseMail;}
        else {
            System.out.println("l'adresse email n'est pas bien renseignée");
        }
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Societe{" +
                "id=" + id +
                ", raisonSociale='" + raisonSociale + '\'' +
                ", numRue='" + numRue + '\'' +
                ", nomRue='" + nomRue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresseMail='" + adresseMail + '\'' +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
