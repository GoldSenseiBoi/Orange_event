package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	public static User verifConnexion (String email, String mdp) {
		return Modele.verifConnexion(email, mdp); 
	}

	public static void updateUser(User unUser) {
		Modele.updateUser (unUser); 
		
	}
	/*************** Gestion des clients *************/
	public static void insertClient (Client unClient) {
		Modele.insertClient(unClient);
	}
	public static ArrayList<Client> selectAllClients (String filtre){
		return Modele.selectAllClients(filtre);
	}
	public static void deleteClient (int idclient) {
		Modele.deleteClient(idclient);
	}
	public static void updateClient (Client unClient) {
		Modele.updateClient(unClient);
	}
	/*************** Gestion des Produits *************/
	public static void insertProduit (Produit unProduit) {
		Modele.insertProduit(unProduit);
	}
	public static ArrayList<Produit> selectAllProduits (){
		return Modele.selectAllProduits();
	}
	
	/*************  Gestion des techniciens ***********/
	public static ArrayList<Technicien> selectAllTechniciens (){
		return Modele.selectAllTechniciens();
	}
	
	public static void appelProcedure (String nomP, String tab[]) {
		Modele.appelProcedure(nomP, tab);
	}
}






