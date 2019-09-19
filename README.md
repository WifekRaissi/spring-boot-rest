#     Spring-Boot-Rest-API 

Le but de ce tutorial est de créer une application Spring Boot basée sur le système Restful pour la gestion des salariés.      
Avant de commencer on doit introduire quelques notions :
## 	Spring Boot
Spring Boot est un projet qui facilite la configuration d’un projet Spring.
## 	Rest API
Les API Rest sont basées sur l’Hypertext Transfer Protocol (HTTP), un protocole qui définit la communication entre les différentes parties d’une application web. Un client lance une requête HTTP, et le serveur renvoie une réponse à travers plusieurs méthodes dont les plus utilisées sont : POST, GET, PUT et  DELETE.
##   Outils

JDK 1.7+

L'IDE préféré, on a choisi de travailler avec Intellij IDEA

Maven ou Gradle

# I. Implémentation d'une API Rest avec Spring Boot


## 	Création du projet :

La figure suivante présente l'architecture du projet à réaliser durant ce tutorial.




   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/architecture.PNG)
   
   
   

Une application Spring Boot peut être créée selon l’arborescence  de Maven ou Gradle. Pour comprendre la différence entre les deux outils, cet article explique bien la différence.
https://gradle.org/maven-vs-gradle/
Durant ce projet on a utilisé Maven.
Pour créer le projet il y a trois façons : 

1. Spring Boot Initializer : on a utilisé cette méthode durant le premier tutorial Spring Boot Kotlin   
(https://medium.com/@wifekraissi/a-very-beginner-journey-with-kotlin-and-spring-boot-c46adeff9db8)

2. Command Line Tool (CLI)

3. A partir de l'IDE et Maven: la méthode utilisée pour le présent projet :

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


   parent:pour hériter les propriétés du spring-boot-starter-parent comme le numéro du port  et la configuration.
   
   dependencies : contient toutes les librairies dont dépend le projet.
   
   build: contient les plugins.
   
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
   
   

Jusqu’à maintenant l’adresse localhost:8080 affiche une erreur ce qui est normal puisqu’on n’a pas encore créé le contrôleur. 

## Contrôleur:
Le contrôleur asssure la communication entre l'application et les clients, il reçoit les requêtes  et renvoie les réponses.

## SalariesController.java
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

@RequestMapping : Son rôle principal est de préciser quelle méthode doit être appelée pour une URI donnée. Elle peut être remplacée 
directement par l'une des annotation: @GetMapping, @PostMapping, @DeleteMapping ou @PutMapping.

Maintenant si on exécute l'application le message défini dans le contrôleur sera affiché.



   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/6.png)


## Salarie.java
Le but de cette application est de gérer les différentes opérations concernant les salariés. Pour ce fait on doit créer une classe Salarie qui regroupe les propriétes d'un salarié

```
package com.axeane.model;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Salarie {
    private long id;
    private static final AtomicInteger count = new AtomicInteger(-1);

    private String nom;
    private String prenom;
    private BigDecimal salaire;
    private String adresse;

   public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }

    public String getAdresse() {
        return adresse;
    }

    @Required
    public void setAdresse(String adresse) {

        this.adresse = adresse;
    }

    public Salarie() {
    }

    public Salarie(Long id, String nom, String prenom, BigDecimal salaire, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.adresse = adresse;
    }

    public Salarie(String nom, String prenom, BigDecimal salaire, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.adresse = adresse;
        id = count.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Salarie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", salaire=" + salaire +
                ", adresse='" + adresse + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Salarie)) return false;
        Salarie salarie = (Salarie) o;
        return getId() == salarie.getId() &&
                Objects.equals(getNom(), salarie.getNom()) &&
                Objects.equals(getPrenom(), salarie.getPrenom()) &&
                Objects.equals(getSalaire(), salarie.getSalaire()) &&
                Objects.equals(getAdresse(), salarie.getAdresse());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNom(), getPrenom(), getSalaire(), getAdresse());
    }
}
```
## Services
Maintenant on ajoute une interface SalariesService définissant les différentes méthodes gérées par le contôleur.
Ces méthodes sont implémentées dans la classe SalariesServicesImpl.

