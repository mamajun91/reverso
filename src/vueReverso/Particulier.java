package vueReverso;
import Enum.*;
import ReversoException.CollectionIllegalException;
import ReversoException.Dbexception;
import logiqueReverso.*;
import DAO.Client_DAO;
import DAO.ProspectDAO;
import DAO.ConnexionDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class Particulier extends JFrame {
    Prospect prospect;
    Client client;


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
    Client_DAO clientDao = new Client_DAO();
    ProspectDAO prospectDAO = new ProspectDAO();
    ConnexionDAO con = new ConnexionDAO();


    public Particulier(AccueilForm accueilForm) {
        this.accueilForm = accueilForm;
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
        Quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    /**
     * Configure l'interface en fonction du type de société et de l'action à effectuer, puis valide l'action.
     *
     * @param typeSociete Le type de société (client ou prospect).
     * @param crud        L'action à effectuer (ajouter, modifier ou supprimer).
     * @throws CollectionIllegalException Si une opération illégale est tentée sur une collection.
     * @throws SQLException               si une erreur SQL se produit lors de l'accès à la base de données.
     */
    public void config(TypeSociete typeSociete, Crud crud) throws CollectionIllegalException, SQLException {

        switch (typeSociete) {
            case CLIENT -> {
                switch (crud) {
                    case AJOUTER -> {
                        configAjouter(Crud.AJOUTER, TypeSociete.CLIENT);
                        validerAjou(TypeSociete.CLIENT);
                    }
                    case MODIFIER -> {
                        configAjouter(Crud.MODIFIER, TypeSociete.CLIENT); // Configure l'interface pour la modification
                        try {
                           transfertDonnees(typeSociete); // Remplit les champs avec les données du client sélectionné
                        } catch (SQLException ex) {
                            ex.printStackTrace(); // Gérer les exceptions de manière appropriée
                        }
                        validerMod(TypeSociete.CLIENT); // Valide l'action de modification
                    }

                    case SUPPRIMER -> {
                        configAjouter(Crud.SUPPRIMER, TypeSociete.CLIENT);
                        transfertDonnees(TypeSociete.CLIENT);
                        //valider(TypeSociete.CLIENT, Crud.SUPPRIMER);
                    }
                }
            }
            case PROSPECT -> {
                switch (crud) {
                    case AJOUTER -> {
                        configAjouter(Crud.AJOUTER, TypeSociete.PROSPECT);
                        validerAjou(TypeSociete.PROSPECT);
                    }
                    case MODIFIER -> {
                        configAjouter(Crud.MODIFIER, TypeSociete.PROSPECT);
                        transfertDonnees(TypeSociete.PROSPECT);
                        validerMod(TypeSociete.PROSPECT);
                    }
                    case SUPPRIMER -> {
                        configAjouter(Crud.SUPPRIMER, TypeSociete.PROSPECT);
                        transfertDonnees(TypeSociete.PROSPECT);
                        //valider(TypeSociete.PROSPECT, Crud.SUPPRIMER);
                    }
                }
            }
        }
    }

    /**
     * Active ou désactive les champs de saisie pour les données de la société en fonction du paramètre spécifié.
     *
     * @param enable true pour activer les champs, false pour les désactiver.
     */
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

    /**
     * Active ou désactive les champs de saisie pour les données du client en fonction de l'opération CRUD spécifiée.
     *
     * @param crud L'opération CRUD à effectuer (AJOUTER, SUPPRIMER).
     */
    public void enableClientFields(Crud crud) {
        switch (crud) {
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

    /**
     * Active ou désactive les champs de saisie pour les données du prospect en fonction de l'opération CRUD spécifiée.
     *
     * @param crud L'opération CRUD à effectuer (AJOUTER, SUPPRIMER).
     */
    public void enableProspectFields(Crud crud) {
        switch (crud) {
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

    /**
     * Configure l'interface utilisateur pour l'ajout, la modification ou la suppression d'une société selon le type spécifié.
     *
     * @param crud        L'action CRUD à effectuer (AJOUTER, MODIFIER ou SUPPRIMER).
     * @param typeSociete Le type de société (CLIENT ou PROSPECT).
     */
    public void configAjouter(Crud crud, TypeSociete typeSociete) {
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
                        iden.setEditable(true);
                        enableFields(true);
                        enableClientFields(Crud.AJOUTER);
                    }
                    case MODIFIER -> {
                        iden.setEditable(false);
                        enableFields(true);
                        enableClientFields(Crud.AJOUTER);
                    }
                    case SUPPRIMER -> {
                        iden.setEditable(false);
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
                        iden.setEditable(true);
                        enableFields(true);
                        enableProspectFields(Crud.AJOUTER);
                    }
                    case MODIFIER -> {
                        iden.setEditable(false);
                        enableFields(true);
                        enableProspectFields(Crud.SUPPRIMER);
                    }
                    case SUPPRIMER -> {
                        iden.setEditable(false);
                        enableFields(false);
                        enableProspectFields(Crud.SUPPRIMER);
                    }
                }
            }
        }
    }

    /**
     * Remplit les champs de l'interface utilisateur avec les données de la société fournie.
     *
     * @param societe La société dont les données doivent être affichées dans les champs de l'interface utilisateur.
     */
    public void donneesSociete(Societe societe) {

        RaisonSociale.setText(societe.getRaisonSociale());
        NumDeRue.setText(societe.getNumRue());
        NomDeRue.setText(societe.getNomRue());
        CodePostal.setText(societe.getCodePostal());
        Ville.setText(societe.getVille());
        Telephone.setText(societe.getTelephone());
        AdresseMail.setText(societe.getAdresseMail());
        Commentaire.setText(societe.getCommentaire());
    }

    /**
     * Transfère les données de la société sélectionnée dans les champs correspondants.
     *
     * @param typeSociete Le type de société dont les données doivent être transférées (CLIENT ou PROSPECT).
     * @throws SQLException Une exception SQL en cas d'erreur lors de l'accès à la base de données.
     */
    public void transfertDonnees(TypeSociete typeSociete) throws SQLException {

        switch (typeSociete) {
            case CLIENT -> {
                String selectedClientName = (String) accueilForm.getComboBoxClient().getSelectedItem();
                int selectedIndex = accueilForm.getComboBoxClient().getSelectedIndex();
                if (selectedIndex >= 0) {
                    for (Client client : clientDao.findAll(con)) {
                        if (client.getRaisonSociale().equals(selectedClientName)) {
                            donneesSociete(client);
                            chiffreAffaire.setText(String.valueOf(client.getChiffreAffaire()));
                            nombreEmploye.setText(String.valueOf(client.getNombreEmployes()));
                            break;
                        }
                    }
                }
            }

            case PROSPECT -> {
                String selectedClientName = (String) accueilForm.getComboBoxProspect().getSelectedItem();
                int selectedIndex = accueilForm.getComboBoxProspect().getSelectedIndex();
                if (selectedIndex >= 0) {
                    for (Prospect prospect : prospectDAO.findAll(con)) {
                        if (prospect.getRaisonSociale().equals(selectedClientName)) {
                            donneesSociete(prospect);
                            dateProspect.setText(prospect.getDateProspect());
                            prospectInteret2.setText(prospect.getProspectInteresse());
                        }
                    }
                }
            }
        }
    }

    /**
     * Vérifie si tous les champs requis sont remplis en fonction du type de société spécifié.
     *
     * @param typeSociete Le type de société concerné (CLIENT ou PROSPECT).
     * @return true si tous les champs sont remplis, false sinon.
     */
    public Boolean verif(TypeSociete typeSociete) {
        if (
                 RaisonSociale.getText().isEmpty() || RaisonSociale.getText() == null
                || NumDeRue.getText().isEmpty() || NumDeRue.getText() == null
                || NomDeRue.getText().isEmpty() || NomDeRue.getText() == null
                || CodePostal.getText().isEmpty() || CodePostal.getText() == null
                || Ville.getText().isEmpty() || Ville.getText() == null
                || Telephone.getText().isEmpty() || Telephone.getText() == null
                || AdresseMail.getText().isEmpty() || AdresseMail.getText() == null
                || Commentaire.getText().isEmpty() || Commentaire.getText() == null)
            switch (typeSociete) {
                case CLIENT -> {
                    if (chiffreAffaire.getText().isEmpty() || chiffreAffaire.getText() == null
                            || nombreEmploye.getText().isEmpty() || nombreEmploye.getText() == null) {
                        JOptionPane.showConfirmDialog(null, "Tous les champs doivent être renseignés");
                    }
                }
                case PROSPECT -> {
                    if (dateProspect.getText().isEmpty() || dateProspect.getText() == null
                            || prospectInteret2.getText().isEmpty() || prospectInteret2.getText() == null) {
                        JOptionPane.showConfirmDialog(null, "Tous les champs doivent être renseignés");
                    }
                }
            }
        {
        }
        return false;
    }

    /**
     * Méthode AjouterClientProspect permettant d'ajouter un nouveau client ou prospect en fonction du type spécifié.
     *
     * @param typeSociete Le type de société concerné (CLIENT ou PROSPECT).
     * @throws IllegalArgumentException Lancée si le format des données est incorrect.
     * @throws SQLException             Lancée en cas d'erreur lors de l'accès à la base de données.
     */
    public void AjouterClientProspect(TypeSociete typeSociete) throws IllegalArgumentException, SQLException {
        if (!verif(typeSociete)) {
            return; // Si la vérification échoue, quitter la méthode
        }

        try {
            new SocieteForm().remplirSociete(typeSociete);
        } catch (CollectionIllegalException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problème au niveau des Collections", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (typeSociete) {
            case CLIENT -> {
                if (client.getChiffreAffaire() != Double.parseDouble(chiffreAffaire.getText())) {
                    JOptionPane.showConfirmDialog(null, "le format du CA n'est pas correct");
                }

                // Récupérer les valeurs des champs Swing pour créer un nouveau client
                String raison_sociale = RaisonSociale.getText();
                String num_rue = NumDeRue.getText();
                String nom_rue = NomDeRue.getText();
                String code_postal = CodePostal.getText();
                String ville = Ville.getText();
                String telephone = Telephone.getText();
                String adresse_mail = AdresseMail.getText();
                String commentaire = Commentaire.getText();
                double chiffre_affaire = Double.parseDouble(chiffreAffaire.getText());
                int nombre_employe = Integer.parseInt(nombreEmploye.getText());

                // Créer un nouvel objet Client avec ces valeurs
                Client creer = new Client(
                        // Assurez-vous que iden.getText() renvoie bien un identifiant valide
                        raison_sociale,
                        num_rue,
                        nom_rue,
                        code_postal,
                        ville,
                        telephone,
                        adresse_mail,
                        commentaire,
                        chiffre_affaire,
                        nombre_employe
                );

                // Appeler la méthode create du DAO de client en passant le nouvel objet Client
                try {
                    clientDao.create(con, creer);
                } catch (SQLException e) {
                    // Gérer les exceptions ici
                }
            }
            case PROSPECT -> {

                try {
                    // Récupérer les valeurs des champs Swing pour créer un nouveau prospect

                    String raison_sociale = RaisonSociale.getText();
                    String num_rue = NumDeRue.getText();
                    String nom_rue = NomDeRue.getText();
                    String code_postal = CodePostal.getText();
                    String ville = Ville.getText();
                    String telephone = Telephone.getText();
                    String adresse_mail = AdresseMail.getText();
                    String commentaire = Commentaire.getText();
                    String date_prospect = dateProspect.getText();
                    String prospect_interess = prospectInteret2.getText();

                    // Créer un nouvel objet Prospect avec ces valeurs
                    Prospect newProspect = new Prospect(

                            raison_sociale,
                            num_rue,
                            nom_rue,
                            code_postal,
                            ville,
                            telephone,
                            adresse_mail,
                            commentaire,
                            date_prospect,
                            prospect_interess
                    );

                    // Appeler la méthode create du DAO de prospect en passant le nouvel objet Prospect
                    prospectDAO.create(con, newProspect);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showConfirmDialog(null, "le format du nombre entré n'est pas correct");
                } catch (SQLException e) {
                    // Gérer les exceptions ici
                }
            }
        }
    }

    /**
     * Méthode ModifierClientProspect permettant de modifier un client ou prospect en fonction du type spécifié.
     *
     * @param typeSociete Le type de société concerné (CLIENT ou PROSPECT).
     * @throws IllegalArgumentException lancée si le format des données est incorrect.
     * @throws SQLException lancée en cas d'erreur lors de l'accès à la base de données.
     */
    public void ModifierClientProspect(TypeSociete typeSociete) throws IllegalArgumentException, SQLException {
        if (!verif(typeSociete)) {
            return; // Si la vérification échoue, quitter la méthode
        }
        switch (typeSociete) {
            case CLIENT -> {
                if (client.getChiffreAffaire() != Double.parseDouble(chiffreAffaire.getText())) {
                    JOptionPane.showConfirmDialog(null, "le format du CA n'est pas correct");
                }
                // Récupérer les valeurs des champs Swing pour créer un nouveau client
                String selectedClientName = (String) accueilForm.getComboBoxClient().getSelectedItem();
                if (selectedClientName != null && !selectedClientName.isEmpty()) {
                    // Récupérer l'index du client sélectionné
                    int selectedIndex = accueilForm.getComboBoxClient().getSelectedIndex();

                    if (selectedIndex >= 0) {
                        // Récupérer le client sélectionné à partir de l'index
                        Client selectedClient = Client_DAO.find(con, selectedIndex);

                        if (selectedClient.getRaisonSociale().equals(selectedClientName)) {

                            // Récupérer les valeurs des champs Swing pour mettre à jour le client

                            String raison_sociale = RaisonSociale.getText();
                            String num_rue = NumDeRue.getText();
                            String nom_rue = NomDeRue.getText();
                            String code_postal = CodePostal.getText();
                            String ville = Ville.getText();
                            String telephone = Telephone.getText();
                            String adresse_mail = AdresseMail.getText();
                            String commentaire = Commentaire.getText();
                            double chiffre_affaire = Double.parseDouble(chiffreAffaire.getText());
                            int nombre_employe = Integer.parseInt(nombreEmploye.getText());

                            selectedClient.setRaisonSociale(raison_sociale);
                            selectedClient.setNumRue(num_rue);
                            selectedClient.setNomRue(nom_rue);
                            selectedClient.setCodePostal(code_postal);
                            selectedClient.setVille(ville);
                            selectedClient.setTelephone(telephone);
                            selectedClient.setAdresseMail(adresse_mail);
                            selectedClient.setCommentaire(commentaire);
                            selectedClient.setChiffreAffaire(chiffre_affaire);
                            selectedClient.setNombreEmployes(nombre_employe);

                            // Appeler la méthode update du DAO de client pour mettre à jour le client dans la base de données
                            try {
                                clientDao.update(con, selectedClient);
                            } catch (SQLException e) {
                                // Gérer les exceptions liées à la mise à jour du client
                                JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du client", "Erreur", JOptionPane.ERROR_MESSAGE);
                            } catch (Dbexception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            case PROSPECT -> {
                // Récupérer les valeurs des champs Swing pour créer un nouveau prospect
                String selectedProspectName = (String) accueilForm.getComboBoxProspect().getSelectedItem();
                int selectedIndex = accueilForm.getComboBoxProspect().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Prospect selectedProspect = prospectDAO.find(con, selectedIndex);
                    // Récupérer le prospect à partir de l'index
                    if (prospect != null && prospect.getRaisonSociale().equals(selectedProspectName)) {
                        // Récupérer les valeurs des champs Swing pour mettre à jour le prospect
                        String raison_sociale = RaisonSociale.getText();
                        String num_rue = NumDeRue.getText();
                        String nom_rue = NomDeRue.getText();
                        String code_postal = CodePostal.getText();
                        String ville = Ville.getText();
                        String telephone = Telephone.getText();
                        String adresse_mail = AdresseMail.getText();
                        String commentaire = Commentaire.getText();
                        String date_prospect = dateProspect.getText();
                        String prospect_interess = prospectInteret2.getText();
                        // Mettre à jour les informations du prospect avec les nouvelles valeurs
                        selectedProspect.setRaisonSociale(raison_sociale);
                        selectedProspect.setNumRue(num_rue);
                        selectedProspect.setNomRue(nom_rue);
                        selectedProspect.setCodePostal(code_postal);
                        selectedProspect.setVille(ville);
                        selectedProspect.setTelephone(telephone);
                        selectedProspect.setAdresseMail(adresse_mail);
                        selectedProspect.setCommentaire(commentaire);
                        selectedProspect.setDateProspect(date_prospect);
                        selectedProspect.setProspectInteresse(prospect_interess);
                        // Appeler la méthode update du DAO de prospect pour mettre à jour le prospect dans la base de données
                        try {
                            prospectDAO.update(con, selectedProspect);
                        } catch (SQLException e) {
                            // Gérer les exceptions liées à la mise à jour du prospect
                            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du prospect", "Erreur", JOptionPane.ERROR_MESSAGE);
                        } catch (Dbexception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }

    /**
     * Méthode SupprimerClientProspect permettant de supprimer un client ou prospect en fonction du type spécifié.
     *
     * @param typeSociete Le type de société concerné (CLIENT ou PROSPECT).
     * @throws SQLException Lancée en cas d'erreur lors de l'accès à la base de données.
     */
    public void SupprimerClientProspect(TypeSociete typeSociete) throws SQLException {
        switch (typeSociete) {
            case CLIENT -> {
                // Récupérer les valeurs des champs Swing pour supprimer le client
                String selectedClientName = (String) accueilForm.getComboBoxClient().getSelectedItem();
                int selectedIndex = accueilForm.getComboBoxClient().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Client selectedClient = Client_DAO.find(con, selectedIndex);
                    // Récupérer le client à partir de l'index
                    if (client != null && client.getRaisonSociale().equals(selectedClientName)) {
                        // Appeler la méthode delete du DAO de client pour supprimer le client de la base de données
                        try {
                            clientDao.delete(con, selectedClient);
                        } catch (SQLException e) {
                            // Gérer les exceptions liées à la suppression du client
                            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du client", "Erreur", JOptionPane.ERROR_MESSAGE);
                        } catch (Dbexception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            case PROSPECT -> {
                // Récupérer les valeurs des champs Swing pour supprimer le prospect
                String selectedProspectName = (String) accueilForm.getComboBoxProspect().getSelectedItem();
                int selectedIndex = accueilForm.getComboBoxProspect().getSelectedIndex();
                if (selectedIndex >= 0) {
                    Prospect selectedProspect = prospectDAO.find(con, selectedIndex);
                    // Récupérer le prospect à partir de l'index
                    if (prospect != null && prospect.getRaisonSociale().equals(selectedProspectName)) {
                        // Appeler la méthode delete du DAO de prospect pour supprimer le prospect de la base de données
                        try {
                            prospectDAO.delete(con, selectedProspect);
                        } catch (SQLException e) {
                            // Gérer les exceptions liées à la suppression du prospect
                            JOptionPane.showMessageDialog(null, "Erreur lors de la suppression du prospect", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    /**
     * Valide l'action spécifiée sur la société donnée.
     *
     * @param typeSociete Le type de société sur lequel l'action est effectuée (CLIENT ou PROSPECT).
     * @throws CollectionIllegalException Si une opération illégale est tentée sur une collection.
     * @throws SQLException si une erreur SQL se produit lors de l'accès à la base de données.
     */
    public void validerMod(TypeSociete typeSociete) throws CollectionIllegalException, SQLException {
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SocieteForm().remplirSociete(typeSociete);
                    if (typeSociete == TypeSociete.CLIENT) {
                        ModifierClientProspect(TypeSociete.CLIENT); // Modifie le client avec les valeurs mises à jour
                    } else {
                        ModifierClientProspect(TypeSociete.PROSPECT); // Modifie le prospect avec les valeurs mises à jour
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(); // Gérer les exceptions de manière appropriée
                } catch (CollectionIllegalException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void validerAjou(TypeSociete typeSociete) throws CollectionIllegalException, SQLException {
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeSociete == TypeSociete.CLIENT) {
                    try {
                        AjouterClientProspect(TypeSociete.CLIENT); // Modifie le client avec les valeurs mises à jour
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        AjouterClientProspect(TypeSociete.PROSPECT); // Modifie le prospect avec les valeurs mises à jour
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}