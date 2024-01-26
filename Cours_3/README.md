# Cours de Bases de données - Semaine 3
Ce cours de bases de données à l'ÉTS a couvert les bases des interrogrations avec SQL à travers quelques exemples.

## Contenu du cours

### Comment faire une requête ?

```sql
SELECT ... FROM ...;
```

```sql
SELECT ... INTO ...;
```

### Comment manipuler le contenu d'une base de données ?

```sql
INSERT INTO ... VALUES ...;
```

```sql
INSERT INTO ... SELECT ...;
```

```sql
UPDATE ... SET ... WHERE ...;
```

```sql
DELETE ... FROM ... WHERE ...;
```


### Comment faire une sous-requête ?

```sql
SELECT * FROM (
    SELECT nom, prix FROM Produits
) P
WHERE P.prix > 5.00;
```

### Créer une vue

```sql
CREATE OR REPLACE VIEW Nom_Vue AS requestSQL;
```

Exemple:

```sql
CREATE OR REPLACE VIEW Produits_Dispendieux AS
SELECT nom, prix
FROM Produits
WHERE prix > 5.00;
```

### Signes

* Le symbole utilisé pour la comparaison est "`=`".
* Le symbole utilisé pour different est "`<>`".
* On peut utiliser "`not`" pour la difference, par exemple `WHERE NOT Prix = 5.00`.
* Pour une valeur inconnue on peut utiliser "`NULL`", par exemple `WHERE NOT Prix IS NULL`.
* Pour encadrer des valeurs on peut utiliser "`BETWEEN`" ; par exemple `WHERE Prix BETWEEN 3.00 AND 5.00`.

### Renommage

Il est possible de renommer une table pour faciliter l’écriture des conditions et jointures, ainsi que lever l’ambiguïté lors d’auto-jointures

```sql
SELECT nom AS produit_boulanger, prix FROM Produits;
```

```sql
SELECT P.nom, P.prix FROM Produits P WHERE P.id_prd BETWEEN 11 AND 13;
```

### Interrogation de données

* **Produit Cartésien** (X) : 
    ```sql
    SELECT * FROM Produits, Editeurs;
    ```
* **Jointure Naturelle** (⋈) : 
    ```sql
    SELECT * FROM Produits INNER JOIN Editeurs ON Produits.code_edit = Editeurs.code;
    ```
    OU
    ```sql
    SELECT * FROM Produits, Editeurs WHERE Produits.code_edit = Editeurs.code;
    ```

### Agrégats et fonctions d'agrégats

Un agrégats est un regroupement de données ; par exemple dans une table "Employe" regrouper tous les employés par département.


#### Filtrer par agrégat

Il est possible de filtrer les lignes de la table en mettant une condition sur le résultat de la fonction d’agrégat avec la clause "`HAVING`" ; par exemple :

```sql
SELECT classe, COUNT(*)
FROM Sorties_Produits
GROUP BY classe
HAVING COUNT(*) > 5;
```

| classe | COUNT(*) |
| :---: | :---: |
| A15 | 9 |
| C10 | 20 |
| L00 | 17 |
| A20 | 12 |
| A10 | 10 |
| V08 | 8 |
| B39 | 15 |
| M25 | 9 |
