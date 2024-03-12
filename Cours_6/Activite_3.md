# Activite 3

## Definition de la table Employe

```sql
CREATE TABLE Employe
(
    id_employe  NUMBER(10) PRIMARY KEY,
    nom         VARCHAR2(50) NOT NULL,
    prenom      VARCHAR2(50) NOT NULL,
    salaire     NUMBER(10,2) DEFAULT 0 NOT NULL,
    date_entree DATE,
    CONSTRAINT CHK_Employe_salaire_positif CHECK (salaire >= 0);
)
```

* Definir un declencheur qui empeche la suppression d'employes

```sql
CREATE OR REPLACE TRIGGER TRG_NO_DELETE_EMPLOYE
BEFORE DELETE ON Employe
BEGIN
    RAISE_APPLICATION_ERROR(-20980, 'Impossible de supprimer un employe');
END;
/
```
