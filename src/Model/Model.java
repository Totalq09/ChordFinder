package Model;

import java.io.File;
import java.util.*;

/**
 * Created by piter on 2017-02-28.
 */
public class Model {

    private Map<Integer, FretboardSet> chords;

    public Model(){
        chords = new HashMap<Integer, FretboardSet>(20);

        addChords();
    }

    public FretboardSet getChord(String chord){
        int hashChord = getHash(chord);

        return chords.get(hashChord);
    }

    private int getHash(String string){
        int hash = 7;
        for (int i = 0; i < string.length(); i++) {
            hash = hash*31 + string.charAt(i);
        }

        return hash;
    }

    private void addChords(){

        File file;

        try{
            file = new File(getClass().getResource("/res/chords.txt").toURI());
            if(file == null)
                throw new Exception("Plik chords nie zostal wczytany");

            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){

                String[][] stringPattern = new String[6][];
                String chordName = scanner.nextLine();

                for(int i = 0; i < 6; i++) {
                    stringPattern[i] = scanner.nextLine().split(" ");
                }

                int[][] positions = new int[6][2];

                for(int i = 0; i < 6; i++){
                    for(int j = 1; j < 3; j++){
                        positions[i][j-1] = Integer.parseInt(stringPattern[i][j]);
                        if(positions[i][j-1] == -1 || positions[i][j-1] == 0)
                            break;
                    }
                }

                int chordHash = getHash(chordName);

                if(chords.containsKey(chordHash)){
                    chords.get(chordHash).addNewSet(positions);
                }
                else{
                    chords.put(chordHash, new FretboardSet(positions));
                }

                scanner.nextLine();
            }

        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
