# [Exercice 5](Exercice_4.md#création-de-la-vue)

1. En utilisant la vue "Sorties_Produits", trouver le nombre de sorties dont le coût total excède 200$.
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
