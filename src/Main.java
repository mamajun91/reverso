import log.FormatterReverso;
import logiqueReverso.Client;
import logiqueReverso.Prospect;
import vueReverso.AccueilForm;

import java.io.IOException;
import java.util.logging.FileHandler;

import static log.LogReverso.LOGGER;

public  class Main {

    public static void main(String[] args) throws IOException {

        Client junior = new Client(1, "junior", "1", "rue de la Bibliothèque", "57300", "Hagondange",
                "0624199598", "martinmahob@gmail.com", "interessé", 1000.35, 5) {
        };
        Client junio = new Client(2, "junio", "1", "Avenue du Pacx", "57175", "gandrange",
                "0665197759", "martiahob@gmail.com", "interessé", 100067.35, 56) {
        };

        Prospect man = new Prospect(1, "man", "2", "rue de tresor", "57300", "Hagondange",
                "0624199590", "mahob@gmail.com", "interessé", "28/08/1991", "oui") {
        };

        


        new AccueilForm();

        FileHandler fh = new FileHandler("LogReverso.log", true);
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(fh);
        fh.setFormatter(new FormatterReverso());

    }
}