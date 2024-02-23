package ReversoTestUni;

import logiqueReverso.Client;
import logiqueReverso.CollectClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTestTest extends Client {

    @Test
    void testSetChiffreAffaire() {
        Client mar = new Client();
        double res = mar.setChiffreAffaire(56);
        System.out.println(res);
    }

    @Test
    void testSetNombreEmployes() {
        Client m= new Client();
        int resm =m.setNombreEmployes(0);
        System.out.println(resm);
    }
}