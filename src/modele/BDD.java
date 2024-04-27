package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {
	//Idem que la classe PDO de PHP 
	private String serveur, bdd, user, mdp ; 
	private Connection maConnexion ;
	public BDD(String serveur, String bdd, String user, String mdp) {
		super();
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnexion = null; 
	} 
	public void chargerPilote () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException exp) {
			System.out.println("Erreur chargement du pilote JDBC.");
		}
	}
	public void seConnecter () {
		this.chargerPilote();
		String url ="jdbc:mysql://"+this.serveur+"/"+this.bdd;
		try {
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		}
		catch (SQLException exp) {
			System.out.println("Erreur : connexion impossible à "+url);
			exp.printStackTrace();
		}
	}
	public void seDeconnecter() {
		try {
			if (this.maConnexion !=null) {
				this.maConnexion.close();
			}
		}
		catch (SQLException exp) {
			System.out.println("Erreur de fermeture de la connexion"); 
		}
	}
	public Connection getMaConnexion () {
		return this.maConnexion;
	}
}







