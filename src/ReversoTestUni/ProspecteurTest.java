package ReversoTestUni;

import logiqueReverso.Prospect;
import org.junit.jupiter.api.Test;

class ProspecteurTest extends Prospect {

    @Test
    void testSetDateProspect() {
        Prospect mi=new Prospect();
        String res=mi.setDateProspect("21/07/23");
        System.out.println(res);
    }
}