package eu.lucianocauzzi.monopolygame.model;

import java.util.Random;

/**
 * Created by lucio on 02/04/16.
 */
public class Dice {

    private static final int START_VALUE = 1;
    private static final int DEFAULT_FACES = 6;

    private int nFaces;

    public Dice() {
        this.nFaces = DEFAULT_FACES;
    }

    public Dice(int nFaces) {

        if (nFaces > 0) {
            this.nFaces = nFaces;

        }else{
            this.nFaces = DEFAULT_FACES;
        }
    }

    public int roll(){

        Random random = new Random();
        return random.nextInt(nFaces) + START_VALUE;

    }


}
