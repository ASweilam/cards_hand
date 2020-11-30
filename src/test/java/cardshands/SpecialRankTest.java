package cardshands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialRankTest {
    @Test
    @DisplayName("Special Rank value - Success")
    void canGetSpecialRankValue() {

        final SpecialRank specialRankValue = SpecialRank.JOKER;

        assertEquals(specialRankValue.getRankValue(), 0, "should be zero");

    }

}