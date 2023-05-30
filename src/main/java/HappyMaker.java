import java.util.List;

public class HappyMaker {

    private final NeighbourVerifier neighbourVerifier;
    private final HappyFeelingsInserter happyFeelingsInserter;
    private final SadFeelingsHunter sadFeelingsHunter;

    public HappyMaker(NeighbourVerifier neighbourVerifier, HappyFeelingsInserter happyFeelingsInserter, SadFeelingsHunter sadFeelingsHunter) {
        this.neighbourVerifier = neighbourVerifier;
        this.happyFeelingsInserter = happyFeelingsInserter;
        this.sadFeelingsHunter = sadFeelingsHunter;
    }

    public void beHappy(List<Integer> feelings) {
        int position_to_be_checked = sadFeelingsHunter.FindSadFeeling(feelings, 0);
        while (position_to_be_checked != -1) {
            if (!neighbourVerifier.CheckNeighbours(feelings, position_to_be_checked)) {
                happyFeelingsInserter.InsertHappyFeelings(feelings, position_to_be_checked);
            } else {
                position_to_be_checked += 1;
            }
            position_to_be_checked = sadFeelingsHunter.FindSadFeeling(feelings, position_to_be_checked);
        }
    }
}