import java.util.List;

public class NeighbourVerifier {

    //returns true if we can jump over the current feeling

    //we can jump over the current feeling if the current feeling is different from -1 or
    //equal to -1 but surrounded by 1
    public boolean CheckNeighbours(List<Integer> feelings, int position_to_be_checked) {
        if ((Integer) feelings.toArray()[position_to_be_checked] != -1) {
            return true;
        }
        if (position_to_be_checked != 0) {
            if (feelings.get(position_to_be_checked - 1) != 1) {
                return false;
            }
        }
        if (position_to_be_checked != feelings.size() - 1) {
            return feelings.get(position_to_be_checked + 1) == 1;
        }
        return true;
    }
}