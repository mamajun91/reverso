package ReversoTestUni;

import DAO.Client_DAO;
import DAO.ConnexionDAO;
import ReversoException.Dbexception;
import logiqueReverso.Client;
import logiqueReverso.Prospect;
import org.junit.jupiter.api.Test;
import vueReverso.AccueilForm;

import java.sql.SQLException;
import java.util.ArrayList;

class Client_DAOTest extends Prospect {

    @Test
    void findAll() throws SQLException {

        Client_DAO clicli = new Client_DAO();
        ConnexionDAO con = new ConnexionDAO();
        ArrayList<Client> res = clicli.findAll(con);
        System.out.println(res);

    }
    @Test
    void findA() throws SQLException {

        Client_DAO clicli = new Client_DAO();
        ConnexionDAO con = new ConnexionDAO();
        Client res = clicli.find(con,1);
        System.out.println(res);


    }

    @Test
    void update() throws SQLException, Dbexception {

        Client_DAO clicli = new Client_DAO();
        ConnexionDAO con = new ConnexionDAO();

        // Obtenir un objet Client à mettre à jour
        Client res = clicli.update(con,clicli.find(con,1));

        System.out.println(res);
    }
}