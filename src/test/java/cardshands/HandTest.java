package cardshands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    @DisplayName("New hand should be empty - Success")
    void aNewHandShouldBeAnEmptyHand() {

        Hand hand = new Hand();

        assertEquals(hand.getCards(), Collections.emptyList(), "should be empty");
    }

    @Test
    @DisplayName("Construct hand with cards - Success")
    void canCreateHandWithCards() {

        final List<Card> cardsToAdd =
                Arrays.asList(new Card(SpecialRank.JOKER), new Card(Rank.QUEEN, Suit.HEARTS));

        Hand hand = new Hand(cardsToAdd);

        assertEquals(hand.getCards().size(), cardsToAdd.size(), "should be 2");
    }

    @Test
    @DisplayName("Can't create hand with Null - Success")
    void aNewHandShouldNotGetConstructedUsingANull() {

        final IllegalArgumentException nullArgHandCreationException = assertThrows(
                IllegalArgumentException.class,
                () -> new Hand(null),
                "should throw exception");

        assertEquals(nullArgHandCreationException.getMessage(), "Can't create a hand with Null. Must be valid List of cards");
    }

    @Test
    @DisplayName("add single card to hand - Success")
    void canAddASingleCardToHand() {
        Card valueCard = new Card(Rank.FIVE, Suit.HEARTS);
        Card faceCard = new Card(Rank.QUEEN, Suit.HEARTS);
        Card jokerCard = new Card(SpecialRank.JOKER);

        Hand valueCardHand = new Hand(Collections.singletonList(valueCard));
        Hand faceCardHand = new Hand(Collections.singletonList(faceCard));
        Hand jokerCardHand = new Hand(Collections.singletonList(jokerCard));

        assertAll(
          "Adding a single card to an empty hand",
                ()-> assertEquals(valueCardHand.getCards(), Collections.singletonList(valueCard), "should have single value card"),
                ()-> assertEquals(faceCardHand.getCards(), Collections.singletonList(faceCard), "should have single face card"),
                ()-> assertEquals(jokerCardHand.getCards(), Collections.singletonList(jokerCard), "should have single joker card")
        );
    }

    @Test
    @DisplayName("Can't add a null to hand - Success")
    void cannotAddANullToACard() {

        Hand hand = new Hand();

        final IllegalArgumentException nullArgAtHandCreationException = assertThrows(
                IllegalArgumentException.class,
                () -> hand.add(null),
                "should throw exception");

        assertEquals(nullArgAtHandCreationException.getMessage(), "Can't add a null to hand. Must be valid card");
    }

    @Test
    @DisplayName("new Hand has no cards - Success")
    void aNewHandShouldHaveZeroCards() {

        Hand emptyHand = new Hand();

        assertEquals(emptyHand.getCardsAmount(), 0, "A new hand should be an empty hand");
    }

    @Test
    @DisplayName("Should not construct a hand with 5+ - Success")
    void cannotCreateAHandWithMoreThan5Cards() {
        final List<Card> listOf6cards =
                Arrays.asList(new Card(SpecialRank.JOKER), new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.CLUBS)
                        , new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES), new Card(Rank.ACE, Suit.CLUBS));

        final IllegalStateException createHandWith6CardsException = assertThrows(
                IllegalStateException.class,
                () -> new Hand(listOf6cards),
                "should throw exception");

        assertEquals(createHandWith6CardsException.getMessage(), "Hand can only take up tp 5 cards at creation");
    }

    @Test
    @DisplayName("Full hand doesn't accept more cards - Success")
    void cannotAddACardToAFullHand() {
        final List<Card> listOf5cards =
                Arrays.asList(new Card(SpecialRank.JOKER), new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.CLUBS)
                        , new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES));

        Hand hand = new Hand(listOf5cards);

        final IllegalStateException addCardToFullHandException = assertThrows(
                IllegalStateException.class,
                () -> hand.add(new Card(SpecialRank.JOKER)),
                "should throw exception");

        assertEquals(addCardToFullHandException.getMessage(), "Hand can only take up tp 5 cards");
    }

    @Test
    @DisplayName("calculate number of cards in hand - Success")
    void canCalculateTheNumberOfCardsInHand() {
        final List<Card> listOf5cards =
                Arrays.asList(new Card(SpecialRank.JOKER), new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.CLUBS)
                        , new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES));

        Hand hand = new Hand(listOf5cards);

        assertEquals(hand.getCardsAmount(), 5, "should be 5 cards");
    }

    @Test
    @DisplayName("get total value of cards in hand - Success")
    void canGetTheTotalValueOfCardsInHand_Joker() {
        Hand handWithOneJoker = new Hand();
        handWithOneJoker.add(new Card(SpecialRank.JOKER));

        Hand handWith5Jokers =
                new Hand(Collections.nCopies(5, new Card(SpecialRank.JOKER)));

        assertAll(
                "Counting cards total value - Joker case",
                () -> assertEquals(handWithOneJoker.getTotalCardsValue(), 0, "should be zero" ),
                () -> assertEquals(handWith5Jokers.getTotalCardsValue(), 0, "should be zero" )
        );
    }

    @Test
    @DisplayName("calculate value of face cards - Success")
    void canGetTheTotalValueOfCardsInHand_FaceCard() {

        Hand handWithOneQueen = new Hand();
        handWithOneQueen.add(new Card(Rank.QUEEN,Suit.HEARTS));

        Hand handWith5Queens =
                new Hand(Collections.nCopies(5, new Card(Rank.QUEEN,Suit.HEARTS) ));

        assertAll(
                "Counting cards total value - Joker case",
                () -> assertEquals(handWithOneQueen.getTotalCardsValue(), 20, "should be 20" ),
                () -> assertEquals(handWith5Queens.getTotalCardsValue(), 100, "should be 100" )
        );
    }

    @Test
    @DisplayName("calculate value of value cards - Success")
    void canGetTheTotalValueOfCardsInHand_ValueCard() {

        Hand handWithOneSeven = new Hand();
        handWithOneSeven.add(new Card(Rank.SEVEN,Suit.HEARTS));

        Hand handWith5Sevens =
                new Hand(Collections.nCopies(5, new Card(Rank.SEVEN,Suit.HEARTS) ));

        assertAll(
                "Counting cards total value - Joker case",
                () -> assertEquals(handWithOneSeven.getTotalCardsValue(), 7, "should be 7" ),
                () -> assertEquals(handWith5Sevens.getTotalCardsValue(), 35, "should be 35" )
        );
    }

    @Test
    @DisplayName("empty hand has empty value - Success")
    void canGetTheTotalValueOfCards_EmptyHand() {

        Hand hand = new Hand();

        assertEquals(hand.getTotalCardsValue(), 0, "should be 0");
    }

    @Test
    @DisplayName("Reset a full hand - Success")
    void canRestAFullHand() {
        final List<Card> listOf5cards =
                Arrays.asList(new Card(SpecialRank.JOKER), new Card(Rank.QUEEN, Suit.HEARTS), new Card(Rank.JACK, Suit.CLUBS)
                        , new Card(Rank.NINE, Suit.CLUBS), new Card(Rank.THREE, Suit.SPADES));

        Hand hand = new Hand(listOf5cards);
        hand.reset();

        assertEquals(hand.getCards(), Collections.emptyList(), "should be an empty");
    }

    @Test
    @DisplayName("Reset an empty hand - Success")
    void canRestEmptyHand() {

        Hand hand = new Hand();
        hand.reset();

        assertEquals(hand.getCards(), Collections.emptyList(), "should be an empty");
    }
}