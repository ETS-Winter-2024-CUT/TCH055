# Exercice 3

1. En vous basant sur le diagramme de Venn, écrivez larequêtepermettant d’afficher les produits n’ayant pas de Classe associée.

    ```sql
    SELECT *
    FROM Produits P
    LEFT JOIN Classes C ON P.classe = C.classe
    WHERE P.classe IS NULL;
    ```

2. Expliquez pourquoi ceci n’aurait, de toute façon, pas pu donner de résultats.

    Le champ "classe" de la table Produits est non-Null-able, il a donc toujours une valeur.
