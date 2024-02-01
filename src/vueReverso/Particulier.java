package vueReverso;

import Enum.*;
import logiqueReverso.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class Particulier extends JFrame {
    Prospect prospect;
    Client  client;

    int i;

    int j;
    private final AccueilForm accueilForm;
    private JPanel panel1;
    private JTextField iden;
    private JTextField RaisonSociale;
    private JTextField NumDeRue;
    private JTextField NomDeRue;
    private JTextField CodePostal;
    private JTextField Ville;
    private JTextField Telephone;
    private JTextField AdresseMail;
    private JTextField Commentaire;
    private JTextField dateProspect;
    private JButton Ok;
    private JButton Quitter;
    private JPanel panelValiQuit;
    private JTextField chiffreAffaire;
    private JTextField nombreEmploye;
    private JPanel panelClient;
    private JTextField prospectInteret2;
    private JPanel panelProspect;
    private JLabel DtProsp;
    private JLabel PrspInter;
    private JLabel ChffAffa;
    private JLabel NbrEmpl;


    public Particulier(AccueilForm accueilForm) {
        this.accueilForm=accueilForm;
        Init();
    }
    public void Init() {
        setVisible(true);
        setTitle("particulier");
        setSize(400, 600);
        setDefaultCloseOperation(3);
        this.add(panel1);
        panel1.setVisible(true);
        panelClient.setVisible(false);
        panelProspect.setVisible(false);
        panelValiQuit.setVisible(true);
        config();
    }
    public void config() {
        Quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public void configAjouter(Crud crud, TypeSociete typeSociete) {
        iden.setEditable(true);
        switch (typeSociete) {
            case CLIENT -> {
                panelClient.setVisible(true);
                ChffAffa.setVisible(true);
                NbrEmpl.setVisible(true);
                panelProspect.setVisible(false);
                DtProsp.setVisible(false);
                PrspInter.setVisible(false);
                switch (crud) {
                    case AJOUTER -> {
                        enableFields(true);
                        enableClientFields(Crud.AJOUTER);
                    }
                    case SUPPRIMER -> {
                        enableFields(false);
                        enableClientFields(Crud.SUPPRIMER);
                    }
                }
            }
            case PROSPECT -> {
                panelProspect.setVisible(true);
                DtProsp.setVisible(true);
                PrspInter.setVisible(true);
                panelClient.setVisible(false);
                ChffAffa.setVisible(false);
                NbrEmpl.setVisible(false);
                switch (crud) {
                    case AJOUTER -> {
                        enableFields(true);
                        enableProspectFields(Crud.AJOUTER);
                    }
                    case SUPPRIMER -> {
                        enableFields(false);
                        enableProspectFields(Crud.SUPPRIMER);
                    }
                }
            }
        }
    }
    public void enableFields(boolean enable) {
        RaisonSociale.setEditable(enable);
        NumDeRue.setEditable(enable);
        NomDeRue.setEditable(enable);
        CodePostal.setEditable(enable);
        Ville.setEditable(enable);
        Telephone.setEditable(enable);
        AdresseMail.setEditable(enable);
        Commentaire.setEditable(enable);
    }
    public void enableClientFields(Crud crud) {
        switch (crud){
            case AJOUTER -> {
                chiffreAffaire.setEditable(true);
                nombreEmploye.setEditable(true);
            }
            case SUPPRIMER -> {
                chiffreAffaire.setEditable(false);
                nombreEmploye.setEditable(false);
            }
        }
    }
    public void enableProspectFields(Crud crud) {
        switch (crud){
            case AJOUTER -> {
                dateProspect.setEditable(true);
                prospectInteret2.setEditable(true);
            }
            case SUPPRIMER -> {
                dateProspect.setEditable(false);
                prospectInteret2.setEditable(false);
            }
        }
    }
    public void donneesSociete(Societe societe){
        iden.setText(String.valueOf(societe.getId()));
        RaisonSociale.setText(societe.getRaisonSociale());
        NumDeRue.setText(societe.getNumRue());
        NomDeRue.setText(societe.getNomRue());
        CodePostal.setText(societe.getCodePostal());
        Ville.setText(societe.getVille());
        Telephone.setText(societe.getTelephone());
        AdresseMail.setText(societe.getAdresseMail());
        Commentaire.setText(societe.getCommentaire());
    }
    public void transfertDonnees(TypeSociete typeSociete,Crud crud) {
        switch (typeSociete){
            case CLIENT -> {
                i = accueilForm.getComboBoxClient().getSelectedIndex();
                System.out.println(i);
                if (i >= 0) {
                    Client client = CollectClient.listClient.get(i-1);
                    donneesSociete(client);
                    chiffreAffaire.setText(String.valueOf(client.getChiffreAffaire()));
                    nombreEmploye.setText(String.valueOf(client.getNombreEmployes()));
                    switch (crud) {
                        case MODIFIER -> {
                            configAjouter(Crud.AJOUTER, TypeSociete.CLIENT);
                            valider(TypeSociete.CLIENT,Crud.MODIFIER);
                        }
                        case SUPPRIMER -> {
                            configAjouter(Crud.SUPPRIMER, TypeSociete.CLIENT);
                            valider(TypeSociete.CLIENT,Crud.SUPPRIMER);
                        }
                    }
                }
            }
            case PROSPECT -> {
                j = accueilForm.getComboBoxProspect().getSelectedIndex();
                if (j >= 0) {
                    Prospect prospect = CollectProspect.listProspect.get(j-1);
                    donneesSociete(prospect);
                    dateProspect.setText(prospect.getDateProspect());
                    prospectInteret2.setText(prospect.getProspectInteresse());
                    switch (crud) {
                        case MODIFIER -> {
                            configAjouter(Crud.AJOUTER, TypeSociete.PROSPECT);
                            valider(TypeSociete.PROSPECT,Crud.MODIFIER);
                        }
                        case SUPPRIMER -> {
                            configAjouter(Crud.SUPPRIMER, TypeSociete.PROSPECT);
                            valider(TypeSociete.PROSPECT,Crud.SUPPRIMER);
                        }
                    }
                }
            }
        }
    }
    public void AjouterClientProspect(TypeSociete typeSociete, Crud crud) {
        switch (typeSociete) {
            case CLIENT -> {
                switch (crud){
                    case AJOUTER -> {
                if (Objects.equals(client, new Client())) {
                    client.setId(CollectClient.listClient.size() + 1);
                }
                    }
                    case MODIFIER -> {
                        if (Objects.equals(client, new Client())){
                            iden.setEnabled(false);
                        }
                    }
                }
                CollectClient.listClient.add(
                        new Client(
                                Integer.parseInt(iden.getText()),
                                RaisonSociale.getText(),
                                NumDeRue.getText(),
                                NomDeRue.getText(),
                                CodePostal.getText(),
                                Ville.getText(),
                                Telephone.getText(),
                                AdresseMail.getText(),
                                Commentaire.getText(),
                                Double.parseDouble(chiffreAffaire.getText()),
                                Integer.parseInt(nombreEmploye.getText())
                        )
                );
            }
            case PROSPECT -> {
                switch (crud){
                    case AJOUTER -> {
                        if (Objects.equals(prospect, new Prospect())) {
                            prospect.setId(CollectProspect.listProspect.size() + 1);
                        }
                    }
                    case MODIFIER -> {
                        if (Objects.equals(prospect, new Prospect())) {
                            iden.setEnabled(false);
                        }
                    }
                }
                CollectProspect.listProspect.add(
                        new Prospect(
                                Integer.parseInt(iden.getText()),
                                RaisonSociale.getText(),
                                NumDeRue.getText(),
                                NomDeRue.getText(),
                                CodePostal.getText(),
                                Ville.getText(),
                                Telephone.getText(),
                                AdresseMail.getText(),
                                Commentaire.getText(),
                                dateProspect.getText(),
                                prospectInteret2.getText()
                        )
                );
            }
        }
    }
    public void SupCliPros(TypeSociete typeSociete){
        switch (typeSociete){
            case CLIENT -> {
                dispose();
                i = accueilForm.getComboBoxClient().getSelectedIndex();
                CollectClient.listClient.remove(i-1);
                new SocieteForm().remplirSociete(TypeSociete.CLIENT);
            }
            case PROSPECT -> {
                dispose();
                j = accueilForm.getComboBoxProspect().getSelectedIndex();
                CollectProspect.listProspect.remove(j-1);
                new SocieteForm().remplirSociete(TypeSociete.PROSPECT);
            }
        }
    }
    public void valider(TypeSociete typeSociete, Crud crud) {
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (typeSociete) {
                    case CLIENT -> {
                        switch (crud) {
                            case AJOUTER -> {
                                dispose();
                                configAjouter(Crud.AJOUTER, TypeSociete.CLIENT);
                                AjouterClientProspect(TypeSociete.CLIENT, Crud.AJOUTER);
                                new SocieteForm().remplirSociete(TypeSociete.CLIENT);
                            }
                            case MODIFIER -> {
                                AjouterClientProspect(TypeSociete.CLIENT, Crud.MODIFIER);
                                configAjouter(Crud.AJOUTER, TypeSociete.CLIENT);
                                SupCliPros(TypeSociete.CLIENT);
                            }
                            case SUPPRIMER -> {
                                configAjouter(Crud.SUPPRIMER, TypeSociete.CLIENT);
                                SupCliPros(TypeSociete.CLIENT);
                            }
                        }
                    }
                    case PROSPECT -> {
                        switch (crud) {
                            case AJOUTER -> {
                                dispose();
                                AjouterClientProspect(TypeSociete.PROSPECT,Crud.AJOUTER);
                                configAjouter(Crud.AJOUTER, TypeSociete.PROSPECT);
                                new SocieteForm().remplirSociete(TypeSociete.PROSPECT);
                            }
                            case MODIFIER -> {
                                AjouterClientProspect(TypeSociete.PROSPECT,Crud.MODIFIER);
                                configAjouter(Crud.AJOUTER, TypeSociete.PROSPECT);
                                SupCliPros(TypeSociete.PROSPECT);
                            }
                            case SUPPRIMER -> {
                                configAjouter(Crud.SUPPRIMER, TypeSociete.PROSPECT);
                                SupCliPros(TypeSociete.PROSPECT);
                            }
                        }
                    }
                }
            }
        });
    }
}


