package vueReverso;


import Enum.*;
import logiqueReverso.*;




import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AccueilForm extends JFrame {

    private JPanel panel1;
    private JButton Client;
    private JButton Prospect;
    private JButton AJOUTER;
    private JButton RETOUR;
    private JButton Quitter;
    private JPanel PanelCombobox;
    private JPanel panelCrud;
    private JPanel panelRETQUIT;
    private JPanel panelselectSocie;
    private JPanel panelSelect;
    public JComboBox<String> comboBoxClient;
    private JButton MODIFIER;
    private JButton SUPPRIMER;
    private JButton LIRE;
    public JComboBox<String> comboBoxProspect;
    private JButton QUITTER;

    public  JComboBox<String> getComboBoxClient() {
        return comboBoxClient;
    }

    public  JComboBox<String> getComboBoxProspect() {
        return comboBoxProspect;
    }

    public AccueilForm() {
        Init();
        config();
    }
    public void Init() {
        setVisible(true);
        setTitle("Societes");
        setSize(400, 400);
        setDefaultCloseOperation(3);
        this.add(panel1);
        panel1.setVisible(true);
        panelRETQUIT.setVisible(false);
        panelSelect.setVisible(true);
        panelselectSocie.setVisible(true);
        panelCrud.setVisible(false);
        PanelCombobox.setVisible(false);
    }
// actions boutons Bienvenue et selction Client ou Prospect...........................................................
    public void config() {

        Client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurationCliPros();
                AJOUTER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {new Particulier(AccueilForm.this).valider(TypeSociete.CLIENT,Crud.AJOUTER);}
                });
                LIRE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {new SocieteForm().remplirSociete(TypeSociete.CLIENT);}
                });
                MODIFIER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {combobox(Crud.MODIFIER,TypeSociete.CLIENT);}
                });
                SUPPRIMER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {combobox(Crud.SUPPRIMER,TypeSociete.CLIENT);}
                });
            }
        });
        Prospect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurationCliPros();
                AJOUTER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {new Particulier(AccueilForm.this).valider(TypeSociete.PROSPECT,Crud.AJOUTER);}
                });
                LIRE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {new SocieteForm().remplirSociete(TypeSociete.PROSPECT);}
                });
                MODIFIER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {combobox(Crud.MODIFIER,TypeSociete.PROSPECT);}
                });
                SUPPRIMER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {combobox(Crud.SUPPRIMER,TypeSociete.PROSPECT);}
                });
            }
        });
        RETOUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelRETQUIT.setVisible(true);
                panelSelect.setVisible(true);
                panelselectSocie.setVisible(false);
                panelCrud.setVisible(true);
                PanelCombobox.setVisible(false);
            }
        });
        QUITTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new AccueilForm();
                dispose();
            }
        });
    }
    public void configurationCliPros(){
        panelRETQUIT.setVisible(true);
        panelSelect.setVisible(true);
        panelselectSocie.setVisible(false);
        panelCrud.setVisible(true);
        PanelCombobox.setVisible(false);
    }
    public void configModifierSupprimer(TypeSociete typeSociete){
        panelRETQUIT.setVisible(true);
        panelSelect.setVisible(true);
        panelselectSocie.setVisible(false);
        panelCrud.setVisible(false);
        PanelCombobox.setVisible(true);
        switch (typeSociete) {
            case CLIENT -> {
                comboBoxClient.setVisible(true);
                comboBoxProspect.setVisible(false);
                remplirCombobox(TypeSociete.CLIENT);
            }
            case PROSPECT -> {
                comboBoxProspect.setVisible(true);
                comboBoxClient.setVisible(false);
                remplirCombobox(TypeSociete.PROSPECT);
            }
        }
    }
    public void remplirCombobox(TypeSociete typeSociete) {
        switch (typeSociete) {
            case CLIENT -> {
                for (Client client : CollectClient.listClient) {
                    comboBoxClient.addItem(client.getRaisonSociale());
                }
            }
            case PROSPECT -> {
                for (Prospect prospect : CollectProspect.listProspect) {
                    comboBoxProspect.addItem(prospect.getRaisonSociale());
                }
            }
        }
    }
    public void combobox(Crud crud , TypeSociete typeSociete){
        switch (typeSociete){
            case CLIENT -> {
                configModifierSupprimer(TypeSociete.CLIENT);
                comboBoxClient.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        switch (crud) {
                            case MODIFIER -> {
                                new Particulier(AccueilForm.this).transfertDonnees(TypeSociete.CLIENT,Crud.MODIFIER);
                                dispose();
                            }
                            case SUPPRIMER -> {
                                new Particulier(AccueilForm.this).transfertDonnees(TypeSociete.CLIENT,Crud.SUPPRIMER);
                                dispose();
                            }
                        }
                    }
                });
            }
            case PROSPECT -> {
                configModifierSupprimer(TypeSociete.PROSPECT);
                comboBoxProspect.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        switch (crud) {
                            case MODIFIER -> {
                                new Particulier(AccueilForm.this).transfertDonnees(TypeSociete.PROSPECT,Crud.MODIFIER);
                                dispose();
                            }
                            case SUPPRIMER -> {
                                new Particulier(AccueilForm.this).transfertDonnees(TypeSociete.PROSPECT,Crud.SUPPRIMER);
                                dispose();
                            }
                        }
                    }
                });
            }
        }
    }
}


