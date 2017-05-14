package Model;

/**
 * Created by piter on 2017-02-28.
 */
public class SingleSet{
    private int set[][];

    public SingleSet(){
        set = new int[6][2];
    }

    public SingleSet(int[][] set){
        this.set = new int[6][2];
        this.set = set.clone();
    }

    public int[][] getSet(){
        return set.clone();
    }
}
