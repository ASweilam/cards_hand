package cardshands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/***
 * Card class that represents a card. It uses composition on Suit,Rank and Special Rank.
 * Constructing a card can happen using the constructor by passing Rank and Suit or by passing a SpecialRank
 * to the constructor to create a Joker card.
 *
 *
 */
public class Card {

    private static final Logger logger = LoggerFactory.getLogger(Card.class);

    private Suit suit;

    private Rank rank;

    private SpecialRank specialRank;

    //constructor for non-special cards.
    public Card(final Rank rank, final Suit suit) {
        if ((rank == null) || (suit == null)) {
            logger.error("An attempt to create a card with Null parameters");
            throw new IllegalArgumentException("Card suit or value cannot be null.");
        }
        this.rank = rank;
        this.suit = suit;
        logger.info("A new card created - {} of {} ", rank.getRankValue(), suit.getSuitText());
    }

    //Constructor for Special Cards
    public Card(final SpecialRank specialRank) {
        this.specialRank = Objects.requireNonNullElse(specialRank, SpecialRank.JOKER);
        logger.info("A new special card created - {} ", SpecialRank.JOKER);
    }

    /***
     * Find out the textual representation of a Suit.
     * @return a textual representation of the Suit.
     * @throws UnsupportedOperationException if the card is special card
     */
    public String getSuitText() {
        if (isSpecial()) {
            logger.error("An attempt to get a Suit of a special card");
            throw new UnsupportedOperationException("Special cards don't have a Suit");
        }
        return Objects.requireNonNull(suit).getSuitText();
    }

    /***
     * Find out the integer value of a rank. For example, joker rank has value of 0 and Queen has value of 20.
     * @return the rank integer value.
     */
    public int getRankValue() {
        return isSpecial() ? specialRank.getRankValue() : rank.getRankValue();
    }

    /***
     * Helper method to check if a card is special.
     * @return true if the card is Special card
     */
    private boolean isSpecial(){
        return (this.suit == null) && (this.specialRank != null);
    }

    public String toString() {
        if (isSpecial()) {
            return "Card: " + SpecialRank.JOKER;
        } else {
            return "Card: " + rank.getRankValue() + " of " + Objects.requireNonNull(suit).getSuitText();
        }
    }
}
