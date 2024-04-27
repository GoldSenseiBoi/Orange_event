package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Produit;
import controleur.Technicien;
import controleur.User;

public class Modele {
	//instanciation de la connexion Mysql 
	private static BDD uneBdd = new BDD("localhost:8889", "orange_efrei", "root", "root");
	
	public static User verifConnexion (String email, String mdp) {
		User unUser = null; 
		String requete ="select * from user where email = ? and mdp = ? ;";
		try {
			uneBdd.seConnecter();
			PreparedStatement unStat = uneBdd.getMaConnexion().prepareStatement(requete) ;
			//affecter des données aux parametres 
			unStat.setString(1, email);
			unStat.setString(2, mdp);
			
			ResultSet unRes =  unStat.executeQuery(); 
			
			if(unRes.next()) {
				//extraction des données de la BDD 
				unUser = new User (
						unRes.getInt("iduser"), unRes.getString("nom"), 
						unRes.getString("prenom"), unRes.getString("email"), 
						unRes.getString("mdp"), unRes.getString("role")
						); 
			}
			unStat.close();
			uneBdd.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur execution requete :" + requete);
			exp.printStackTrace();
		}
		return unUser;
	}

	public static void updateUser(User unUser) {
		 String requete ="update user set nom='"
				 +unUser.getNom()+"',prenom ='" + unUser.getPrenom() 
				 +"',email = '"+unUser.getEmail() + "', mdp ='"
				 +unUser.getMdp()+"', role='"+unUser.getRole()
				 +"' where iduser ="+unUser.getIduser();
		 try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}
	
	/**************** Gestion des clients ***************/
	public static void insertClient (Client unClient) {
		String requete ="insert into client values (null, '"
			+ unClient.getNom()+"','" +unClient.getPrenom()
			+ "','" +unClient.getAdresse() + "','" +unClient.getEmail()
			+ "');"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}
	public static ArrayList<Client> selectAllClients (String filtre){
		ArrayList<Client> lesClients = new ArrayList<Client>(); 
		String requete ; 
		if (filtre.equals("")) {
			requete="select * from client ; ";
		}else {
			requete="select * from client where nom like '%"+filtre+"%' or "
					+" prenom like '%"+filtre+"%' or adresse like '%"+filtre+"%' ;";
		}
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Client unClient = new Client (
						desRes.getInt("idclient"), desRes.getString("nom"), 
						desRes.getString("prenom"),desRes.getString("adresse"),
						desRes.getString("email")
						);
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
		return lesClients ;
	}
	public static void deleteClient (int idclient) {
		String requete ="delete from client where idclient = "+ idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}
	public static void updateClient (Client unClient) {
		String requete ="update client set nom='"+unClient.getNom()
						+"' , prenom ='"+unClient.getPrenom()
						+"', adresse ='"+unClient.getAdresse()
						+"', email='"+unClient.getEmail()
						+"'   where idclient = "+unClient.getIdclient()+";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}

	/**************** Gestion des Produits ***************/
	public static void insertProduit (Produit unProduit) {
		String requete ="insert into produit values (null, '"
			+ unProduit.getDesignation()+"','" +unProduit.getPrixAchat()
			+ "','" +unProduit.getDateAchat() + "','" +unProduit.getCategorie()
			+ "','" +unProduit.getIdclient()+ "');"; 
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}
	public static ArrayList<Produit> selectAllProduits (){
		ArrayList<Produit> lesProduits = new ArrayList<Produit>(); 
		String requete ="select * from produit ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Produit unProduit = new Produit (
						desRes.getInt("idproduit"), desRes.getString("designation"), 
						desRes.getFloat("prixAchat"),desRes.getString("dateAchat"),
						desRes.getString("categorie"), desRes.getInt("idclient")
						);
				lesProduits.add(unProduit);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
		return lesProduits ;
	}
	public static ArrayList<Technicien> selectAllTechniciens (){
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>(); 
		String requete ="select * from technicien ; ";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Technicien unTechnicien = new Technicien (
						desRes.getInt("idtechnicien"), desRes.getString("nom"), 
						desRes.getString("prenom"),desRes.getString("specialite"),
						desRes.getString("dateEmbauche") 
						);
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
		return lesTechniciens ;
	}
	
	public static void appelProcedure (String nomP, String tab[]) {
		String chaine = "'" + String.join("','", tab) + "'"; 
		String requete ="call  " + nomP + "(" + chaine +") ; "; 
		System.out.println(requete);
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			unStat.execute(requete); 
			unStat.close();
			uneBdd.seDeconnecter();
		 }
		 catch(SQLException exp) {
				System.out.println("Erreur execution requete :" + requete);
				exp.printStackTrace();
			}
	}
}





















