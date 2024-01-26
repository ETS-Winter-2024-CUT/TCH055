# Exercice 5


## Création de la vue

```sql
CREATE VIEW Sorties_Produits
AS
SELECT P.code, P.description, P.classe, P.cout * S.quantite as cout_total, C.taxable
FROM Sorties S
INNER JOIN Produits P on S.code_produit = P.code
INNER JOIN Classes C on P.classe = C.classe;
```
