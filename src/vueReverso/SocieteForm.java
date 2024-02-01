package vueReverso;
import Enum.*;
import logiqueReverso.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SocieteForm extends JFrame {
    private JPanel panel1;
    private JTable tableClient;
    private JTable tableProspect;
    private JPanel panelClient;
    private JPanel panelProspect;
    private JButton quitter;
    private JScrollPane scrollPaneClient;
    private JScrollPane scrollPanelProspect;
    private JPanel panelQuit;


    public SocieteForm() {
        Init();
    }

    public void Init() {
        setVisible(true);
        setTitle("societes");
        setSize(500, 500);
        setDefaultCloseOperation(3);
        this.add(panel1);
        panel1.setVisible(true);
        quitter.setVisible(true);
        panelQuit.setVisible(true);
    }

    public void ConfigurationTable(TypeSociete typeSociete) {
        switch (typeSociete) {
            case CLIENT -> {
                panelQuit.setVisible(true);
                panelClient.setVisible(true);
                tableClient.setVisible(true);
                panelProspect.setVisible(false);
                scrollPaneClient.setVisible(true);
                tableProspect.setVisible(false);
            }
            case PROSPECT -> {
                panelProspect.setVisible(true);
                tableProspect.setVisible(true);
                panelClient.setVisible(false);
                tableClient.setVisible(false);
            }
        }
    }


    public void remplirSociete(TypeSociete typeSociete) {
        switch (typeSociete) {
            case CLIENT -> {
                ConfigurationTable(TypeSociete.CLIENT);
                String[] tableBaseClient = {"identifiant", "Raison Sociale", "Num rue", "Nom rue", "Code Postal", "Ville", "Téléphone", "Adresse Mail", "Commentaire",
                        "Chiffre Affaire", "Nombre Employés"};
                DefaultTableModel model = new DefaultTableModel();
                for (String colonne : tableBaseClient) {
                    model.addColumn(colonne);
                }
                for (Client client : CollectClient.listClient) {
                    Object[] rowData = {
                            client.getId(),
                            client.getRaisonSociale(),
                            client.getNumRue(),
                            client.getNomRue(),
                            client.getCodePostal(),
                            client.getVille(),
                            client.getTelephone(),
                            client.getAdresseMail(),
                            client.getCommentaire(),
                            client.getChiffreAffaire(),
                            client.getNombreEmployes()
                    };
                    model.addRow(rowData);
                }
                tableClient = new JTable(model);
                scrollPaneClient = new JScrollPane(tableClient);
                add(scrollPaneClient);
            }
            case PROSPECT -> {
                ConfigurationTable(TypeSociete.PROSPECT);
                String[] tableBaseProspecteur = {"idendtifiant", "Raison Sociale", "Num de rue", "Nom de rue", "Code Postal", "Ville", "Téléphone", "Adresse Mail", "Commentaire",
                        "Date Prospection", "Prospecteur Interessé"};
                DefaultTableModel model1 = new DefaultTableModel();
                for (String colonne : tableBaseProspecteur) {
                    model1.addColumn(colonne);
                }
                for (Prospect prospect : CollectProspect.listProspect) {
                    Object[] rowData = {
                            prospect.getId(),
                            prospect.getRaisonSociale(),
                            prospect.getNumRue(),
                            prospect.getNomRue(),
                            prospect.getCodePostal(),
                            prospect.getVille(),
                            prospect.getTelephone(),
                            prospect.getAdresseMail(),
                            prospect.getCommentaire(),
                            prospect.getDateProspect(),
                            prospect.getProspectInteresse()
                    };
                    model1.addRow(rowData);
                }
                tableProspect = new JTable(model1);
                scrollPanelProspect = new JScrollPane(tableProspect);
                add(scrollPanelProspect);
            }
        }
    }
}