### SalariesService
```
package com.axeane.services;


import com.axeane.model.Salarie;

import java.util.List;

public interface SalariesService {

    void addsalarie(Salarie salarie);

    List<Salarie> getListSalaries();

    Salarie findSalariedById(Long searchedId);

    void deleteSalaried(Long id);

    void updateSalarie(Salarie salaried);

}

```
### SalariesServiceImpl
La classe ServiceImpl doit être un bean spring à travers l'une des annotations  @Service ou @Component.
```
package com.axeane.services;


import com.axeane.model.Salarie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SalariesServiceImpl implements SalariesService {

    private Logger logger = LoggerFactory.getLogger(SalariesServiceImpl.class);
    private List<Salarie> salariess = Stream.of(
            new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis"),
            new Salarie("rahma", "raissi", new BigDecimal(55555), "Tunis"),
            new Salarie("amine", "raissi", new BigDecimal(88888), "Tunis"),
            new Salarie("ines", "raissi", new BigDecimal(999999), "Tunis"))
            .collect(Collectors.toList());

    @Override
    public void addsalarie(Salarie salarie) {
        salariess.add(salarie);
    }

    @Override
    public List<Salarie> getListSalaries() {
        return salariess;
    }

    @Override
    public Salarie findSalariedById(Long searchedId) {
        return salariess.stream()
                .filter(x -> searchedId.equals((x.getId())))
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteSalaried(Long id) {
        Salarie salarie = findSalariedById(id);
        salariess.remove(salarie);
    }

    @Override
    public void updateSalarie(Salarie salarie) {
        Salarie salarie1 = findSalariedById(salarie.getId());
        if (salarie1 != null) {
            salarie1.setNom(salarie.getNom());
            salarie1.setPrenom(salarie.getPrenom());
            salarie1.setAdresse(salarie.getAdresse());
            salarie1.setSalaire(salarie.getSalaire());
        }
    }

}

```
 
 
 
 ### SalariesController
 On peut maintenant compléter SalariesController par les différentes requêtes HTTP en injectant la classe service.
 
 ```
package com.axeane.controllers;

import com.axeane.model.Salarie;
import com.axeane.services.SalariesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalariesController {

    private final SalariesService salariesService;

    public SalariesController(SalariesService salariesService) {
        this.salariesService = salariesService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addSalaries(@RequestBody Salarie salarie) {
        salariesService.addsalarie(salarie);
        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getSalaries() {
        List<Salarie> salaries = salariesService.getListSalaries();
        if (salaries != null) {
            return new ResponseEntity<>(salaries, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSalariesById(@PathVariable("id") long id) {
        Salarie salarie = salariesService.findSalariedById(id);
        if (salarie != null) {
            return new ResponseEntity<>(salarie, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity updateSalaries(@RequestBody Salarie salarie) {
        if (salariesService.findSalariedById(salarie.getId()) != null) {
            salariesService.updateSalarie(salarie);
            return new ResponseEntity<>(salarie, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteSalaries(@PathVariable("id") Long id) {
        Salarie salarie = salariesService.findSalariedById(id);
        if (salarie != null) {
            salariesService.deleteSalaried(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
```
@GetMapping :utilisée pour récupérer des informations à partir d'un serveur sans rien modifier. 

@PostMapping :utilisée pour envoyer des données au serveur.

@PutMapping : utilisée pour modifier des données.

@DeleteMapping :utilisée pour supprimer des données.

@PathVariable: utilisée pour extraire des données à partir de l'URI.

## Tests:
Pour tester les différentes requêtes il suffit d’utiliser Postman.





   ## Get

   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/get.PNG)
   
   
   
   
   
   ## Post
   
   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/post.PNG)
   
   
   
   
   
   
   ## Update
   
   
   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/put.PNG)
   
   
   
   
   
   ## Delete
   
   
   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/delete.PNG)
   
   

# II. Validation des données
 
 En envoyant des requêtes au serveur on attend des formats bien définis des données récupérées, mais pour différentes raisons on peut recevoir des résultats qui ne respectent pas les formats prédéfinis ou bien des erreurs qui ne seront pas comprises par l'utilisateur.
d'où le besoin d'implémenter des méthodes qui gérent les erreurs et surtout d'indiquer avec précision d'où vient l'exception avec un message clair.


## Implémentation des validateurs

### Salaries.java
   @NotEmpty:pour indiquer que le champ ne doit pas être vide.
   
   @NotNull:pour indiquer que la valeur ne doit pas être null.
   
   @Size: pour indiquer la longueur minimale et/ou maximale d'une chaîne avec et message d'erreur au de violation de la contrainte.
  
  ```
    @NotEmpty
    @NotNull
    private String nom;

    @NotEmpty
    @NotNull
    private String prenom;

    @NotNull
    private BigDecimal salaire;

    @NotEmpty
    @NotNull
    @Size(max = 256, message = "address should have maximum 256 characters")
    private String adresse;

  ```
On peut trouver plus d'annotations de validation si on a besoin dans la liste suivante:
DecimalMax
DecimalMin
Digits
Email
Future
FutureOrPresent
Max
Min
Negative
NegativeOrZero
NotBlank
NotEmpty
NotNull
Null
Past
PastOrPresent
Pattern
Positive
PositiveOrZero
   

Pour autoriser la validation, il faut ajouter @Valid avec @RequestBody.

