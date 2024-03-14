package ReversoTestUni;

import DAO.ConnexionDAO;
import ReversoException.Dbexception;
import logiqueReverso.Prospect;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class ConnexionDAOTest extends Prospect {

    @Test
    void getConnection() throws Dbexception {
        ConnexionDAO con = new ConnexionDAO();
        Connection res = con.getConnection();
        System.out.println(res);
    }
}