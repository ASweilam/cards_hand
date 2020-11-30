package cardshands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final Logger logger = LoggerFactory.getLogger(Hand.class);
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
        logger.info("A new empty hand created.");
    }

    public Hand(final List<Card> cardList) {
        if (cardList == null) {
            logger.error("An attempt to construct a hand using a null.");
            throw new IllegalArgumentException("Can't create a hand with Null. Must be valid List of cards");
        }

        if (cardList.size() > 5) {
            logger.error("An attempt to create a hand with 5+ cards.");
            throw new IllegalStateException("Hand can only take up tp 5 cards at creation");
        }

        this.cards = new ArrayList<>(cardList);         //enforce ArrayList data structure + defensive strategy
        logger.info("A new hand created with {} cards.", cardList.size());

    }

    /***
     * Retrieve all the cards in a hand.
     * @return List of cards.
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards); // unmodifiable view of cards - defensive strategy
    }

    /***
     * Reset the hand by removing all cards from the hand.
     */
    public void reset() {
        cards.clear();
        logger.info("Hand reset.");
    }

    /***
     * Add a card to hand. Maximum number of cards a hand can have is five.
     * @param card The card to add.
     *
     * @throws IllegalArgumentException if card is null.
     * @throws IllegalStateException if hand has 5 cards.
     */
    public void add(final Card card) {
        if (card == null) {
            logger.error("An attempt to add a null to hand.");
            throw new IllegalArgumentException("Can't add a null to hand. Must be valid card");
        }

        if (cards.size() >= 5) {
            logger.error("An attempt to add more cards to hand than 5.");
            throw new IllegalStateException("Hand can only take up tp 5 cards");
        }

        this.cards.add(card);

        if (card.getRankValue() == 0) {
            logger.info("A new special card added to hand - {}", SpecialRank.JOKER);
        } else {
            logger.info("A new card added to hand - {} of {}", card.getRankValue(), card.getSuitText());
        }

    }

    /***
     * Get the total number of cards in the hand
     * @return integer number of cards in hand
     */
    public int getCardsAmount() {
        return this.cards.size();
    }

    /***
     * calculates the sum of cards' values in a hand at any time.
     * @return int Sum of card's values
     */
    public int getTotalCardsValue() {
        return cards.stream()
                .mapToInt(card -> card.getRankValue())
                .sum();
    }


}