```
 public ResponseEntity addSalaries(@Valid @RequestBody Salarie salarie) {
        salariesService.addsalarie(salarie);
        return new ResponseEntity<>(salarie, HttpStatus.CREATED);
    }
```

Maintenant si on execute une requête Post avec des données qui ne respectent pas le format on obtient un status "404 BAD Request".
On sait qu'il s'agit d'une mauvaise requête mais on ne sait pas d'où vient exactement le problème.

## Personnalisation de la réponse de validation

On peut définir une réponse personnalisée en utilisant l'API de Zalando https://github.com/zalando/problem-spring-web 
```
package com.salaries.demo.utils;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.Status;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

import java.net.URI;
import java.util.Date;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Couldn't create document")

public class SalarieException extends AbstractThrowableProblem {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer problemObjectMapperModules() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
                new ProblemModule(),
                new ConstraintViolationProblemModule()
        );
    }

    private static final long serialVersionUID = 1L;
    private static final URI TYPE = URI.create("https://salaries.org/failed");
    private Date timestamp;
    private String details;

    public SalarieException(Date timestamp, final String title, String details) {
        super(TYPE, title, Status.FORBIDDEN);
        this.timestamp = timestamp;
        this.details = details;
    }

    public SalarieException() {
        super(TYPE, "Salaried not found", Status.NOT_FOUND);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setDetails(String details) {
        this.details = details;
    }


}
```
## ControllerAdvice
@ControllerAdvice: c'est une annotation spring qui permet d'implémenter un code qui sera appliqué sur toutes les classes de type contrôleur.

## CustomizedResponseEntityExceptionHandler.java 
```

import com.axeane.utils.ExceptionResponse;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.validation.ConstraintViolationProblemModule;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice()
public class CustomizedResponseEntityExceptionHandler implements ProblemHandling {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer problemObjectMapperModules() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
                new ProblemModule(),
                new ConstraintViolationProblemModule()
        );
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ExceptionResponse badRequest(final ExceptionResponse exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse notFoundRequest(final ExceptionResponse exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleException(final Exception exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return error;
    }
}
```
On peut maintenant remarquer la différence en testant avec Postman:

   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/validation.PNG)


# III. Tests

dans ce tutorial on s'intéresse aux tests unitaires et d'integration:
## III.1 Test unitaire
Avec les tests unitaires On vérifie le bon fonctionnement d'une partie précise d'un logiciel ou d'une portion d'un programme. Chaque classe doit être testée en isolation complète  

## SalariesServiceImplTest.java

