package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Produit;
import controleur.Tableau;

public class PanelProduits extends PanelPrincipal implements ActionListener
{
	private JPanel panelForm =  new JPanel(); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	
	private JTextField txtDesignation = new JTextField(); 
	private JTextField txtPrixAchat = new JTextField();
	private JTextField txtDateAchat = new JTextField();
	private JComboBox<String> txtCategorie = new JComboBox<String>(); 
	private static JComboBox<String> txtIdClient = new JComboBox<String>();
	
	private JTable tableProduits ; 
	private JScrollPane uneScroll ; 
	
	private JLabel txtNbProduits = new JLabel();
	
	private Tableau unTableau ; 
	
	public PanelProduits () {
		super ("Gestion des Produits"); 
		//remplir le combobox de la catégorie 
		this.txtCategorie.addItem("Téléphone");
		this.txtCategorie.addItem("Informatique");
		this.txtCategorie.addItem("Télévision");
		//construction du panel Produit 
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setBounds(40, 100,350, 300);
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.add(new JLabel("Designation :")); 
		this.panelForm.add(this.txtDesignation);
		this.panelForm.add(new JLabel("Prix Achat :")); 
		this.panelForm.add(this.txtPrixAchat);
		this.panelForm.add(new JLabel("Date Achat :")); 
		this.panelForm.add(this.txtDateAchat);
		this.panelForm.add(new JLabel("Catégorie:")); 
		this.panelForm.add(this.txtCategorie);
		this.panelForm.add(new JLabel("le Client :")); 
		this.panelForm.add(txtIdClient);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//remplir le Combox des id clients
		remplirCBXClients (); 
		
		
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//construction de la JTable 
		String entetes [] = {"Id Produit", "Désignation", "Prix", "Date Achat", "Catégorie", "Client"}; 
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.tableProduits = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(this.tableProduits); 
		this.uneScroll.setBounds(420, 160, 360, 240);
		this.uneScroll.setBackground(Color.gray);
		this.add(this.uneScroll);
		
		//installer le label txtNbProduits 
		this.txtNbProduits.setBounds(440, 440, 300, 20);
		this.txtNbProduits.setText("Le nombre de Produits est de :"+this.unTableau.getRowCount());
		this.add(this.txtNbProduits);
	}
	public Object [][] obtenirDonnees (){
		ArrayList<Produit> lesProduits = Controleur.selectAllProduits();
		Object [][] matrice = new Object [lesProduits.size()][6];
		int i =0; 
		for (Produit unProduit : lesProduits) {
			matrice[i][0]= unProduit.getIdproduit();  
			matrice[i][1]= unProduit.getDesignation(); 
			matrice[i][2]= unProduit.getPrixAchat();
			matrice[i][3]= unProduit.getDateAchat(); 
			matrice[i][4]= unProduit.getCategorie();
			matrice[i][5]= unProduit.getIdclient();
			i++;
		}
		return matrice ; 
	}
	
	public static void remplirCBXClients ()
	{
		//vider le combox des clients 
		txtIdClient.removeAllItems();
		//remplir avec les clients de la BDD : id-nom 
		ArrayList<Client> lesClients = Controleur.selectAllClients(""); 
		for (Client unClient : lesClients) {
			txtIdClient.addItem(unClient.getIdclient() +"-" + unClient.getNom());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			 this.txtDesignation.setText("");
			 this.txtPrixAchat.setText("");
			 this.txtDateAchat.setText("");
			 
		 }
		else if (e.getSource() == this.btEnregistrer) {
			 //on récupère les champs du formulaire dans des variables 
			 String designation = this.txtDesignation.getText();
			 float prixAchat = Float.parseFloat(this.txtPrixAchat.getText());
			 String dateAchat = this.txtDateAchat.getText();
			 String categorie = this.txtCategorie.getSelectedItem().toString(); 
			 //parser le item selectionné dans le CBX idClient 
			 String chaine = txtIdClient.getSelectedItem().toString(); 
			 String tab[] = chaine.split("-"); 
			 int idclient = Integer.parseInt(tab[0]); 
			 
			 //on instancie la classe Produit 
			 Produit unProduit = new Produit(designation, prixAchat, dateAchat, categorie, idclient);
			 //on vérifie les données 
			 
			 //on insère dans la base de données 
			 //Controleur.insertProduit(unProduit);
			 
			 //appel de la procedure stockee : insertProduit 
			 String nomP = "insertProduit"; 
			 String tabSql[] = {designation, prixAchat+"", dateAchat, categorie, idclient+""};
			 Controleur.appelProcedure(nomP, tabSql);
			 
			 
			 //on actualise l'affichage 
			//actualisation des données clients dans l'affichage 
			 this.unTableau.setDonnees(this.obtenirDonnees());
			 
			 
			 //on vide les champs 
			 this.txtDesignation.setText("");
			 this.txtPrixAchat.setText("");
			 this.txtDateAchat.setText("");
			 
			 //on affiche un message de confirmation 
			 JOptionPane.showMessageDialog(this, "insertion effectuée");
			 
			 //on actualise le nombre de produits 
			 this.txtNbProduits.setText("Le nombre de Produits est de :"+this.unTableau.getRowCount());
		 }
	}
}








