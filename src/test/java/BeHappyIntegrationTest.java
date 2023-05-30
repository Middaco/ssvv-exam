import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class BeHappyIntegrationTest {
    @Mock
    private NeighbourVerifier neighbourVerifier;
    @Mock
    private HappyFeelingsInserter happyFeelingsInserter;
    @Mock
    private SadFeelingsHunter sadFeelingsHunter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBeHappy_with_sadFeelingsHunter() {
        var sadFeelingsHunterReal = new SadFeelingsHunter();
        var happyMaker = new HappyMaker(neighbourVerifier, happyFeelingsInserter, sadFeelingsHunterReal);
        when(neighbourVerifier.CheckNeighbours(anyList(), eq(1))).thenReturn(false);

        ArgumentCaptor argumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);
        Mockito.doAnswer(invocation -> {
            List<Integer> data = invocation.getArgument(0);
            data.set(2, 1);
            data.add(0);
            return null;
        }).when(happyFeelingsInserter).InsertHappyFeelings((List<Integer>) argumentCaptor.capture(), (Integer) argumentCaptor2.capture());


        var initial = new ArrayList(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        Assert.assertEquals(initial, new ArrayList(List.of(1, -1, 1, 0)));

        verify(neighbourVerifier).CheckNeighbours(anyList(), eq(1));
        verify(happyFeelingsInserter).InsertHappyFeelings((List<Integer>) argumentCaptor.capture(), (Integer) argumentCaptor2.capture());
    }

    @Test
    public void testBeHappy_with_happyFeelingsInserter() {
        var happyFeelingsInserterReal = new HappyFeelingsInserter();
        var happyMaker = new HappyMaker(neighbourVerifier, happyFeelingsInserterReal, sadFeelingsHunter);
        when(neighbourVerifier.CheckNeighbours(anyList(), eq(1))).thenReturn(false);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(0))).thenReturn(1);
        when(sadFeelingsHunter.FindSadFeeling(anyList(), eq(2))).thenReturn(-1);

        var initial = new ArrayList(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        Assert.assertEquals(initial, new ArrayList(List.of(1, -1, 1, 0)));

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

        ArgumentCaptor argumentCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor argumentCaptor2 = ArgumentCaptor.forClass(Integer.class);
        Mockito.doAnswer(invocation -> {
            List<Integer> data = invocation.getArgument(0);
            data.set(2, 1);
            data.add(0);
            return null;
        }).when(happyFeelingsInserter).InsertHappyFeelings((List<Integer>) argumentCaptor.capture(), (Integer) argumentCaptor2.capture());


        var initial = new ArrayList(List.of(1, -1, 0));
        happyMaker.beHappy(initial);
        Assert.assertEquals(initial, new ArrayList(List.of(1, -1, 1, 0)));

        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(0));
        verify(sadFeelingsHunter).FindSadFeeling(anyList(), eq(2));
        verify(happyFeelingsInserter).InsertHappyFeelings((List<Integer>) argumentCaptor.capture(), (Integer) argumentCaptor2.capture());
    }
}
