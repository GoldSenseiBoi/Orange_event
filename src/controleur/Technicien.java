package controleur;

public class Technicien 
{
	private int idtechnicien ; 
	private String nom, prenom, specialite, dateEmbauche ;
	public Technicien(int idtechnicien, String nom, String prenom, String specialite, String dateEmbauche) {
		super();
		this.idtechnicien = idtechnicien;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.dateEmbauche = dateEmbauche;
	} 
	public Technicien( String nom, String prenom, String specialite, String dateEmbauche) {
		super();
		this.idtechnicien = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.specialite = specialite;
		this.dateEmbauche = dateEmbauche;
	}
	public int getIdtechnicien() {
		return idtechnicien;
	}
	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getDateEmbauche() {
		return dateEmbauche;
	}
	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	} 
	
}


 