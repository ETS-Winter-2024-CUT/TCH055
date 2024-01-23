# Exercice 5

1. En utilisant la vue "Sorties_Produits", trouver le nombre de sorties dont le coût total excède 200$.
    ```sql
    CREATE VIEW Sorties_Produits
    AS
    SELECT P.code, P.description, P.classe, P.cout * S.quantite as cout_total, C.taxable
    FROM Sorties S
    INNER JOIN Produits P on S.code_produit = P.code
    INNER JOIN Classes C on P.classe = C.classe;
    ```

    Puis

    ```sql
    SELECT COUNT(*)
    FROM Sorties_Produits SP
    WHERE SP.cout_total > 200;
    ```

2. Trouver le montant total des produits sortis de l’inventaire qui proviennent de la classe "C1"
    ```sql
    SELECT SUM(cout_total)
    FROM Sorties_Produits
    WHERE classe LIKE 'C1';
    ```
