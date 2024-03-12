# Activité 1

## Énoncé

- Écrire un bloc PL/SQL qui parcourt tous les projets delatableProjet à l’aide d’un curseur.

- Afficher le nom de la personne qui est chef du projet.

## Modèle physique :

```sql
DROP TABLE Classes CASCADE CONSTRAINTS;
DROP TABLE Produits CASCADE CONSTRAINTS;
DROP TABLE Sorties CASCADE CONSTRAINTS;
DROP TABLE Projets CASCADE CONSTRAINTS;
DROP TABLE Chefs CASCADE CONSTRAINTS;

CREATE TABLE Classes
(
    classe VARCHAR2(3) PRIMARY KEY,
    nom VARCHAR2(50) NOT NULL,
    taxable NUMBER(1) NOT NULL
);

CREATE TABLE Produits
(
    code            NUMBER(10) PRIMARY KEY,
    description     VARCHAR2(25) NOT NULL,
    etagere         VARCHAR2(5),
    unite           VARCHAR2(15) NOT NULL,
    cout            NUMBER(10, 2),
    num_telephone   VARCHAR2(15),
    quantite        NUMBER(38),
    classe          VARCHAR2(3) NOT NULL REFERENCES Classes(classe)
);

CREATE TABLE Chefs
(
    matricule VARCHAR2(10) PRIMARY KEY,
    nom VARCHAR2(50) NOT NULL,
    date_nais date
);

CREATE TABLE Projets
(
    code        VARCHAR2(10) PRIMARY KEY,
    date_debut  DATE NOT NULL,
    date_fin    DATE,
    matricule_chef   VARCHAR2(10) NOT NULL REFERENCES Chefs(matricule)
);

CREATE TABLE Sorties
(
    no_sortie       VARCHAR2(10) PRIMARY KEY,
    code_produit    NUMBER(10) NOT NULL REFERENCES Produits(code),
    date_sortie     DATE NOT NULL,
    code_projet     VARCHAR2(10) NOT NULL REFERENCES Projets(code),
    quantite        NUMBER(10) NOT NULL
);

INSERT INTO Classes (classe, nom, taxable) VALUES
('C1', 'Class A', 1),
('C2', 'Class B', 0),
('C3', 'Class C', 1);

INSERT INTO Produits (code, description, etagere, unite, cout, num_telephone, quantite, classe) VALUES
(1, 'Product 1', 'eta1', 'Unit1', 20.50, '123-456-7890', 100, 'C1'),
(2, 'Product 2', 'eta2', 'Unit2', 15.75, '987-654-3210', 50, 'C2'),
(3, 'Product 3', 'eta3', 'Unit3', 30.25, '555-123-4567', 75, 'C3');

INSERT INTO Chefs (matricule, nom, date_nais) VALUES
('M1', 'Chef 1', TO_DATE('1980-05-15', 'YYYY-MM-DD')),
('M2', 'Chef 2', TO_DATE('1975-10-20', 'YYYY-MM-DD')),
('M3', 'Chef 3', TO_DATE('1985-03-08', 'YYYY-MM-DD'));

INSERT INTO Projets (code, date_debut, date_fin, matricule_chef) VALUES
('P1', TO_DATE('2024-01-01', 'YYYY-MM-DD'), TO_DATE('2024-02-01', 'YYYY-MM-DD'), 'M1'),
('P2', TO_DATE('2024-02-01', 'YYYY-MM-DD'), TO_DATE('2024-03-01', 'YYYY-MM-DD'), 'M2'),
('P3', TO_DATE('2024-03-01', 'YYYY-MM-DD'), NULL, 'M3');

INSERT INTO Sorties (no_sortie, code_produit, date_sortie, code_projet, quantite) VALUES
('S1', '1', TO_DATE('2024-01-10', 'YYYY-MM-DD'), 'P1', 10),
('S2', '2', TO_DATE('2024-02-15', 'YYYY-MM-DD'), 'P2', 5),
('S3', '3', TO_DATE('2024-03-20', 'YYYY-MM-DD'), 'P3', 8);
```

## Solution :

```sql
DECLARE
    CURSOR cur_liste_projets IS
        SELECT matricule_chef
        FROM Projets;

    -- Variables pour stocker les données du projet
    v_projet Projets%ROWTYPE;
    v_nom_chef Chef.nom%TYPE;
BEGIN
    OPEN projets_cursor;

    LOOP
        FETCH projets_cursor INTO proj;

        -- Sortir de la boucle si aucune ligne trouvée
        EXIT WHEN projets_cursor%NOTFOUND;

        SELECT nom
        INTO v_nom_chef
        FROM Chef
        WHERE matricule = proj.matricule;

        DBMS_OUTPUT.PUT_LINE(v_nom_chef);
    END LOOP;

    CLOSE projets_cursor;
END;
/
```
