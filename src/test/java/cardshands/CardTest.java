package cardshands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    @DisplayName("Create Card - Success")
    void canCreateNonSpecialCards() {

        Card sevenOfClubs = new Card(Rank.SEVEN, Suit.CLUBS);
        Card queenOfHearts = new Card(Rank.QUEEN, Suit.HEARTS);

        assertAll("asserting success case of card creation",
                () -> assertEquals(sevenOfClubs.getRankValue(), Rank.SEVEN.getRankValue(), "should be rank of 7 value"),
                () -> assertEquals(sevenOfClubs.getSuitText(), Suit.CLUBS.getSuitText() , "should be Clubs"),

                () -> assertEquals(queenOfHearts.getRankValue(), Rank.QUEEN.getRankValue(), "should be rank of Queen Value"),
                () -> assertEquals(queenOfHearts.getSuitText(), Suit.HEARTS.getSuitText(), "should be Hearts")
        );
    }

    @Test
    @DisplayName("Create Special Card - Success")
    void canCreateSpecialCard() {

        Card jokerCard = new Card(SpecialRank.JOKER);

        assertEquals(jokerCard.getRankValue(), SpecialRank.JOKER.getRankValue(), "should be value of Joker");
    }

    @Test
    @DisplayName("Special Card shouldn't have a Suit - Success")
    void specialCardDoesNotHaveSuit() {
        Card jokerCard = new Card(SpecialRank.JOKER);

        final UnsupportedOperationException unsupportedOperationEx =
                assertThrows(UnsupportedOperationException.class,
                        () -> jokerCard.getSuitText(), "should throw an exception");

        assertEquals(unsupportedOperationEx.getMessage(), "Special cards don't have a Suit", "should have the same message");
    }


    @Test
    @DisplayName("face cards have value of 20 - Success")
    void faceCardsHasValueOf20() {
        Card queenOfSpades = new Card(Rank.QUEEN, Suit.SPADES);
        Card jackOfDiamonds = new Card(Rank.JACK, Suit.DIAMONDS);
        Card kingOfClubs = new Card(Rank.KING, Suit.CLUBS);
        Card aceOfHearts = new Card(Rank.ACE, Suit.HEARTS);

        assertAll(
                "Asserting face cards value",
                () -> assertEquals(queenOfSpades.getRankValue(), 20, "should be value of 20"),
                () -> assertEquals(jackOfDiamonds.getRankValue(), 20, "should be value of 20"),
                () -> assertEquals(kingOfClubs.getRankValue(), 20, "should be value of 20"),
                () -> assertEquals(aceOfHearts.getRankValue(), 20, "should be value of 20")
        );
    }

    @Test
    @DisplayName("Joker has value of 0 - Success")
    void jokerHasValueOfZero() {

        Card jokerCard = new Card(SpecialRank.JOKER);

        assertEquals(jokerCard.getRankValue(), 0, "should be 0");
    }

    @Test
    @DisplayName("Card can't be instantiated by null rank and suit - Success")
    void forbiddenToCreateACardWithBothParametersAsNull() {

        final IllegalArgumentException argumentException =
                assertThrows(IllegalArgumentException.class,
                        () -> new Card(null, null),
                        "Rank and Suit when Null throws an exception");

        assertEquals(argumentException.getMessage(), "Card suit or value cannot be null.");
    }

    @Test
    @DisplayName("Card can't be instantiated by null rank - Success")
    void forbiddenToCreateACardWithNullRank() {

        final IllegalArgumentException argumentException =
                assertThrows(IllegalArgumentException.class,
                        () -> new Card(null, Suit.SPADES),
                        "Rank and Suit when Null throws an exception");

        assertEquals(argumentException.getMessage(), "Card suit or value cannot be null.");
    }

    @Test
    @DisplayName("Card can't be instantiated by null suit - Success")
    void forbiddenToCreateACardWithNullSuit() {

        final IllegalArgumentException argumentException =
                assertThrows(IllegalArgumentException.class,
                        () -> new Card(Rank.FOUR, null),
                        "Rank and Suit when Null throws an exception");

        assertEquals(argumentException.getMessage(), "Card suit or value cannot be null.");
    }

    @Test
    @DisplayName("Special card instantiated by null rank creates a Joker - Success")
    void whenCreateASpecialCardWithNullRankShouldCreateAJokerByDefault() {

        Card jokerCard = new Card(null);

        assertEquals(jokerCard.getRankValue(),0, "should be 0");
    }


    /**
     * Testing the getRankValue(). The method should detect if the card is special and call the right
     * method for special card. If the card is not special card, it should not call the special card methods to get the rank.
     */
    @Test
    @DisplayName("special and non-special cards have different ranks - Success")
    void getRankValueShouldGetTheCorrectValueDependingOnTheTypeOfCard() {

        Card faceCard = new Card(Rank.KING, Suit.CLUBS);
        Card valueCard = new Card(Rank.FOUR, Suit.DIAMONDS);
        Card jokerCard = new Card(SpecialRank.JOKER);

        assertAll( "getting the appropriate value per card type",
                () -> assertEquals(faceCard.getRankValue(), 20, "should be 20"),
                () -> assertEquals(valueCard.getRankValue(), 4, "should be 4"),
                () -> assertEquals(jokerCard.getRankValue(), 0, "should be 0")
        );

    }
}