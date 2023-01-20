# mycine-Back

## Configuration de la BDD

Vous pouvez télécharger le bin de PostgreSQL.

Pour la première configuration de la bdd, il faudra se placer dans pgsql/bin

```
+-- postgresql
|   +-- data
|   +-- pgsql
|   |  +-- bin    
|   |  +-- doc 
|   |  +-- lib
|   |  +-- ...  
```

puis saisir les commandes suivantes :

```shell script
$ initdb -D ../../data -E utf8
$ pg_ctl -D ../../data -l logfile start
$ createuser -s -P mycine-admin
Enter password for new role: mycine-admin
Enter it again: mycine-admin
$ createdb -O mycine-admin -E utf8 mycine 
```

Lancer la base de données

```
$ pg_ctl -D ../../data -l logfile start
```
Arrêter la base de données

```
$ pg_ctl -D ../../data -l logfile stop
```

Réinitialiser la base de données

```
$ dropdb mycine && createdb -O mycine-admin -E utf8 mycine
```

## Lancement de l'application

```
$ mvnw spring-boot:run
```

***
