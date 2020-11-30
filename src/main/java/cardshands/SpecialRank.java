package cardshands;

public enum SpecialRank {
    JOKER(0);

    private final int rankValue;

    SpecialRank(int rankValue) {
        this.rankValue = rankValue;
    }

    public int getRankValue() {
        return rankValue;
    }
}
