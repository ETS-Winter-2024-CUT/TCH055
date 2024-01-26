# Exercice 3

1. En se basant sur le diagramme de Venn, écrire la requête permettant d’afficher les produits n’ayant pas de Classe associée.

    ```sql
    SELECT *
    FROM Produits P
    LEFT JOIN Classes C ON P.classe = C.classe
    WHERE P.classe IS NULL;
    ```

2. Explication de pourquoi ceci n’aurait, pas pu donner de résultats.

    Le champ "classe" de la table Produits est non-Null-able, il a donc toujours une valeur.
