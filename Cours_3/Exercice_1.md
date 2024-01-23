# Exercice 1

1. Lister tous les projets.

    ```sql
    SELECT * FROM Projets;
    ```

2. Trouver les noms des chefs de projet de la compagnie.

    ```sql
    SELECT nom FROM Chefs;
    ```

3. Trouver le code et le cout des produits dont on dispose de plus que 20 unités en stock.

    ```sql
    SELECT code, cout, quantite FROM Produits WHERE quantite > 20;
    ```

4. Trouver les produits qui se vendent par sac (voir colonne ‘unite’).

    ```sql
    SELECT * FROM Produits WHERE unite LIKE 'sac'; 
    ```