```

import com.axeane.model.Salarie;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class SalariesServiceImplTest {

    private SalariesServiceImpl salariesServiceImpl;

    @Before
    public void setUp() {
        salariesServiceImpl = new SalariesServiceImpl();
    }

    @Test
    public void addSalarie() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        int sizeAfter = sizeBefore + 1;
        assertThat(sizeAfter, is(sizeBefore + 1));
        Salarie salarie1 = salaries.get(salaries.size() - 1);
        assertEquals("amira", salarie1.getNom());
    }

    @Test
    public void addSalarieNotEmpty() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        assertFalse(salarie.getNom().isEmpty());
        assertFalse(salarie.getPrenom().isEmpty());
        assertFalse(salarie.getAdresse().isEmpty());
    }

    @Test
    public void addSalarieNotNull() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        assertNotNull(salarie.getNom());
        assertNotNull(salarie.getPrenom());
        assertNotNull(salarie.getAdresse());
        assertNotNull(salarie.getSalaire());
        assertFalse(salarie.getAdresse().length() > 256);
    }

    @Test
    public void testAdresseLengh() throws Exception {
        Salarie salarie = new Salarie("amira", "raissi", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
    }

    @Test
    public void getListSalaries() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        salaries.add(salarie);
        int sizeAfter = sizeBefore + 1;
        assertFalse(salaries.isEmpty());
        assertThat(sizeAfter, is(sizeBefore + 1));
        Assertions.assertThat(salaries).contains(salarie);
    }

    @Test
    public void findSalariedById() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        System.out.println(salaries.toString());
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        salaries.add(salarie);
        Salarie salarie1 = salariesServiceImpl.findSalariedById((long) (salaries.size()));

        assertThat(salarie1.getId(), is(salarie.getId()));
        assertThat(salarie1.getNom(), is(salarie.getNom()));
        assertThat(salarie1.getPrenom(), is(salarie.getPrenom()));
        assertThat(salarie1.getAdresse(), is(salarie.getAdresse()));
    }

    @Test
    public void deleteSalaried() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        int sizeBefore = salaries.size();
        salariesServiceImpl.deleteSalaried((long) salaries.size());
        int sizeAfter = salaries.size();
        assertThat(sizeAfter, is(sizeBefore - 1));
    }

    @Test
    public void updateSalarie() throws Exception {
        List<Salarie> salaries = salariesServiceImpl.getListSalaries();
        Salarie salarie = new Salarie("raissi", "abir", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.addsalarie(salarie);
        Salarie salarie2 = new Salarie((long) (salaries.size()), "wifek", "wifek", new BigDecimal(444444), "Tunis");
        salariesServiceImpl.updateSalarie(salarie2);
        assertThat(salaries.get(salaries.size() - 1).getPrenom(), is("wifek"));
        assertThat(salaries.get(salaries.size() - 1).getNom(), is("wifek"));
    }
}
```
## SalariesControllerTest.java
```
import com.axeane.model.Salarie;
import com.axeane.services.SalariesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(SalariesController.class)
public class SalariesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    @Autowired
    SalariesService salarieServiceMock;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void addSalarie() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        given(salarieServiceMock.addsalarie(salarie)).willReturn(salarie);
        mockMvc.perform(post("/salaries")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(salarie)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("ilyes"))
                .andExpect(jsonPath("$.prenom").value("raissi"))
                .andExpect(jsonPath("$.salaire").value(444444))
                .andExpect(jsonPath("$.adresse").value("Tunis"));
        verify(salarieServiceMock, times(1)).addsalarie(salarie);
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void getSalaries() throws Exception {
        Salarie salarie = new Salarie();
        salarie.setNom("Amine");
        List<Salarie> salaries = singletonList(salarie);
        given(salarieServiceMock.getListSalaries()).willReturn(salaries);
        mockMvc.perform(get("/salaries")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nom").value(salarie.getNom()));
    }

    @Test
    public void getSalariesById() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        given(salarieServiceMock.findSalariedById(salarie.getId())).willReturn(salarie);
        mockMvc.perform(get("/salaries/" + salarie.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom", is(salarie.getNom())));
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void updateSalaries() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        when(salarieServiceMock.findSalariedById(salarie.getId())).thenReturn(salarie);
        doNothing().when(salarieServiceMock).updateSalarie(salarie);
        mockMvc.perform(
                put("/salaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salarie)))
                .andExpect(status().isOk());
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verify(salarieServiceMock, times(1)).updateSalarie(salarie);
        verifyNoMoreInteractions(salarieServiceMock);
    }

    @Test
    public void deleteSalaries() throws Exception {
        Salarie salarie = new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis");
        when(salarieServiceMock.findSalariedById(salarie.getId())).thenReturn(salarie);
        doNothing().when(salarieServiceMock).deleteSalaried(salarie.getId());
        mockMvc.perform(
                delete("/salaries/{id}", salarie.getId()))
                .andExpect(status().isOk());
        verify(salarieServiceMock, times(1)).findSalariedById(salarie.getId());
        verify(salarieServiceMock, times(1)).deleteSalaried(salarie.getId());
        verifyNoMoreInteractions(salarieServiceMock);
    }
}
```
## III.2 Tests d'intégration

Les tests d'intégration Couvrent toute l’application. Chacun des modules indépendants du logiciel est assemblé et testé dans l'ensemble

  ## SalariesControllerIntegrationTest.java
  
  
  ```
  
import com.axeane.MainApplicationClass;

import com.axeane.model.Salarie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplicationClass.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalariesControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addSalarie() {
        ResponseEntity<Salarie> responseEntity =
                restTemplate.postForEntity("/salaries", new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis"), Salarie.class);
        Salarie salarie = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assert salarie != null;
        assertEquals("ilyes", salarie.getNom());
    }
}
  ```
# IV. Intégration du Swagger  

Swagger nous permet de décrire la structure de l'API créée pour faciliter sa consommation.

Pour Commencer on doit ajouter la  dépendance suivante:

```
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
            <scope>compile</scope>
        </dependency>
  ```
        
On peut vérifier son fonctionnement en visitant l'URL: http://localhost:8080/v2/api-docs 
              
   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/sawagger.PNG)



Pour simplifier l'utilisation de Swagger on peut autoriser une interface graphique par l'ajout de la dépendance suivante:      
```
          <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
            <scope>compile</scope>
        </dependency>
  ```
   


L'interaction entre l'utilisateur et l'API est maintenant plus facile à partir de l'URL suivante http://localhost:8080/swagger-ui.html.



   ![alt text](https://github.com/WifekRaissi/spring-boot-rest/blob/master/src/main/resources/images/swagger%20ui.PNG)
   
   
## A noter:
 Ce tutorial est l'introduction à une série de tutoriaux pour Spring Boot. Dans le tutorial suivant on va commencer Spring Data par l'intégration de MySQL avec cette même application.
 
 Plus de détails dans: https://github.com/WifekRaissi/spring-boot-rest-data-mysql
  
  
