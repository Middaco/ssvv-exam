import java.util.List;

public class HappyFeelingsInserter {
    public void InsertHappyFeelings(List<Integer> feelings, int position_to_insert_happy_feelings) {
        if (feelings.get(position_to_insert_happy_feelings) == -1) {
            if (position_to_insert_happy_feelings != feelings.size() - 1) {
                feelings.add(position_to_insert_happy_feelings + 1, 1);
            } else if (feelings.get(position_to_insert_happy_feelings + 1) != 1) {
                feelings.add(position_to_insert_happy_feelings + 1, 1);
            }
            if (position_to_insert_happy_feelings == 0) {
                feelings.add(position_to_insert_happy_feelings, 1);
            } else if (feelings.get(position_to_insert_happy_feelings - 1) != 1) {
                feelings.add(position_to_insert_happy_feelings, 1);
            }
        }
    }
}