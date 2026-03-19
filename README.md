Opgave strategy pattern til validering med global fejlhåndtering
I skal bygge en lille Spring Boot applikation, hvor validering af input er implementeret med Strategy pattern, og hvor fejl håndteres centralt med ControllerAdvice. Målet er at holde controlleren tynd, samle forretningslogik i service-laget og gøre validering udskiftelig uden store if-else kæder.

Strategy pattern
strategy pattern er et design pattern, hvor du indkapsler forskellige måder at løse den samme opgave på i hver sin klasse og gør dem udskiftelige. Idéen er, at en klasse (context) ikke selv ved hvordan noget gøres, men får en “strategi” sat ind, som udfører arbejdet.

Hvad problemet er uden strategy pattern uden strategy ender man ofte med mange if else eller switch, fx:
* hvis type er a, gør sådan
* hvis type er b, gør noget andet
Det gør koden sværere at udvide og vedligeholde.

Hvordan strategy pattern løser det
* fælles interface for en bestemt adfærd
* én klasse pr. strategi
* context bruger interfacet, ikke de konkrete klasser

Eksempel
// strategy interface //
public interface PaymentStrategy {
void pay(int amount);
}

// concrete strategies //
public class CreditCardPayment implements PaymentStrategy {
public void pay(int amount) {
System.out.println("betaler med kreditkort: " + amount);
  }  
}

public class MobilePayPayment implements PaymentStrategy {
public void pay(int amount) {
System.out.println("betaler med mobilepay: " + amount);
  }
}

// context //
public class PaymentService {
private PaymentStrategy strategy;

public void setStrategy(PaymentStrategy strategy) {
this.strategy = strategy;
  }

public void pay(int amount) {
strategy.pay(amount);
  }
}

Brug i praksis
PaymentService service = new PaymentService();
service.setStrategy(new MobilePayPayment());
service.pay(100);

Hvad man skal huske på 2 semester
* strategy handler om adfærd, ikke data
* du kan skifte strategi uden at ændre resten af koden
* i spring bruges strategy ofte sammen med dependency injection

Strategy pattern gør din kode mere fleksibel og lettere at udvide uden at ændre eksisterende kode.

Case

I bygger en registreringsside, hvor en bruger opretter en konto med:
* username
* email
* password

Når brugeren trykker opret, skal systemet validere input og enten vise en success-side eller en fejlside med en forståelig besked.

Krav til arkitektur
I skal bruge lagdelt arkitektur:
* Controller
* Service
* Validering (strategier)
* Model

Controlleren må ikke indeholde valideringslogik.

Del 1 model og view
1.1 Model
Lav en MOdel fx RegisterUserModel med felterne:
* username
* email
* password

Den skal bruges til data-binding fra formularen.

1.2 Thymeleaf sider
Lav mindst to sider:
* register.html med formular
* success.html som vises ved succes

Hvis der sker fejl, skal der vises en fejlside (se del 4).

Del 2 strategy pattern til validering
2.1 Interface
Lav et interface fx:
* ValidationStrategy

Med en metode fx:
* void validate(RegisterUserDto dto

Metoden skal kaste en exception, hvis input er ugyldigt.

2.2 Konkrete strategier
Lav mindst to strategier:
* SimpleValidationStrategy
- username skal være mindst 3 tegn
- email skal indeholde @
- password skal være mindst 6 tegn

* StrictValidationStrategy
- username mindst 6 tegn og må ikke indeholde mellemrum
- email skal matche en simpel regex (fx noget der ligner en mail)
- password mindst 10 tegn og skal indeholde både tal og bogstav

I må gerne lave flere strategier, fx “SchoolPolicyValidationStrategy”.

Del 3 service-laget vælger strategi
3.1 Service
Lav en UserService, der har ansvaret for:
* at vælge en strategi
* at kalde validate
* at returnere resultat hvis alt er ok

Krav: I må ikke lave valg af strategi med en stor if-else i controlleren.

I kan vælge en af disse løsninger:
Løsning A (let)
* Brug en enum eller string parameter fx mode=simple|strict
* Service vælger strategi baseret på mode
Løsning B (for kodehoveder)
* Brug Spring til at injicere en liste af strategier og vælg ud fra navn

Del 4 global fejlhåndtering med controlleradvice
I spring boot kan der opstå fejl flere steder, fx i en controller eller service. uden global fejlhåndtering ender du ofte med at skrive den samme try catch mange steder, eller også får brugeren en uklar standard-fejlside.
global fejlhåndtering med @ControllerAdvice betyder, at du laver ét centralt sted, der “fanger” bestemte exceptions fra dine controllere og bestemmer, hvad der skal ske. typisk:
* du samler fejl-håndteringen ét sted
* du giver brugeren et pænt svar eller en fejl-side
* du kan returnere samme struktur for fejl fra alle endpoints

et lille eksempel til en begynder-rest api
@RestControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
return ResponseEntity.badRequest().body(ex.getMessage());
  }

@ExceptionHandler(Exception.class)
public ResponseEntity<String> handleGeneric(Exception ex) {
return ResponseEntity.status(500).body("der skete en uventet fejl");
  }
}

Hvordan det virker i praksis
* hvis en controller kaster IllegalArgumentException, rammer den første metode og returnerer http 400
* hvis noget andet går galt, rammer den sidste metode og returnerer http 500

Hvis du bruger thymeleaf (html-sider) kan du også i stedet returnere et view-navn (fx "error") og vise en pæn fejlside, men idéen er den samme: fejl håndteres ét sted.

4.2 ControllerAdvice
Lav en klasse fx:
* GlobalExceptionHandler
Med:
* @ControllerAdvice
Og en handler:
* @ExceptionHandler(ValidationException.class)
Den skal:
* lægge en fejlbesked i modellen
* returnere en Thymeleaf-side fx error.html

4.3 fejlview
Lav error.html som viser:
* en kort fejltekst
* selve fejlbeskeden
* et link tilbage til register-siden

Del 5 ekstra krav
5.1 feedback til brugeren
Fejlbeskeden skal være brugervenlig og præcis fx:
* “Password skal være mindst 10 tegn og indeholde både tal og bogstav”
Ikke bare “Validation failed”.

5.2 unit test af strategier
Lav mindst 4 tests:
* 2 for SimpleValidationStrategy
* 2 for StrictValidationStrategy
Test både valid og invalid input.

Ekstraopgaver hvis de bliver færdige
Ekstra 1 flere fejl på én gang
Udvid validate så den kan samle flere fejl og vise dem samlet på error-siden.
Hint
* Lav en exception der indeholder en liste af fejlbeskeder

Ekstra 2 dynamisk valg af strategi i UI
Lav en dropdown i register.html hvor man vælger:
* simple
* strict
Send valget med i post-request.

Tips
Brug DTO binding med ModelAttribute, ligesom i dine andre Thymeleaf opgaver og hold samme struktur med register og success sider, som I kender fra tidligere øvelser
