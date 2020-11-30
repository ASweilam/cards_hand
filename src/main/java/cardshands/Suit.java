package cardshands;

public enum Suit  {
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String suitText;

    Suit(final String suitText) {
        this.suitText = suitText;
    }


    public String getSuitText() {
        return suitText;
    }
}
