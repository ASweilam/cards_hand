package cardshands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuitTest {

    @Test
    @DisplayName("Suit text - Success")
    void canGetSuitText() {

        final Suit hearts = Suit.HEARTS;
        final Suit clubs = Suit.CLUBS;
        final Suit spades = Suit.SPADES;
        final Suit diamonds = Suit.DIAMONDS;

        assertAll(
                "asserting Suit's text",
                ()-> assertEquals(hearts.getSuitText(), "Hearts", "should be Hearts"),
                ()-> assertEquals(clubs.getSuitText(), "Clubs", "should be Clubs"),
                ()-> assertEquals(spades.getSuitText(), "Spades", "should be Spades"),
                ()-> assertEquals(diamonds.getSuitText(), "Diamonds", "should be Diamonds")
        );
    }

}