# Activité 2

## Énoncé

- Écrire la procédure PL/SQL `p_Remplir_Comptabilitequi`, en utilisant un **CURSEUR**, ajoute dans la table `Comptabilite` les informations sur les produits, sachant que:

    - La colonne `prix_txin` représente le coût du produit, taxes incluses;
    - La colonne total_inventaire doit contenir le montant total du produit en inventaire (le prix taxes incluses * quantité en inventaire).

## Modèle physique :

```sql
DROP TABLE Comptabilite CASCADE CONSTRAINTS;

CREATE TABLE Comptabilite(
    code_produit VARCHAR2(10) PRIMARY KEY,
    desc_produit VARCHAR2(50) NOT NULL,
    prix_txin   NUMBER(10,2)   NOT NULL,
    total_inventaire NUMBER(10,2) NOT NULL
);
```

## Solution

```sql
CREATE OR REPLACE PROCEDURE p_Remplir_Comptabilite
IS
    CURSOR cur_produits IS
        SELECT *
        FROM Produit
        JOIN Classe ON Produit.classe = Classe.produit;

    v_total_inventaire NUMBER(10, 2);
BEGIN
    FOR prod IN cur_produits
    LOOP
        v_total_inventaire := prod.prix_txin * prod.quantite_inventaire;

        INSERT INTO Comptabilite (code_produit, desc_produit, prix_txin, total_inventaire)
        VALUES (prod.code_produit, prod.desc_produit, prod.prix_txin, v_total_inventaire);
    END LOOP;
END;
/
```
