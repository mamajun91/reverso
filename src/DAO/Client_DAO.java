package DAO;

import ReversoException.Dbexception;
import com.mysql.cj.x.protobuf.Mysqlx;
import log.LogReverso;
import logiqueReverso.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import static log.LogReverso.LOGGER;

public class Client_DAO {
    private void findAllOne(Client client, ResultSet rs) throws SQLException {
        client.setId(rs.getInt("id_societe"));
        client.setRaisonSociale(rs.getString("raison_sociale"));
        client.setNumRue(rs.getString("num_rue"));
        client.setNomRue(rs.getString("nom_rue"));
        client.setCodePostal(rs.getString("code_postal"));
        client.setVille(rs.getString("ville"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresseMail(rs.getString("adresse_mail"));
        client.setCommentaire(rs.getString("commentaire"));
        client.setChiffreAffaire(rs.getDouble("chiffre_affaire"));
        client.setNombreEmployes(rs.getInt("nombre_employe"));
    }
    public ArrayList<Client> findAll(ConnexionDAO con) throws SQLException {
        Statement stmt = null;
        String query = "SELECT s.*, c.chiffre_affaire, c.nombre_employe " +
                "FROM reverso.societe s " +
                "INNER JOIN reverso.client c ON s.id_societe = c.id_societe";
        ArrayList<Client> clients = new ArrayList<>();
        Client client = new Client();
        try {
            stmt = con.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                findAllOne(client, rs); // Remplissez cette instance avec les données actuelles
                clients.add(client); // Ajoutez cette instance à la liste des clients
            }
            rs.close();
        } catch (SQLException | Dbexception e) {
            LOGGER.log(Level.parse("error"),e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return clients;
    }

    public Client find(ConnexionDAO con) throws SQLException {
        PreparedStatement pstmt = null;
        String query = "SELECT s.*, c.chiffre_affaire, c.nombre_employe " +
                "FROM reverso.societe s " +
                "INNER JOIN reverso.client c ON s.id_societe = c.id_societe " +
                "WHERE c.id_societe = 1";
        Client client = new Client();
        try {
            pstmt = con.getConnection().prepareStatement(query);
            // Exécuter la requête préparée
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Remplir l'objet Client avec les données récupérées
                findAllOne(client, rs);
            }
            rs.close();
        } catch (SQLException | Dbexception e) {
            throw new RuntimeException(e);
        } finally {
            // Fermer le PreparedStatement et le Scanner
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.parse("error"),e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return client;
    }
        public ArrayList<Client> create(ConnexionDAO con) throws SQLException {
        String querySociete = "INSERT INTO reverso.societe (raison_sociale,num_rue,nom_rue,code_postal,ville,telephone,adresse_mail,commentaire) "+
                "VALUES (?,?,?,?,?,?,?,?,?)";
        String queryClient = "INSERT INTO reverso.client (id_societe, chiffre_affaire, nombre_employe) "+
                "VALUES (LAST_INSERT_ID(), ?, ?)";
        return getClients(con, querySociete, queryClient);
    }

    public Client update(ConnexionDAO con) throws Dbexception, SQLException {
        String queryClient = "UPDATE reverso.client " +
                "SET chiffre_affaire = 2347778.34, nombre_employe = 45 " +
                "WHERE id_societe = 1";
        Client client = find(con);
        try (PreparedStatement stmtClient = con.getConnection().prepareStatement(queryClient)) {
            stmtClient.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.parse("error"),e.getMessage());
            throw new RuntimeException(e);
        }
        return client;
    }

    public ArrayList<Client> delete(ConnexionDAO con) throws SQLException {
        Statement stmt = null;
        String querySociete = "DELETE FROM reverso.societe WHERE id_societe = 1";
        String queryClient= "DELETE FROM reverso.client WHERE id_societe = 1";
        return getClients(con, querySociete, queryClient);
    }
    private ArrayList<Client> getClients(ConnexionDAO con, String querySociete, String queryClient) throws SQLException {
        ArrayList<Client> clients = new ArrayList<>();
        clients=findAll(con);
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtClient = con.getConnection().prepareStatement(queryClient)) {
            stmtSociete.executeUpdate();
            stmtClient.executeUpdate();
        } catch (SQLException | Dbexception e) {
            LOGGER.log(Level.parse("error"),e.getMessage());
            throw new RuntimeException(e);
        }
        return clients;
    }
}




