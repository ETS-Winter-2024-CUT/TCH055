# Activite 1

```sql
-- RESTRICT (Par défaut):bloque si une des lignes à supprimer a des dépendances.
-- CASCADE: Supprime les lignes dépendantes (si on a définit une politique ON DELETE)

DROP TABLE Patient CASCADE CONSTRAINTS; 
DROP TABLE Medecins CASCADE CONSTRAINTS;

CREATE TABLE Medecins
(
    id_medecin  NUMBER(10) PRIMARY KEY,
    nom         VARCHAR2(50) NOT NULL,
    prenom      VARCHAR2(50) NOT NULL,
);

CREATE TABLE Patients
(
    id_patient  NUMBER(10) PRIMARY KEY,
    nom         VARCHAR2(50) NOT NULL,
    prenom      VARCHAR2(50) NOT NULL,
    id_medecin  NUMBER(10)
);

ALTER TABLE Patients ADD CONSTRAINT FK_Patients_Medecins FOREIGN KEY (id_medecin)
    REFERENCES Medecins(id_medecin)
    ON DELETE SET NULL;

COMMIT;
```
