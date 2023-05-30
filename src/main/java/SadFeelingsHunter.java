import java.util.List;

public class SadFeelingsHunter {
    public int FindSadFeeling(List<Integer> feelings, int starting_position_for_searching) {
        boolean sad_feeling_found = false;
        int current_position;
        for (current_position = starting_position_for_searching;
             current_position < feelings.size();
             current_position += 1) {
            if (feelings.get(current_position) == -1) {
                sad_feeling_found = true;
                break;
            }
        }
        if (sad_feeling_found) {
            return current_position;
        }
        return -1;
    }
}