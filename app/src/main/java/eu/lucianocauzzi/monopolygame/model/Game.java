package eu.lucianocauzzi.monopolygame.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by lucio on 02/04/16.
 */
public class Game {

    private static final String TAG = Game.class.getSimpleName();

    private int playerNumber = Player.PLAYER_MIN;

    // Hasmap<Turn, playerIndex>
    private HashMap<Integer, Integer> mPlayerOrderTurns;

    private Board mBoard = new Board();

    private int currentRound = 0;

    public static Game newInstance(int aPlayerNumber) throws Exception{
        Game game = new Game();
        game.initPlayer(aPlayerNumber);
        return game;
    }


    private void initPlayer(int aPlayerNumber) throws Exception{

        playerNumber = aPlayerNumber;
        Player.initWithNumberOfInstances(aPlayerNumber);
        mPlayerOrderTurns = generatePlayerOrder(Player.getAllInstances());
        Round.initWithNumberOfInstances(Round.DEFAULT_ROUND_NUMBER, playerNumber);
    }




    public Integer[] playRound() throws Exception{

        Round round = Round.getInstanceAtIndex(currentRound);

        int size = mPlayerOrderTurns.size();

        for (int i = 0; i < size; i++){

            Player p = Player.getInstanceAtIndex(mPlayerOrderTurns.get(i));
            Dice dice = new Dice();
            int move = dice.roll();
            mBoard.movePlayer(p, move);
            round.addPlayerScore(p.getIndex(), move);
            Log.d(TAG, "[ROLL DICE]: " +  p.getName() + " -> " + move);
        }

        Integer[] result = round.getAllPlayerScore();

        currentRound++;

        return result;
    }

    public Integer[] play() throws Exception{

        if (!Round.isGameFinished(currentRound)){
            return playRound();

        }else{
            return null;
        }
    }


    /**
     *
     * @param players
     * @return position, playerIndex
     */
    public static HashMap<Integer, Integer> generatePlayerOrder(Player[] players){

        HashMap<Integer, Integer> result = new HashMap<>();
        ArrayList<Integer> assignedPosition = new ArrayList<>();

        int size = players.length;

        for (int i = 0; i < size; i++){

            boolean voidPositionFound = false;

            Player p = players[i];

            do {
                Random random = new Random();
                int extracted = random.nextInt(size);

                if (!assignedPosition.contains(extracted)){
                    assignedPosition.add(extracted);
                    result.put(extracted, p.getIndex());
                    voidPositionFound = true;
                }

            }while (!voidPositionFound);

        }

        return result;
    }

    public int getCurrentRound(){
        return currentRound;
    }

    public int getPlayerPositionOnBoard(Player player){
        return mBoard.getPlayerPosition(player);


    }

}
