package DAO;

import ReversoException.Dbexception;
import logiqueReverso.Prospect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;


import static log.LogReverso.LOGGER;

public class ProspectDAO {
    int id_societe;
    private void findAllOne(Prospect prospect, ResultSet rs) throws SQLException {

       prospect.setRaisonSociale(rs.getString("raison_sociale"));
       prospect.setNumRue(rs.getString("num_rue"));
       prospect.setNomRue(rs.getString("nom_rue"));
       prospect.setCodePostal(rs.getString("code_postal"));
       prospect.setVille(rs.getString("ville"));
       prospect.setTelephone(rs.getString("telephone"));
       prospect.setAdresseMail(rs.getString("adresse_mail"));
       prospect.setCommentaire(rs.getString("commentaire"));
       prospect.setDateProspect(rs.getString("date_prospect"));
       prospect.setProspectInteresse(rs.getString("prospect_interess"));
    }
    public ArrayList<Prospect> findAll(ConnexionDAO con) throws SQLException {
        Statement stmt = null;
        String query ="SELECT s.*, p.date_prospect, p.prospect_interess " +
                "FROM reverso.societe s " +
                "INNER JOIN reverso.prospect p ON s.id_societe = p.id_societe ";
        ArrayList<Prospect> prospects = new ArrayList<>();
        try {
            stmt = con.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Prospect prospect = new Prospect(); 
                findAllOne(prospect, rs); 
                prospects.add(prospect);
            }
            rs.close();
        } catch (SQLException | Dbexception e) {
            LOGGER.log(Level.parse("error"), e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return prospects;
    }
    public Prospect find(ConnexionDAO con, int id_societe) throws SQLException {
        PreparedStatement pstmt = null;
        String query = "SELECT s.*, p.date_prospect, p.prospect_interess " +
                "FROM reverso.societe s  " +
                "INNER JOIN reverso.prospect p ON s.id_societe = p.id_societe " +
                "WHERE s.id_societe = p.id_societe ";
        Prospect prospect = new Prospect();
        try {
            pstmt = con.getConnection().prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                findAllOne(prospect, rs);
            }
            rs.close();
        } catch (SQLException | Dbexception e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.parse("error"), e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return prospect;
    }
    public ArrayList<Prospect> create(ConnexionDAO con, Prospect newProspect) throws SQLException {
        String querySociete ="INSERT INTO reverso.societe (raison_sociale,num_rue,nom_rue,code_postal,ville,telephone,adresse_mail,commentaire) "+
                "VALUES (?,?,?,?,?,?,?,?,?) ";
        String queryProspect = "INSERT INTO reverso.prospect (id_societe, date_prospect, prospect_interess) " +
                "VALUES (LAST_INSERT_ID(), ?, ?) ";
        return getProspects(con, querySociete, queryProspect);
    }
    public Prospect update(ConnexionDAO con, Prospect newProspect) throws Dbexception, SQLException {
        String querySociete = "UPDATE reverso.societe " +
                "SET raison_sociale = ?, num_rue = ?, nom_rue = ?, code_postal = ?, ville = ?, telephone = ?, adresse_mail = ?, commentaire = ? " +
                "WHERE id_societe = ? ";
        String queryProspect = "UPDATE reverso.prospect " +
                "SET date_prospect=? " +
                "WHERE id_societe = id_societe ";
        newProspect = find(con, id_societe);
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtClient = con.getConnection().prepareStatement(queryProspect)) {
            stmtSociete.executeUpdate();
            stmtClient.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.parse("error"),e.getMessage());
            throw new RuntimeException(e);
        }
        return newProspect;
    }
    public ArrayList<Prospect> delete(ConnexionDAO con, Prospect id_societe) throws SQLException {
        Statement stmt = null;
        String querySociete = "DELETE FROM reverso.societe WHERE id_societe = id_societe ";
        String queryProspect= "DELETE FROM reverso.prospect WHERE id_societe = id_societe ";
        return getProspects(con, querySociete, queryProspect);
    }
    private ArrayList<Prospect> getProspects(ConnexionDAO con, String querySociete, String queryProspect) throws SQLException {
        ArrayList<Prospect> prospects = new ArrayList<>();
        prospects=findAll(con);
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtProspect = con.getConnection().prepareStatement(queryProspect)) {
            stmtSociete.executeUpdate();
            stmtProspect.executeUpdate();
        } catch (SQLException | Dbexception e) {
            LOGGER.log(Level.parse("error"),e.getMessage());
            throw new RuntimeException(e);
        }
        return prospects;
    }
}
