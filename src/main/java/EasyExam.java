import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EasyExam {
    public static int FindSadFeeling(List<Integer> feelings, int  starting_position_for_searching){
        Boolean sad_feeling_found = false;
        int current_position;
        for(current_position = starting_position_for_searching;
            current_position < feelings.size();
            current_position += 1){
            if(feelings.get(current_position) == -1){
                sad_feeling_found = true;
                break;
            }
        }
        if(sad_feeling_found){
            return current_position;
        }
        return -1;
    }

    //returns true if we can jump over the current feeling
    //we can jump over the current feeling if the current feeling is different from -1 or
    //equal to -1 but surrounded by 1
    public static boolean CheckNeighbours(List<Integer> feelings,int position_to_be_checked){
        if((Integer)feelings.toArray()[position_to_be_checked] != -1){
            return true;
        }
        if(position_to_be_checked != 0){
            if(feelings.get(position_to_be_checked - 1) != 1){
                return false;
            }
        }
        if(position_to_be_checked != feelings.size()-1){
            if(feelings.get(position_to_be_checked + 1) != 1){
                return false;
            }
        }
        return true;
    }

    public static void InsertHappyFeelings(List<Integer> feelings, int position_to_insert_happy_feelings){
        if(feelings.get(position_to_insert_happy_feelings) == -1){
            if(position_to_insert_happy_feelings != feelings.size() - 1){
                feelings.add(position_to_insert_happy_feelings + 1, 1);
            }else if(feelings.get(position_to_insert_happy_feelings + 1) != 1){
                feelings.add(position_to_insert_happy_feelings + 1, 1);
            }
            if(position_to_insert_happy_feelings == 0){
                feelings.add(position_to_insert_happy_feelings, 1);
            } else if(feelings.get(position_to_insert_happy_feelings - 1) != 1){
                feelings.add(position_to_insert_happy_feelings, 1);
            }
        }
    }

    public static void beHappy(List<Integer> feelings){
        int position_to_be_checked = FindSadFeeling(feelings,  0);
        while(position_to_be_checked != -1){
            if(!CheckNeighbours(feelings, position_to_be_checked)){
                InsertHappyFeelings(feelings, position_to_be_checked);
            }else{
                position_to_be_checked += 1;
            }
            position_to_be_checked = FindSadFeeling(feelings,  position_to_be_checked);
        }
    }

    public static void main(String[] args){
        int starting_position_for_searchig = 0;
        ArrayList<Integer> feelings = new ArrayList<>(Arrays.asList(-1,-1,0,0,1,1,-1,1,0,-1,1,0,1,1,-1,0,1,1));

        beHappy(feelings);

        feelings.forEach(System.out::println);
    }
}
