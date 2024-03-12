# Activité 3

## Énoncé

Modifier la procédure de l'[activité précédente](Activite_2.md#solution) pour que l’appelant sache combien de produits ont été insérés dans la table `Comptabilité` par la procédure.

## Solution

```sql
CREATE OR REPLACE PROCEDURE p_Remplir_Comptabilite
IS
    CURSOR cur_produits IS
        SELECT *
        FROM Produit
        JOIN Classe ON Produit.classe = Classe.produit;

    v_total_inventaire NUMBER(10, 2);
    v_nb_insertions NUMBER := 0;
BEGIN
    FOR prod IN cur_produits
    LOOP
        v_total_inventaire := prod.prix_txin * prod.quantite_inventaire;

        INSERT INTO Comptabilite (code_produit, desc_produit, prix_txin, total_inventaire)
        VALUES (prod.code_produit, prod.desc_produit, prod.prix_txin, v_total_inventaire);

        v_nb_insertions := v_nb_insertions + 1;
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Nombre d''insertions : ' || v_nb_insertions);
END;
/
```
