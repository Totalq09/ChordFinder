package Model;

import java.util.ArrayList;
import java.util.List;

public class FretboardSet{

    public List<SingleSet> chordPosition;

    public FretboardSet(){

        chordPosition = new ArrayList<>(3);
    }
    public FretboardSet(int[][] set){

        chordPosition = new ArrayList<>(3);
        addNewSet(set);
    }

    public void addNewSet(int[][] set) {
        chordPosition.add(new SingleSet(set));
    }
}

