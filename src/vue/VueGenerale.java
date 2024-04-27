package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.OrangeEvent;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener
{
	private JButton btProfil = new JButton("Profil"); 
	private JButton btClients = new JButton("Clients"); 
	private JButton btProduits = new JButton("Produits"); 
	private JButton btTechniciens = new JButton("Techniciens");
	private JButton btInterventions = new JButton("Interventions");
	private JButton btQuitter = new JButton("Quitter"); 
	private JPanel panelMenu = new JPanel (); 
	
	//instanciation des panels 
	private PanelProfil unPanelProfil ; 
	private PanelClients unPanelClients= new PanelClients(); 
	private PanelProduits unPanelProduits= new PanelProduits(); 
	private PanelTechniciens unPanelTechniciens = new PanelTechniciens(); 
	private PanelInterventions unPanelInterventions= new PanelInterventions();
	
	
	public VueGenerale(User unUser) {
		
		//envoie de unUser au profil 
		this.unPanelProfil = new PanelProfil(unUser); 
				
		this.setTitle("Admin BDD Orange Event");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 900, 600);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.gray);
		
		//contruction du panelMenu 
		this.panelMenu.setBounds(20, 10, 800, 30);
		this.panelMenu.setBackground(Color.gray);
		this.panelMenu.setLayout(new GridLayout(1,6));
		this.panelMenu.add( this.btProfil);
		this.panelMenu.add(this.btClients); 
		this.panelMenu.add(this.btProduits); 
		this.panelMenu.add(this.btTechniciens); 
		this.panelMenu.add(this.btInterventions); 
		this.panelMenu.add(this.btQuitter); 
		this.add(this.panelMenu);
		
		//insertion des Panels dans la fenetre 
		this.add(this.unPanelProfil); 
		this.add(this.unPanelClients);
		this.add(this.unPanelProduits);
		this.add(this.unPanelTechniciens);
		this.add(this.unPanelInterventions);
		
		//rendre les boutons ecoutables 
		this.btProfil.addActionListener(this);
		this.btClients.addActionListener(this);
		this.btProduits.addActionListener(this);
		this.btTechniciens.addActionListener(this);
		this.btInterventions.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		this.setVisible(true);
	}
	public void afficherPanel (int choix) {
		this.unPanelProfil.setVisible(false);
		this.unPanelClients.setVisible(false);
		this.unPanelProduits.setVisible(false);
		this.unPanelTechniciens.setVisible(false);
		this.unPanelInterventions.setVisible(false);
		switch (choix) {
		case 1 : this.unPanelProfil.setVisible(true); break;
		case 2 : this.unPanelClients.setVisible(true); break;
		case 3 : this.unPanelProduits.setVisible(true); break;
		case 4 : this.unPanelTechniciens.setVisible(true); break;
		case 5 : this.unPanelInterventions.setVisible(true); break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btProfil) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btClients) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btProduits) {
			 this.afficherPanel(3);
		 }
		 else if (e.getSource() == this.btTechniciens) {
			 this.afficherPanel(4);
		 }
		 else if (e.getSource() == this.btInterventions) {
			 this.afficherPanel(5);
		 }
		 else if (e.getSource() == this.btQuitter) {
			OrangeEvent.rendreVisibleVueGenerale(false, null);
			OrangeEvent.rendreVisibleVueConnexion(true);
		 }
	}
}






