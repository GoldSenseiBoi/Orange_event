package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.User;

public class PanelProfil extends PanelPrincipal implements ActionListener
{
	private User unUser ; 
	private JTextArea txtInfos = new JTextArea(); 
	private JButton btModifier = new JButton("Modifier"); 
	
	private JPanel panelForm = new JPanel (); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JTextField txtRole = new JTextField();
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	 
	
	public PanelProfil (User unUser) {
		super ("Gestion des Infos du Profil");
		this.unUser = unUser; 
		
		this.txtInfos.setText(
				"______ Les Informations de votre Profil ______"
				+"\n\n Votre nom    : " + unUser.getNom()
				+"\n\n Votre Prénom : " + unUser.getPrenom()
				+"\n\n Votre Email  : " + unUser.getEmail()
				+"\n\n Votre Role   : " + unUser.getRole()
				);
		this.txtInfos.setBounds(50, 120, 300, 200);
		this.txtInfos.setBackground(Color.gray);
		this.add(this.txtInfos);
		
		this.btModifier.setBounds(100, 360, 100, 30);
		this.add(this.btModifier);
		//installation du formulaire dans le panel 
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setBounds(400, 120,350, 300);
		this.panelForm.setLayout(new GridLayout(6,2));
		this.panelForm.add(new JLabel("Nom user :")); 
		this.panelForm.add(this.txtNom);
		
		this.panelForm.add(new JLabel("Prénom user :")); 
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Email :")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP :")); 
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(new JLabel("Role :")); 
		this.panelForm.add(this.txtRole);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		this.panelForm.setVisible(false);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btModifier.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btModifier) {
			 this.txtNom.setText(this.unUser.getNom());
			 this.txtPrenom.setText(this.unUser.getPrenom());
			 this.txtEmail.setText(this.unUser.getEmail());
			 this.txtMdp.setText(this.unUser.getMdp());
			 this.txtRole.setText(this.unUser.getRole());
			 this.panelForm.setVisible(true);
			 
		 }else if (e.getSource()== this.btAnnuler) {
			 this.panelForm.setVisible(false);
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String nom = this.txtNom.getText(); 
			 String prenom = this.txtPrenom.getText(); 
			 String email = this.txtEmail.getText(); 
			 String mdp = new String (this.txtMdp.getPassword()); 
			 String role = this.txtRole.getText(); 
			 
			 //on realise les modifs dans unUser 
			 this.unUser.setNom(nom);
			 this.unUser.setPrenom(prenom); 
			 this.unUser.setEmail(email);
			 this.unUser.setMdp(mdp);
			 this.unUser.setRole(role);
			 //controle des données 
			 
			 //modification dans la BDD 
			 Controleur.updateUser (this.unUser); 
			 
			 //modification dans l'affichage du TextArea 
			 this.txtInfos.setText(
						"______ Les Informations de votre Profil ______"
						+"\n\n Votre nom    : " + unUser.getNom()
						+"\n\n Votre Prénom : " + unUser.getPrenom()
						+"\n\n Votre Email  : " + unUser.getEmail()
						+"\n\n Votre Role   : " + unUser.getRole()
						);
			 //on ferme le formulaire 
			 this.panelForm.setVisible(false);
			 JOptionPane.showMessageDialog(this, "Modification effectuée");
		 }
		
	}
}















