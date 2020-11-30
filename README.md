# Cards with a hand

This application models a card in the standard 52-card deck alongside a hand that uses the card(s).


Table of contents
=================

<!--ts-->
   * [Specs](#specs)
     * [Card](#card)
     * [Hand](#hand)
   * [Features](#features)
   * [Requirements](#requirements)
   * [Libraries](#libraries)
   * [Running the application locally](#running-the-application-locally)
   * [Tests](#tests)    
      * [Card Test](#card-test)
      * [Hand Test](#hand-test)     
   * [Improvement](#improvement)         
<!--te-->

## Specs

| Object                       | purpose                                  | Remarks
| ----------------------------- | ---------------------------------------- | ----------------------------------------
| Card  | Represents a card | A special card exists
| Hand | Represents a hand | has some functionality
| Rank and Suit| Values wrappers | restricts arguments per card type (special/Non-Special)

### Card

-	Every card has a suit (except the Joker), which can be clubs, diamonds, hearts and spades.

-	A card can be either a card with a number on it between 2 and 10, I'll call this a value card, or it can be one of the face cards: Jack, Queen, King or Ace.

-	There is a special card, the Joker, which does not have a suit.

-	Face cards have value of 20.

-	Joker has a value of 0.

### Hand

-	Should allow a card to be added to the hand.

-	Should allow a maximum of 5 cards to be added to a hand.

-	Should be able to calculate the total amount of cards in the hand.

-	Should be able to calculate the total value of cards in the hand.

-	Should allow the hand to be reset.

## Features
### The application uses a number of defensive coding and monitoring techniques for resilience

- Logging events for production readiness, this allows proper monitoring and alerting capabilities.
- Exception messages, which are unit tested.
- Preventing different types of card initialised using different expected values. For example, the application prevents
creating a special card using a face or value card properties.
- Asserting nulls checks.
- Create a new ArrayList of cards when constructing a hand with a list of cards to assert the List type. 
- Return unmodifiable lists when requesting list of cards from hand.

  
## Requirements
- Java 11
- Maven

## Libraries
- Maven
- slf4j (for logging)
- JUnit-jupiter (For unit testing)


## Running the application locally
There are several ways to run a Java on your local machine. 
- Download the zip
- Unzip the zip file (if you downloaded one)

- For Eclipse: 
    
    Open Eclipse File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
    Select the project.
    
- For IntelliJ: 
        File -> New -> Project from Source Code -> Navigate to the folder where you unzipped the zip
        Select the project.                                             
 
 ## Tests
 There are several tests included that tests functional requirements and edge cases.  
 
 ### Card Test
 #### Core functionality tests covering
 - Create a Card and can create a Special Card
 - Assert that a Special card does not have a Suit
 - Face cards have a value of 20
 - Special Card has a value of Zero
 - Cannot create a card (or a special card) using a null rank nor a null suit.
 - Assert the right values (Ranks) are returned according to the card type.
 
  ### Hand Test
  #### Core functionality tests covering
  - Create an empty Hand and that hand is empty
  - Create a Hand with 5 Cards and only up to 5 cards.
  - Cannot create a hand with a Null
  - Can add a card to hand
  - Cannot add a null card to hand
  - Full hand (with 5 cards) cannot accept more cards
  - Can get the number of cards in a hand
  - When empty hand, can get the zero number of cards
  - Can calculate the total value of cards in a hand, regardless of cards' type
  - Can reset a hand
 
  ## Improvement
  One area of improvement is using an interface/abstract classes to allow further extensibility and separation of concerns.
  This depends on the type of application the model will be merged into. 
  
  For example, in case of merging this into a microservice, using Spring Boot something similar may provide better 
  separation of concerns, testability and resilience:
  
  ```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "CARD_TYPE",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Rank rank;

    public Card() {
    }

    public Card(Rank rank) {
        this.rank = rank;
    }
}
  ```

Then this can be extended by Card type, like so: 

  ```java
@Entity
@DiscriminatorValue(value = "FACE_CARD")
public class FaceCard extends Card {

    @Enumerated(value = EnumType.STRING)
    private Suit suit;

    public FaceCard() {
    }

    public FaceCard(Rank rank, Suit suit) {
        super(rank);
        this.suit = suit;
    }
}
  ```

In terms of Repository to commit into a database, like so would work for our Superclass of Card:

  ```java
@NoRepositoryBean
public interface CardRepository extends CrudRepository<Card, Long> {
}
  ```

For subclasses, such as Face Card or Special Card: 

  ```java
@Repository
public interface FaceCardRepository extends CardRepository {
}
  ```