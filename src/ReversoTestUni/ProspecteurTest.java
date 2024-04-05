package ReversoTestUni;

import DAO.ConnexionDAO;
import DAO.ProspectDAO;
import ReversoException.Dbexception;
import logiqueReverso.Prospect;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ProspecteurTest extends Prospect {

    @Test
    void testSetDateProspect() {
        Prospect mi=new Prospect();
        String res=mi.setDateProspect("21/07/23");
        System.out.println(res);
    }

    @Test
    void findA() throws SQLException {
        ProspectDAO prospectDAO = new ProspectDAO();
        ConnexionDAO con = new ConnexionDAO();
        Prospect res = prospectDAO.find(con, 3);
        System.out.println(res);
    }

    @Test
    void update() throws SQLException, Dbexception {
        ProspectDAO prospectDAO = new ProspectDAO();
        ConnexionDAO con = new ConnexionDAO();

        // Obtenir un objet Client à mettre à jour
        Prospect res = prospectDAO.update(con, prospectDAO.find(con,1));

        System.out.println(res);
    }
}