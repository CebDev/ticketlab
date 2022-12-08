CREATE DATABASE ticketGitDB;

USE ticketGitDB;

CREATE TABLE if NOT EXISTS Projet (
    id INT(10) PRIMARY KEY,
    titre   VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);


CREATE TABLE if NOT EXISTS ProjetTableau (
    projet_id INT(10) NOT NULL,
    tableau_id INT(10) NOT NULL,
    CONSTRAINT projet_tableau_pk PRIMARY KEY (projet_id,tableau_id),
    FOREIGN KEY (projet_id) REFERENCES Projet(id),
    FOREIGN KEY (tableau_id) REFERENCES Tableau(id)
);

CREATE TABLE if NOT EXISTS Tableau (
    id INT(10) PRIMARY KEY,
    titre VARCHAR(255),
    description VARCHAR(255),
    projet_id INT(10) NOT NULL,
    CONSTRAINT FOREIGN KEY (projet_id) REFERENCES Projet(id)
);

CREATE TABLE if NOT EXISTS Etiquette (
    id INT(10) PRIMARY KEY,
    description VARCHAR(255),
    couleur INT(10) NOT NULL UNIQUE
);

CREATE TABLE if NOT EXISTS TableauEtiquette (
    etiquette_id INT(10) UNIQUE NOT NULL,
    tableau_id VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT tableau_etiquette_pk PRIMARY KEY (etiquette_id,tableau_id),
    FOREIGN KEY (etiquette_id) REFERENCES Etiquette(id),
    FOREIGN KEY (tableau_id) REFERENCES Tableau(id)
);


CREATE TABLE if NOT EXISTS Jalon (
    id INT(10) PRIMARY KEY,
    titre VARCHAR(255),
    description VARCHAR(255),
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    CHECK (date_fin >= date_debut),
    CONSTRAINTS id_titre UNIQUE (id,titre)
);

CREATE TABLE if NOT EXISTS Ticket (
    id INT(10) PRIMARY KEY,
    titre VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255),
    est_ouvert TINYINT(1),
    projet_id INT(10) UNIQUE NOT NULL,
    CONSTRAINT FOREIGN KEY (projet_id) REFERENCES Projet(id)
);

CREATE TABLE if NOT EXISTS JalonTicket (
    jalon_id INT(10) NOT NULL,
    ticket_id INT(10) NOT NULL,
    CONSTRAINTS jalon_ticket_pk PRIMARY KEY (jalon_id,ticket_id)
    FOREIGN KEY (jalon_id) REFERENCES Jalon(id),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
);

CREATE TABLE if NOT EXISTS  EtiquetteTicket (
    etiquette_id INT(10) UNIQUE NOT NULL,
    ticket_id INT(10) UNIQUE NOT NULL,
    CONSTRAINT etiquette_ticket_pk PRIMARY KEY (etiquette_id,ticket_id),
    FOREIGN KEY (etiquette_id) REFERENCES Etiquette(id),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(id)
);

CREATE TABLE if NOT EXISTS Membre (
    pseudo VARCHAR(255) PRIMARY KEY,
    prenom VARCHAR(255),
    nom VARCHAR(255),
    role VARCHAR(255) NOT NULL
);

CREATE TABLE if NOT EXISTS TicketMembre (
    ticket_id INT(10) UNIQUE NOT NULL,
    membre_pseudo VARCHAR(255) UNIQUE NOT NULL,
    CONSTRAINT ticket_membre_pk PRIMARY KEY (ticket_id,membre_pseudo),
    FOREIGN KEY (ticket_id) REFERENCES Ticket(id),
    FOREIGN KEY (membre_pseudo) REFERENCES Membre(pseudo)
);


