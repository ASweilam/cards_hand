# Cards with a hand

This application models a card in the standard 52-card deck alongside a hand that uses the card(s).


Table of contents
=================

<!--ts-->
   * [Specs](#specs)
     * [Card](#card)
     * [Hand](#hand)
   * [Requirements](#requirements)
   * [Libraries](#libraries)
   * [Running the application locally](#running-the-application-locally)
   * [Tests](#tests)                  
<!--te-->

## Specs

| Object                       | purpose                                  | Remarks
| ----------------------------- | ---------------------------------------- | ----------------------------------------
| Card  | Represents a card | A special card exists
| Hand | Represents a hand | has some functionality
| Rank and Suit| Values wrappers | restricts arguments per card type (special/Non-Special)

### Card

•	Every card has a suit (except the Joker), which can be clubs, diamonds, hearts and spades.
•	A card can be either a card with a number on it between 2 and 10, I'll call this a value card, or it can be one of the face cards: Jack, Queen, King or Ace.
•	There is a special card, the Joker, which does not have a suit.
•	Face cards have value of 20.
•	Joker has a value of 0.

### Hand

•	Should allow a card to be added to the hand.
•	Should allow a maximum of 5 cards to be added to a hand.
•	Should be able to calculate the total amount of cards in the hand.
•	Should be able to calculate the total value of cards in the hand.
•	Should allow the hand to be reset.

  
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
 