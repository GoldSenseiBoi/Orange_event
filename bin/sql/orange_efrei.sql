drop database if exists orange_efrei; 
create database orange_efrei; 
use orange_efrei; 

create table client (
	idclient int(3) not null auto_increment, 
	nom varchar (50), 
	prenom varchar (50), 
	adresse varchar (50), 
	email varchar(50), 
	primary key (idclient)
);

create table produit (
	idproduit int(3) not null auto_increment, 
	designation varchar (50), 
	prixAchat float , 
	dateAchat date , 
	categorie enum("Téléphone", "Informatique","Télévision"), 
	idclient int(3) not null, 
	primary key (idproduit), 
	foreign key (idclient) references client(idclient)
);
create table technicien (
	idtechnicien int(3) not null auto_increment, 
	nom varchar (50), 
	prenom varchar (50), 
	specialite varchar (50), 
	dateEmbauche date, 
	primary key (idtechnicien)
);
create table intervention (
	idinter  int(3) not null auto_increment, 
	description text, 
	prixInter float , 
	dateInter date , 
	idproduit int(3) not null,
	idtechnicien int(3) not null, 
	primary key (idinter),
	foreign key (idproduit) references produit(idproduit), 
	foreign key (idtechnicien) references technicien(idtechnicien)
);
create table user (
	iduser int(3) not null auto_increment,
	nom varchar(50), 
	prenom varchar(50), 
	email varchar(50), 
	mdp varchar(200), 
	role enum ("admin", "user"), 
	primary key (iduser)
);

insert into user values 
(null, "Paul", "Julien", "a@gmail.com", "123","admin"), 
(null, "Corentin","Ibrahima","b@gmail.com", "456", "user");

delimiter  $
create procedure deleteClient (IN p_idclient int)
Begin 
	delete from produit where idclient = p_idclient ; 
	delete from client where idclient = p_idclient ; 
End   $
delimiter   ; 

delimiter $ 
create procedure insertClient (IN p_nom varchar(50), 
		IN p_prenom varchar(50), IN p_adresse varchar(50), 
		IN p_email varchar(50))
Begin 
	# verifier avant insertion qu il nexiste pas de client avec le meme 
	# email. 
	declare nb int ; 
	select count(*) into nb from client where email = p_email ; 
	if nb = 0 then 
	insert client values (null, p_nom, p_prenom, p_adresse, p_email); 
	end if ; 
End $
delimiter  ; 


delimiter  $ 
create procedure insertProduit (IN p_designation varchar (50), 
	IN p_prixAchat float , IN p_dateAchat date , 
	IN p_categorie varchar(50), IN p_idclient int)
Begin 
	#si le prixAchat < 0 alors prixAchat = 0 
	if p_prixAchat < 0 then 
	set p_prixAchat = 0 ; 
	end if ; 

	# si la date est null, mettre la date aujourdhui 
	if p_dateAchat = "" or p_dateAchat is null then 
	set p_dateAchat = sysdate() ; 
	end if ;  
	# la categorie par defaut est Télévision 
	if p_categorie = "" or p_categorie is null then 
	set p_categorie = "Télévision"; 
	# insertion du produit 
	insert into produit values (null, p_designation, p_prixAchat, 
		p_dateAchat, p_categorie, p_idclient);
End  $
delimiter  ;

















