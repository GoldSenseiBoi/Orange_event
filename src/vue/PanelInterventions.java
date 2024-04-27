package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Produit;
import controleur.Technicien;

public class PanelInterventions extends PanelPrincipal{

	private JPanel panelForm =  new JPanel(); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	
	private JTextField txtDescription = new JTextField(); 
	private JTextField txtPrixInter = new JTextField();
	private JTextField txtDateInter = new JTextField();
	private JComboBox<String> txtIdProduit = new JComboBox<String>(); 
	private static JComboBox<String> txtIdTechnicien = new JComboBox<String>();
	
	public PanelInterventions () {
		super ("Gestion des interventions");
		//construction du panel Intervention 
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setBounds(40, 100,350, 300);
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.add(new JLabel("Description :")); 
		this.panelForm.add(this.txtDescription);
		this.panelForm.add(new JLabel("Prix Inter :")); 
		this.panelForm.add(this.txtPrixInter);
		this.panelForm.add(new JLabel("Date Inter :")); 
		this.panelForm.add(this.txtDateInter);
		this.panelForm.add(new JLabel("Produit:")); 
		this.panelForm.add(this.txtIdProduit);
		this.panelForm.add(new JLabel("Technicien :")); 
		this.panelForm.add(txtIdTechnicien);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		
		//remplir les CBX Produit et Technicien
		this.remplirCBX (); 
	}
	public void remplirCBX() {
		this.txtIdProduit.removeAllItems();
		ArrayList<Produit> lesProduits = Controleur.selectAllProduits(); 
		for (Produit unProduit : lesProduits) {
			this.txtIdProduit.addItem(unProduit.getIdproduit() +"-" +unProduit.getDesignation());
		}
		this.txtIdTechnicien.removeAllItems();
		ArrayList<Technicien> lesTechniciens = Controleur.selectAllTechniciens(); 
		for (Technicien unTechnicien : lesTechniciens) {
			this.txtIdTechnicien.addItem(unTechnicien.getIdtechnicien() +"-" 
										+unTechnicien.getNom());
		}
	}
}
