# Cours 6

## Exemple de PL/SQL

```sql
CREATE OR REPLACE TRIGGER TRG_employe_salaire_augmente
BEFORE UPDATE ON Employe
FOR EACH ROW
BEGIN
    IF :NEW.salaire > OLD.salaire * 1.1
    THEN :NEW.salaire := :OLD.salaire * 1.1;
    END IF;
END;
/
```
