import logiqueReverso.Client;
import logiqueReverso.CollectClient;
import logiqueReverso.CollectProspect;
import logiqueReverso.Prospect;
import vueReverso.AccueilForm;

import java.util.ArrayList;

public  class Main {

    public static void main(String[] args) {

        Client junior = new Client(0, "junior", "1", "rue de la Bibliothèque", "57300", "Hagondange",
                "0624199598", "martinmahob@gmail.com", "interessé", 1000.35, 5) {
        };
        Client junio = new Client(1, "junio", "1", "Avenue du Pacx", "57175", "gandrange",
                "0665197759", "martiahob@gmail.com", "interessé", 100067.35, 56) {
        };

        Prospect man = new Prospect(0, "man", "2", "rue de tresor", "57300", "Hagondange",
                "0624199590", "mahob@gmail.com", "interessé", "28/08/1991", "oui") {
        };

        CollectProspect.listProspect.add(man);
        CollectClient.listClient.add(junior);
        CollectClient.listClient.add(junio);


        new AccueilForm();

        System.out.println(junior.getId());
        System.out.println(junio.getId());
        System.out.println(man.getId());
    }
}