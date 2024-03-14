package vueReverso;


import Enum.*;
import ReversoException.CollectionIllegalException;
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
        /**config();**/
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
    }}
// actions boutons Bienvenue et selction Client ou Prospect...........................................................
   /** public void config() {

        Client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurationCliPros();
                AJOUTER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new Particulier(AccueilForm.this).config(TypeSociete.CLIENT, Crud.AJOUTER);
                        } catch (CollectionIllegalException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                });
                LIRE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new SocieteForm().remplirSociete(TypeSociete.CLIENT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème  au niveau des Collections");
                        }
                    }
                });
                MODIFIER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            combobox(Crud.MODIFIER,TypeSociete.CLIENT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème  au niveau des Collections");
                        }
                    }
                });
                SUPPRIMER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            combobox(Crud.SUPPRIMER,TypeSociete.CLIENT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème  au niveau des Collections");
                        }
                    }
                });
            }
        });
        Prospect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configurationCliPros();
                AJOUTER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new Particulier(AccueilForm.this).config(TypeSociete.PROSPECT, Crud.AJOUTER);
                        } catch (CollectionIllegalException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                LIRE.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            new SocieteForm().remplirSociete(TypeSociete.PROSPECT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème de au niveau des Collections");
                        }
                    }
                });
                MODIFIER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            combobox(Crud.MODIFIER,TypeSociete.PROSPECT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème  au niveau des Collections");
                        }
                    }
                });
                SUPPRIMER.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            combobox(Crud.SUPPRIMER,TypeSociete.PROSPECT);
                        } catch (CollectionIllegalException ex) {
                            JOptionPane.showConfirmDialog(null,"Problème  au niveau des Collections");
                        }
                    }
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
    public void configModifierSupprimer(TypeSociete typeSociete) throws CollectionIllegalException {
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
    public void remplirCombobox(TypeSociete typeSociete) throws CollectionIllegalException {
        switch (typeSociete) {
            case CLIENT -> {
                if (CollectClient.listClient !=null && !CollectClient.listClient.isEmpty()) {
                    for (Client client : CollectClient.listClient) {
                        comboBoxClient.addItem(client.getRaisonSociale());
                    }
                }else {
                    throw new CollectionIllegalException("La liste des clients est vide.");
                }
            }
            case PROSPECT -> {
                if (CollectProspect.listProspect != null && !CollectProspect.listProspect.isEmpty()) {
                    for (Prospect prospect : CollectProspect.listProspect) {
                        comboBoxProspect.addItem(prospect.getRaisonSociale());
                    }
                }else{
                    throw new CollectionIllegalException("La liste des prospecteurs est vide.");
                }
            }
        }
    }
    public void combobox(Crud crud , TypeSociete typeSociete) throws CollectionIllegalException {
        switch (typeSociete){
            case CLIENT -> {
                configModifierSupprimer(TypeSociete.CLIENT);
                comboBoxClient.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        switch (crud) {
                            case MODIFIER -> {
                                try {
                                    new Particulier(AccueilForm.this).config(TypeSociete.CLIENT, Crud.MODIFIER);
                                } catch (CollectionIllegalException ex) {
                                    throw new RuntimeException(ex);
                                }
                                dispose();
                            }
                            case SUPPRIMER -> {
                                try {
                                    new Particulier(AccueilForm.this).config(TypeSociete.CLIENT, Crud.SUPPRIMER);
                                } catch (CollectionIllegalException ex) {
                                    throw new RuntimeException(ex);
                                }
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
                                try {
                                    new Particulier(AccueilForm.this).config(TypeSociete.PROSPECT, Crud.MODIFIER);
                                } catch (CollectionIllegalException ex) {
                                    throw new RuntimeException(ex);
                                }
                                dispose();
                            }
                            case SUPPRIMER -> {
                                try {
                                    new Particulier(AccueilForm.this).config(TypeSociete.PROSPECT, Crud.SUPPRIMER);
                                } catch (CollectionIllegalException ex) {
                                    throw new RuntimeException(ex);
                                }
                                dispose();
                            }
                        }
                    }
                });
            }
        }
    }
}
**/

