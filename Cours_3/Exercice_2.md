# Exercice 2

1. Afficher les produits ainsi que leurs classes respectives.

    ```sql
    SELECT * FROM Produits;
    ```

2. Modifiez votre requête pour n’afficher que le nom du produit, son prix, et s’il est taxable ou non.

    ```sql
    SELECT P.description, P.cout, C.taxable 
    FROM Produits P
    INNER JOIN Classes C ON P.classe = C.classe;
    ```

3. Modifiez la dernière requête pour n’afficher l’informationquepour les produits en stock.

    ```sql
    SELECT P.description, P.cout, C.taxable 
    FROM Produits P
    INNER JOIN Classes C ON P.classe = C.classe
    WHERE P.quantite > 0;
    ```
