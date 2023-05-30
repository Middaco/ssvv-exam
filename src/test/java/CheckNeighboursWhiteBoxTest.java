import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckNeighboursWhiteBoxTest {
    /**
     * Checks if we can jump over the first feeling
     * First feeling is positive
     */
    @Test
    void checkNeighboursOfNonNegativeFeeling(){
        var checker = new NeighbourVerifier();
        var feelings = List.of(1,1,1);
        assertTrue(checker.CheckNeighbours(feelings, 0));
    }

    /**
     * Checks neighbors of first feeling
     * First feeling is negative
     */
    @Test
    void checkNeighboursOfNegativeFirstFeeling(){
        var checker = new NeighbourVerifier();
        var feelings = List.of(-1,0,0);
        assertFalse(checker.CheckNeighbours(feelings, 0));
    }

    /**
     * Checks neighbors of middle feeling
     * Middle feeling is negative
     */
    @Test
    void checkNeighboursOfNegativeMiddleFeeling(){
        var checker = new NeighbourVerifier();
        var feelings = List.of(0,-1,0);
        assertFalse(checker.CheckNeighbours(feelings, 1));
    }

    /**
     * Checks neighbors of final feeling
     * Final feeling is negative
     */
    @Test
    void checkNeighboursOfNegativeFinalFeeling(){
        var checker = new NeighbourVerifier();
        var feelings = List.of(0,0,-1);
        assertFalse(checker.CheckNeighbours(feelings, 2));
    }

    /**
     * Checks neighbors of middle feeling
     * Middle feeling is negative
     */
    @Test
    void checkPositiveNeighboursOfNegativeMiddleFeeling(){
        var checker = new NeighbourVerifier();
        var feelings = List.of(1,-1,1);
        assertTrue(checker.CheckNeighbours(feelings, 1));
    }
}
