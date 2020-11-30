package cardshands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RankTest {

    @Test
    @DisplayName("Rank values - Success")
    void canGetRankValues() {

        final Rank ace = Rank.ACE;
        final Rank king = Rank.KING;
        final Rank two = Rank.TWO;
        final Rank five = Rank.FIVE;
        final Rank ten = Rank.TEN;

        assertAll(
                "asserting Rank values",
                ()-> assertEquals(ace.getRankValue(), 20, "should be 20"),
                ()-> assertEquals(king.getRankValue(), 20, "should be 20"),
                ()-> assertEquals(two.getRankValue(), 2, "should be 2"),
                ()-> assertEquals(five.getRankValue(), 5, "should be 5"),
                ()-> assertEquals(ten.getRankValue(), 10, "should be 10")
        );
    }

}