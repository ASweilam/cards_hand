package cardshands;

public enum Rank {

    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),

    ACE(20), JACK(20), QUEEN(20), KING(20);

    private final int rankValue;

    Rank(final int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValue() {
        return rankValue;
    }
}
