import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeHappyIntegrationTest {
    @Mock
    private NeighbourVerifier neighbourVerifier;

    @Mock
    private HappyFeelingsInserter happyFeelingsInserter;

    @Mock
    private SadFeelingsHunter sadFeelingsHunter;

    @Test
    public void testBeHappy_with_sadFeelingsHunter() {
        var sadFeelingsHunterReal = new SadFeelingsHunter();
        var happyMaker = new HappyMaker(neighbourVerifier, happyFeelingsInserter, sadFeelingsHunterReal);
        when(neighbourVerifier.CheckNeighbours(anyList(), eq(1))).thenReturn(false);

        ArgumentCaptor<List<Integer>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        var argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);

        Mockito.doAnswer(invocation -> {
            List<Integer> data = invocation.getArgument(0);
            data.set(2, 1);
            data.add(0);
            return null;
        }).when(happyFeelingsInserter).InsertHappyFeelings(argumentCaptor.capture(), argumentCaptor2.capture());

        var initial = new ArrayList<>(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        assertEquals(initial, List.of(1, -1, 1, 0));

        verify(neighbourVerifier).CheckNeighbours(anyList(), eq(1));
        verify(happyFeelingsInserter).InsertHappyFeelings(argumentCaptor.capture(), argumentCaptor2.capture());
    }

    @Test
    public void testBeHappy_with_happyFeelingsInserter() {
        var happyFeelingsInserterReal = new HappyFeelingsInserter();
        var happyMaker = new HappyMaker(neighbourVerifier, happyFeelingsInserterReal, sadFeelingsHunter);
        when(neighbourVerifier.CheckNeighbours(anyList(), eq(1))).thenReturn(false);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(0))).thenReturn(1);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(2))).thenReturn(-1);

        var initial = new ArrayList<>(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        assertEquals(initial, List.of(1, -1, 1, 0));

        verify(neighbourVerifier).CheckNeighbours(anyList(), eq(1));
        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(0));
        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(2));
    }

    @Test
    public void testBeHappy_with_neighbourVerifier() {
        var neighbourVerifierReal = new NeighbourVerifier();
        var happyMaker = new HappyMaker(neighbourVerifierReal, happyFeelingsInserter, sadFeelingsHunter);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(0))).thenReturn(1);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(2))).thenReturn(-1);

        ArgumentCaptor<List<Integer>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        var argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);

        Mockito.doAnswer(invocation -> {
            List<Integer> data = invocation.getArgument(0);
            data.set(2, 1);
            data.add(0);
            return null;
        }).when(happyFeelingsInserter).InsertHappyFeelings(argumentCaptor.capture(), argumentCaptor2.capture());

        var initial = new ArrayList<>(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        assertEquals(initial, List.of(1, -1, 1, 0));

        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(0));
        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(2));
        verify(happyFeelingsInserter).InsertHappyFeelings(argumentCaptor.capture(), argumentCaptor2.capture());
    }
}
