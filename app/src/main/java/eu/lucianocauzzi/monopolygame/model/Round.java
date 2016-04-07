package eu.lucianocauzzi.monopolygame.model;

import android.util.Log;

/**
 * Created by lucio on 02/04/16.
 */
public class Round {

    public static final String TAG = Round.class.getSimpleName();
    public static final int DEFAULT_ROUND_NUMBER = 20;

    private static Round[] instances;
    private static int maxInstance;
    private static int maxPlayer;

    private int id;

    // -- playerIndex, score
    private Integer[] playerScores = null;

    private Round(){

    }

    public static void initWithNumberOfInstances(int roundNumber, int players)
    {
        instances = new Round[roundNumber];
        maxInstance = roundNumber;
        maxPlayer = players;
    }

    public static Round getInstanceAtIndex(int index) throws Exception
    {
        if (instances == null) {
            throw new Exception("Initialize number of instances first");
        }

        if (index < 0 || index >= maxInstance){

            throw new IllegalArgumentException("Excedeed max instance number");

        }

        if (instances[index] == null)
        {
            instances[index] = new Round();
        }

        return instances[index];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    public void addPlayerScore(Integer playerIndex, Integer score){

        if (playerScores == null){
            playerScores = new Integer[maxPlayer];
        }

        playerScores[playerIndex] = score;
    }

    public Integer[] getAllPlayerScore(){
        return playerScores;
    }

    public boolean hasPlayerPlayedRound(int playerIndex){

        return playerScores[playerIndex] != null;

    }

    public boolean isRoundFinished(){

        if (playerScores == null){
            return false;
        }

        return playerScores.length == maxPlayer;
    }

    public static boolean isGameFinished(int value){

        boolean result = maxInstance <= value;

        Log.d(TAG, "MaxInstance = " + maxInstance + ", currentRound = " + value + " -> Game finished = " + result);

        return result;
    }
}
