# Activite 2

## Creation de la table

```sql
DROP TABLE Client CASCADE CONSTRAINTS;

CREATE TABLE Client
(
    id_client       NUMBER(10) PRIMARY KEY,
    nom             VARCHAR(50),
    prenom          VARCHAR(50),
    marge_credit    NUMBER(10,2),
    solde_compte    NUMBER(10,2)
);

INSERT INTO Client 
    VALUES(10, 'Lacoste','Jeanne', 10000, 3524.50),
    VALUES(11, 'Fournier','Pierre', 5000, 500.38),
    VALUES(12, 'Alonzo','Steve', 10000, 8945.60),
    VALUES(13, 'Macron','Emmanuel', 8000, 1250.50),
    VALUES(14, 'Fafard','Lise', 7500, 4278.00),
    VALUES(15, 'Tremblay','Alma', 5000, 741.50),
    VALUES(16, 'Lacasse','Annie', 12000, 11589.50);

COMMIT;
```

## Requete

```sql
SELECT prenom, nom
FROM Client
WHERE solde_compte/marge_credit > 0.6;
```
