package DAO;
import ReversoException.Dbexception;
import logiqueReverso.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import static log.LogReverso.LOGGER;

public class Client_DAO {

    // Cette variable ne semble pas utilisée dans votre classe, vous pouvez la supprimer.
    // int id_client;

    // Méthode pour remplir un objet Client avec les données d'un ResultSet
    private static void fillClientData(Client client, ResultSet rs) throws SQLException {

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

    // Méthode pour récupérer tous les clients
    public ArrayList<Client> findAll(ConnexionDAO con) throws SQLException {
        Statement stmt = null;
        ArrayList<Client> clients = new ArrayList<>();
        String query = "SELECT s.*, c.chiffre_affaire, c.nombre_employe " +
                "FROM reverso.societe s " +
                "INNER JOIN reverso.client c ON s.id_societe = c.id_societe ";
        try {
            stmt = con.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Client client = new Client();
                fillClientData(client, rs);
                clients.add(client);
            }
        } catch (SQLException | Dbexception e) {
            LOGGER.log(Level.parse("error"), e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return clients;
    }

    // Méthode pour trouver un client par son identifiant
    public static Client find(ConnexionDAO con, int id_client) throws SQLException {
        PreparedStatement pstmt = null;
        Client client = new Client();
        String query = "SELECT s.*, c.chiffre_affaire, c.nombre_employe " +
                "FROM reverso.societe s " +
                "INNER JOIN reverso.client c ON s.id_societe = c.id_societe " +
                "WHERE s.id_societe = ?";
        try {
            pstmt = con.getConnection().prepareStatement(query);
            pstmt.setInt(1, id_client); // Spécifier l'identifiant du client dans la requête
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fillClientData(client, rs);
            }
        } catch (SQLException | Dbexception e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return client;
    }

    // Méthode pour créer un nouveau client
    public void create(ConnexionDAO con, Client client) throws SQLException {
        String querySociete = "INSERT INTO reverso.societe (raison_sociale, num_rue, nom_rue, code_postal, ville, telephone, adresse_mail, commentaire) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String queryClient = "INSERT INTO reverso.client (id_societe, chiffre_affaire, nombre_employe) " +
                "VALUES (LAST_INSERT_ID(), ?, ?)";
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtClient = con.getConnection().prepareStatement(queryClient)) {
            // Remplir les paramètres de la requête pour la table société
            stmtSociete.setString(1, client.getRaisonSociale());
            stmtSociete.setString(2, client.getNumRue());
            stmtSociete.setString(3, client.getNomRue());
            stmtSociete.setString(4, client.getCodePostal());
            stmtSociete.setString(5, client.getVille());
            stmtSociete.setString(6, client.getTelephone());
            stmtSociete.setString(7, client.getAdresseMail());
            stmtSociete.setString(8, client.getCommentaire());
            stmtSociete.executeUpdate();

            // Remplir les paramètres de la requête pour la table client
            stmtClient.setDouble(1, client.getChiffreAffaire());
            stmtClient.setInt(2, client.getNombreEmployes());
            stmtClient.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.parse("error"), e.getMessage());
            throw new RuntimeException(e);
        } catch (Dbexception e) {
            throw new RuntimeException(e);
        }
    }

    // Méthode pour mettre à jour les informations d'un client
    public Client update(ConnexionDAO con, Client updatedClient) throws Dbexception, SQLException {
        String querySociete = "UPDATE reverso.societe " +
                "SET raison_sociale = ?, num_rue = ?, nom_rue = ?, code_postal = ?, ville = ?, telephone = ?, adresse_mail = ?, commentaire = ? " +
                "WHERE id_societe = ?";
        String queryClient = "UPDATE reverso.client " +
                "SET chiffre_affaire = ?, nombre_employe = ? " +
                "WHERE s.id_societe = ?";
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtClient = con.getConnection().prepareStatement(queryClient)) {
            // Remplir les paramètres de la requête pour la table société
            stmtSociete.setString(1, updatedClient.getRaisonSociale());
            stmtSociete.setString(2, updatedClient.getNumRue());
            stmtSociete.setString(3, updatedClient.getNomRue());
            stmtSociete.setString(4, updatedClient.getCodePostal());
            stmtSociete.setString(5, updatedClient.getVille());
            stmtSociete.setString(6, updatedClient.getTelephone());
            stmtSociete.setString(7, updatedClient.getAdresseMail());
            stmtSociete.setString(8, updatedClient.getCommentaire());
            stmtSociete.executeUpdate();

            // Remplir les paramètres de la requête pour la table client
            stmtClient.setDouble(1, updatedClient.getChiffreAffaire());
            stmtClient.setInt(2, updatedClient.getNombreEmployes());
            stmtClient.executeUpdate();
            System.out.println(updatedClient);
        } catch (SQLException e) {
            LOGGER.log(Level.parse("error"), e.getMessage());
            throw new RuntimeException(e);
        }
        return updatedClient;
    }

    // Méthode pour supprimer un client
    public void delete(ConnexionDAO con, Client client) throws SQLException, Dbexception {
        String querySociete = "DELETE FROM reverso.societe WHERE id_societe = ?";
        String queryClient = "DELETE FROM reverso.client WHERE id_societe = ?";
        try (PreparedStatement stmtSociete = con.getConnection().prepareStatement(querySociete);
             PreparedStatement stmtClient = con.getConnection().prepareStatement(queryClient)) {
            // Spécifier l'identifiant du client dans la requête

            // Exécuter les requêtes de suppression
            stmtSociete.executeUpdate();
            stmtClient.executeUpdate();
        }
    }

    public ArrayList<Client> getClients(ConnexionDAO con, String querySociete, String queryClient) throws SQLException {
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




