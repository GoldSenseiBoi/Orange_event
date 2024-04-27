package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

public class PanelClients extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =  new JPanel(); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField();
	
	private JTable tableClients ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	private JTextField txtFiltre = new JTextField(); 
	
	private JLabel txtNbClients = new JLabel(); 
	
	public PanelClients () {
		super ("Gestion des clients"); 
		
		//construction du panel client 
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setBounds(40, 100,350, 300);
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel("Nom Client :")); 
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prénom Client :")); 
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse Client :")); 
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email Contact:")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//placement du panel filtre 
		this.panelFiltre.setBackground(Color.gray);
		this.panelFiltre.setBounds(420, 110, 360, 40);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.add(new JLabel("Filtrer par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer); 
		this.add(this.panelFiltre); 
		this.btFiltrer.addActionListener(this);
		
		//construction de la JTable 
		String entetes [] = {"Id Client", "Nom", "Prénom", "Adresse", "Email"}; 
		this.unTableau = new Tableau (this.obtenirDonnees(""), entetes);
		this.tableClients = new JTable(this.unTableau);
		
		this.uneScroll = new JScrollPane(this.tableClients); 
		this.uneScroll.setBounds(420, 160, 360, 240);
		this.uneScroll.setBackground(Color.gray);
		this.add(this.uneScroll);
		
		//installer le label txtNbClients 
		this.txtNbClients.setBounds(440, 440, 300, 20);
		this.txtNbClients.setText("Le nombre de clients est de :"+this.unTableau.getRowCount());
		this.add(this.txtNbClients);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//suppression d'un client sur double click 
		this.tableClients.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				 int numLigne, idclient ; 
				 if (e.getClickCount() >=2 ) {
					 numLigne= tableClients.getSelectedRow(); 
					 idclient=Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					 int reponse = JOptionPane.showConfirmDialog(null,  
							 "Voulez-vous supprimer le client ?", "Suppression Client",
							 JOptionPane.YES_NO_OPTION); 
					 if (reponse == 0) {
						 //suppression en BDD
						 //Controleur.deleteClient(idclient);
						 
						 //appel de la procédure stockee : deleteClient 
						 String nomP = "deleteClient"; 
						 String tab[] = {idclient+""};
						 Controleur.appelProcedure(nomP, tab);
						 
						 //actualiser l'affichage 
						 unTableau.setDonnees(obtenirDonnees(""));
						 //actualisation de la liste des clients dans le panel Produits 
						 PanelProduits.remplirCBXClients();
						 //actualiser le nombre de clients
						 txtNbClients.setText("Le nombre de clients est de :" + unTableau.getRowCount());
					 }
				 }else if (e.getClickCount() == 1) {
					 numLigne= tableClients.getSelectedRow(); 
					 txtNom.setText( unTableau.getValueAt(numLigne, 1).toString()); 
					 txtPrenom.setText( unTableau.getValueAt(numLigne, 2).toString()); 
					 txtAdresse.setText( unTableau.getValueAt(numLigne, 3).toString());
					 txtEmail.setText( unTableau.getValueAt(numLigne, 4).toString()); 
					 btEnregistrer.setText("Modifier");
				 }
			}
		});
		
	}
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Client> lesClients = Controleur.selectAllClients(filtre);
		Object [][] matrice = new Object [lesClients.size()][5];
		int i =0; 
		for (Client unClient : lesClients) {
			matrice[i][0]= unClient.getIdclient(); 
			matrice[i][1]= unClient.getNom(); 
			matrice[i][2]= unClient.getPrenom();
			matrice[i][3]= unClient.getAdresse(); 
			matrice[i][4]= unClient.getEmail(); 
			i++;
		}
		return matrice ; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtNom.setText("");
			 this.txtPrenom.setText("");
			 this.txtEmail.setText("");
			 this.txtAdresse.setText("");
			 this.btEnregistrer.setText("Enregistrer");
		 }
		 else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			 //on récupère les champs du formulaire dans des variables 
			 String nom = this.txtNom.getText();
			 String prenom = this.txtPrenom.getText();
			 String adresse = this.txtAdresse.getText();
			 String email = this.txtEmail.getText();
			 //on instancie la classe Client 
			 Client unClient = new Client(nom, prenom, adresse, email);
			 //on vérifie les données 
			 
			 //on insère dans la base de données 
			 //Controleur.insertClient(unClient);
			 
			 //appel de la procédure stockee : insertClient 
			 String nomP = "insertClient"; 
			 String tab[] = {nom, prenom, adresse, email};
			 Controleur.appelProcedure(nomP, tab);
			 
			 //on actualise l'affichage après insertion 
			 this.unTableau.setDonnees(this.obtenirDonnees(""));
			 
			 //on vide les champs 
			 this.txtNom.setText("");
			 this.txtPrenom.setText("");
			 this.txtEmail.setText("");
			 this.txtAdresse.setText("");
			 
			 //on affiche un message de confirmation 
			 JOptionPane.showMessageDialog(this, "insertion effectuée");
			 
			 //actualisation de la liste des clients dans le panel Produits 
			 PanelProduits.remplirCBXClients();
			 
			 //on actualise le nombre de clients 
			 this.txtNbClients.setText("Le nombre de clients est de :"+this.unTableau.getRowCount());
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText() ; 
			 //actualiser la matrice des données 
			 this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		 }
		 else if(e.getSource()==this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			 String nom = this.txtNom.getText();
			 String prenom = this.txtPrenom.getText();
			 String adresse = this.txtAdresse.getText();
			 String email = this.txtEmail.getText();
			 
			 int numLigne = this.tableClients.getSelectedRow(); 
			 int idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString()); 
			 
			 //instanciation d'un client avec idclient 
			 Client unClient = new Client (idclient, nom, prenom, adresse, email); 
			 
			 //modification des données du client dans la BDD 
			 Controleur.updateClient(unClient);
			 
			 //actualisation des données clients dans l'affichage 
			 this.unTableau.setDonnees(this.obtenirDonnees(""));
			 
			 //actualisation de la liste des clients dans le panel Produits 
			 PanelProduits.remplirCBXClients();
			 
			 //on vide les champs et on remet Enregistrer 
			 this.txtNom.setText("");
			 this.txtPrenom.setText("");
			 this.txtEmail.setText("");
			 this.txtAdresse.setText("");
			 this.btEnregistrer.setText("Enregistrer");
			 JOptionPane.showMessageDialog(this, "Modification réussie du client.");
		 }
	}
}








