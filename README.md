###  spring-boot-restSpring-Boot-Rest-API 

Le but de ce tutorial est de créer une application Spring Boot basée sur le système Restful pour la gestion des salariés.      
Avant de commencer on doit introduire quelques notions :
## I.	Spring Boot
Spring Boot est un conteneur qui comporte tous les projets Spring. Il reprend une infrastructure similaire à un serveur d’application JEE avec un minimum ou presque sans configuration.
## II.	Rest API
Les API Rest sont basées sur l’Hypertext Transfer Protocol (HTTP), un protocole qui définit la communication entre les différentes parties d’une application web. Un client lance une requête HTTP, et le serveur renvoie une réponse à travers plusieurs méthodes dont les plus utilisées sont : POST, GET, PUT et  DELETE.
## III.	Outils

JDK 1.7+

L'IDE préféré, on a chois de travailler avec Intellij IDEA

## IV.	Création du projet :

La figure suivante présente l'architecture du projet à réaliser durant ce tutorial.




   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/architecture.PNG)
   
   
   

Une application Spring Boot peut être créée selon l’arborescence  de Maven ou Gradle. Pour comprendre la différence entre les deux outils, cet article explique bien la différence.
https://gradle.org/maven-vs-gradle/
Durant ce projet on a utilisé Maven.
Pour créer le projet il y a trois façons : 
1.Spring Boot Initializer : on a utilisé cette méthode durant le premier tutorial Spring Boot Kotlin    (https://medium.com/@wifekraissi/a-very-beginner-journey-with-kotlin-and-spring-boot-c46adeff9db8)
2.Command Line Tool (CLI)
3. Maven with IDE : la méthode utilisée pour le présent projet :

Dans Intellij IDEA : File puis new project et on choisit Maven,




   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/nouveauprojet.png)
   

   
 Puis on remplit les informations demandées :
 GroupId : l’identifiant unique de l’organisation ou l’entreprise qui est généralement sous la forme : com.monentreprise.
 ArtifactId :le nom unique du projet
 La version du projet.
 
 

   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/2.png)
   

On obtient un projet Spring Boot avec un fichier de configuration maven : Pom.xml.


   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/3.png)

 
Maintenant on peut ajouter les dépendances nécessaires.
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.axeane</groupId>
    <artifactId>SpringBootRest</artifactId>
    <version>1.0-SNAPSHOT</version>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.4.RELEASE</version>
    </parent>

    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-autoconfigure -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-autoconfigure</artifactId>
            <version>2.0.4.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

```


<parent> : pour hériter les propriétés du spring-boot-starter-parent comme le numéro du port  et la configuration.
<dependencies> : contient toutes les librairies dont dépend le projet.
<build> : contient les plugins.
On doit mettre à jour le fichier Pom pour télécharger les dépendances (clic droit sur le projet créé ->Maven->Generate Sources and Update Folders).

# Main Class 
On doit créer un package sous java puis une classe « MainApplicationClass.java »
```
package com.axeane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplicationClass {

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}
```
@SpringBootApplication : pour indiquer qu’il s’agit d’une application Spring Boot.
Maintenant on peut exécuter l’application sur le port par défaut 8080. 



   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/4.png)
   
   

Jusqu’à maintenant l’adresse localhost:8080 affiche une erreur ce qui est normal puisqu’on n’a pas encore créer le contrôleur. 

# Contrôleur:
Le contrôleur asssure la communication entre l'application et les clients, il reçoit les requêtes  et renvoi les réponses.

# SalariesController.java
```
package com.axeane.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalariesController {

    @RequestMapping("/")
    public String index() {
        return "votre controller SalariesController.java a été bien créé";
    }
}
```


@RestController : indique qu’il s’agit un controller, elle combine les deux annotations : @Controller et @ResponseBody.
@RequestMapping : Son rôle principal est de préciser quelle méthode doit être appelée pour une URI donnée. Elle peut être remplacée directement par l'une des annotation: @GetMapping, @PostMapping, DeleteMapping ou PutMapping.

Maintenant si on exécute l'application le message défini dans le contrôleur sera affiché.



   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/5.png)





 
 






