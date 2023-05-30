import java.util.ArrayList;
import java.util.Arrays;

public class EasyExam {

    public static void main(String[] args){
        int starting_position_for_searchig = 0;
        ArrayList<Integer> feelings = new ArrayList<>(Arrays.asList(-1,-1,0,0,1,1,-1,1,0,-1,1,0,1,1,-1,0,1,1));
        var neighbourVerifier = new NeighbourVerifier();
        var happyFeelingsInserter = new HappyFeelingsInserter();
        var sadFeelingsHunter = new SadFeelingsHunter();
        new HappyMaker(neighbourVerifier, happyFeelingsInserter, sadFeelingsHunter).beHappy(feelings);

        System.out.println(feelings);
    }
}
