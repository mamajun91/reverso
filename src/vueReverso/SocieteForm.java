package vueReverso;
import DAO.ConnexionDAO;
import Enum.*;
import ReversoException.CollectionIllegalException;
import logiqueReverso.*;
import DAO.Client_DAO;
import DAO.ProspectDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class SocieteForm extends JFrame {
    private JPanel panel1;
    private JTable tableClient;
    private JTable tableProspect;

    private JScrollPane scrollPaneClient;
    private JScrollPane scrollPanelProspect;
    private JButton buttonQuitter;
    private JButton buttonDetails;

    Client_DAO clientDao = new Client_DAO();
    ProspectDAO prospectDAO = new ProspectDAO();
    ConnexionDAO con = new ConnexionDAO();


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
        panel1.setLayout(new BorderLayout());

        // Création des boutons Quitter et Détails
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonDetails = new JButton("Détails");
        buttonQuitter = new JButton("Quitter");
        buttonPanel.add(buttonDetails);
        buttonPanel.add(buttonQuitter);
        panel1.add(buttonPanel, BorderLayout.SOUTH);

        buttonDetails.setVisible(true);
        buttonQuitter.setVisible(true);
    }

    public void ConfigurationTable(TypeSociete typeSociete) {
        switch (typeSociete) {
            case CLIENT -> {
                tableClient.setVisible(true);
                scrollPaneClient.setVisible(true);
                scrollPanelProspect.setVisible(false);
                tableProspect.setVisible(false);
                panel1.setVisible(true);

            }
            case PROSPECT -> {
                tableProspect.setVisible(true);
                scrollPanelProspect.setVisible(true);
                tableClient.setVisible(false);
                scrollPaneClient.setVisible(false);
                panel1.setVisible(true);

            }
        }
    }


    public void remplirSociete(TypeSociete typeSociete) throws CollectionIllegalException, SQLException {

        switch (typeSociete) {
            case CLIENT -> {
                ConfigurationTable(TypeSociete.CLIENT);
                if ( clientDao.findAll(con) != null && !clientDao.findAll(con).isEmpty()) {
                    String[] tableBaseClient = {"identifiant", "Raison Sociale", "Num rue", "Nom rue", "Code Postal", "Ville", "Téléphone", "Adresse Mail", "Commentaire",
                            "Chiffre Affaire", "Nombre Employés"};
                    DefaultTableModel model = new DefaultTableModel();
                    for (String colonne : tableBaseClient) {
                        model.addColumn(colonne);
                    }
                    for (Client client : clientDao.findAll(con)) {
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
                if (prospectDAO.findAll(con) != null && !prospectDAO.findAll(con).isEmpty()) {
                    String[] tableBaseProspecteur = {"identifiant", "Raison Sociale", "Num rue", "Nom rue", "Code Postal", "Ville", "Téléphone", "Adresse Mail", "Commentaire",
                            "Date Prospection", "Prospecteur Interessé"};
                    DefaultTableModel model1 = new DefaultTableModel();
                    for (String colonne : tableBaseProspecteur) {
                        model1.addColumn(colonne);
                    }
                    for (Prospect prospect : prospectDAO.findAll(con)) {
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
