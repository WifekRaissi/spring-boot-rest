## spring-boot-restSpring-Boot-Rest-API 

Durant ce tutorial on est mené à créer une application Spring Boot basée sur le système Restful pour la gestion des salariés.
Avant de commencer on doit définir quelques notions :
## I.	Spring Boot
Spring Boot est un conteneur qui comporte tous les projets Spring. Il reprend une infrastructure similaire à un serveur d’application JEE avec un minimum ou presque sans configuration.
Spring Boot consiste à créer des applications java en se concentrant sur le métier.
## II.	Rest API
Les API Rest sont basées sur l’ Hypertext Transfer Protocol (HTTP), un protocole qui définit la communication entre les différentes parties d’une application web. Un client lance une requête HTTP, et le serveur renvoie une réponse à travers plusieurs méthodes dont les plus utilisées sont : POST, GET, PUT et  DELETE.
## III.	Outils
	Java Development Kit (JDK) 1.7+
        L’environnement de développement préféré, on a choisi de travailler avec Intellij IDEA.

## IV.	Création du projet :
     ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/architecture.PNG)

Une application Spring Boot est créée selon l’arborescence  de Maven ou Gradle, pour comprendre la différence entre les deux outils, cet article explique bien la différence.
https://gradle.org/maven-vs-gradle/
Durant ce projet on a utilisé Maven.
Donc pour créer un projet Spring Boot il y a trois façons : 
1.	Spring Boot Initializer : on a utilisé cette méthode durant le premier tutorial Spring Boot-Kotlin(https://medium.com/@wifekraissi/a-very-beginner-journey-with-kotlin-and-spring-boot-c46adeff9db8)
2.	Command Line Tool (CLI)
3.	 Maven with IDE : la méthode utilisée pour le présent projet :
	Dand Intellij IDEA : File puis new project et on choisit Maven

     ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/nouveauprojet.png)
    
 
Puis on remplit les informations demandées :
 GroupId : l’identifiant unique de l’organisation ou l’entreprise qui est généralement sous la forme : com.monentreprise.
ArtifactId : nom unique du projet
Et la version du projet.
      ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/2.png)

On obtient un projet Spring Boot avec un fichier de configuration maven : Pom.xml.
      ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/3.png)

 
Maintenant on peut ajouter les dépendances nécessaires.



<parent> : pour hériter les propriétés du spring-boot-starter-parent comme le numéro du port  et la configuration.
<dependencies> : contient toutes les librairies dont dépend le projet, à ce stade on a besoin de spring-boot-starter-web.
<build> : contient les plugins.
On doit mettre à jour le fichier Pom pour télécharger les dépendances (clic droit sur le projet créé ->Maven->Generate Sources and Update Folders).

## Classe Main :
On doit créer un package sous java puis une classe « MainApplicationClass.java »
@SpringBootApplication : pour indiquer qu’il s’agit d’une application Spring Boot.
Maintenant on peut exécuter l’application sur le port par défaut 8080.
      ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/4.png)

Jusqu’à maintenant l’adresse localhost:8080 ce qui est normal puisqu’on n’a pas encore créer le contrôleur. 
Contrôleur :
Le contrôleur reçoit les requêtes des clients et renvoi les réponses.
SalariesController.java





@RestController : indique qu’il s’agit un controller, elle combine les deux annotations : @Controller et @ResponseBody.
@RequestMapping : 
       ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/5.png)





 
 






