package vueReverso;
import Enum.*;
import ReversoException.CollectionIllegalException;
import logiqueReverso.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SocieteForm extends JFrame {
    private JPanel panel1;
    private JTable tableClient;
    private JTable tableProspect;
    private JButton quitter;
    private JScrollPane scrollPaneClient;
    private JScrollPane scrollPanelProspect;
    private JButton details;
    private JPanel panelQuitter;


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
        details.setVisible(true);
    }

    public void ConfigurationTable(TypeSociete typeSociete) {
        switch (typeSociete) {
            case CLIENT -> {
                tableClient.setVisible(true);
                scrollPaneClient.setVisible(true);
                scrollPanelProspect.setVisible(false);
                tableProspect.setVisible(false);
                panelQuitter.setVisible(true);
            }
            case PROSPECT -> {
                tableProspect.setVisible(true);
                scrollPanelProspect.setVisible(true);
                tableClient.setVisible(false);
                scrollPaneClient.setVisible(false);
                panelQuitter.setVisible(true);
            }
        }
    }}

/**
    public void remplirSociete(TypeSociete typeSociete) throws CollectionIllegalException {
        switch (typeSociete) {
            case CLIENT -> {
                ConfigurationTable(TypeSociete.CLIENT);
                if (CollectClient.listClient != null && !CollectClient.listClient.isEmpty()) {
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
                } else {
                    throw new CollectionIllegalException("La liste des clients est vide.");
                }
            }
            case PROSPECT -> {
                ConfigurationTable(TypeSociete.PROSPECT);
                if (CollectProspect.listProspect != null && !CollectProspect.listProspect.isEmpty()) {
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
                }else{
                    throw new CollectionIllegalException("La liste des prospecteurs est vide.");
                }
            }
        }
    }
}
**/