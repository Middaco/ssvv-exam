import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Various black box tests for the SadFeelingsHunter.FindSadFeeling method.
 */
public class FindSadFeelingsBlackBoxTest {
    /**
     * Sad feeling is on first position.
     * Offset is zero.
     */
    @Test
    void findSadFeelingsFirstWithZeroOffset() {
        var hunter = new SadFeelingsHunter();
        var feelings = List.of(-1, -1, 0, 1, 1, -1);
        assertEquals(hunter.FindSadFeeling(feelings, 0), 0);
    }

    /**
     * Sad feeling is in a middle position.
     * Offset is zero.
     */
    @Test
    void findSadFeelingsMiddleWithZeroOffset() {
        var hunter = new SadFeelingsHunter();
        var feelings = List.of(0, 1, -1, -1, 1, 0);
        assertEquals(hunter.FindSadFeeling(feelings, 0), 2);
    }

    /**
     * Sad feeling is on the last position.
     * Offset is zero.
     */
    @Test
    void findSadFeelingsLastWithZeroOffset() {
        var hunter = new SadFeelingsHunter();
        var feelings = List.of(0, 0, 0, 1, 1, -1);
        assertEquals(hunter.FindSadFeeling(feelings, 0), 5);
    }

    /**
     * Sad feeling is in a middle position.
     * Offset is non-zero.
     */
    @Test
    void findSadFeelingsWithNonZeroOffset() {
        var hunter = new SadFeelingsHunter();
        var feelings = List.of(0, 0, -1, 1, 1, 1);
        assertEquals(hunter.FindSadFeeling(feelings, 1), 2);
    }

    /**
     * Sad feeling is on the first position.
     * Offset is non-zero.
     * Search should fail.
     */
    @Test
    void findNoSadFeelingsWithNonZeroOffset() {
        var hunter = new SadFeelingsHunter();
        var feelings = List.of(-1, 0, 0, 1, 1, 1);
        assertEquals(hunter.FindSadFeeling(feelings, 1), -1);
    }
}
