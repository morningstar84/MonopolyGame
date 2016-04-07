package eu.lucianocauzzi.monopolygame.model;

import java.util.HashMap;

import eu.lucianocauzzi.monopolygame.model.square.HouseSquare;
import eu.lucianocauzzi.monopolygame.model.square.Square;

/**
 * Created by lucio on 02/04/16.
 * Represents a board
 */
public class Board {

    private static final int INITIAL_POSITION = 0;
    private static final int SQUARE_STANDARD_NUMBER = 40;

    private int nSquare;


    // -- playerIndex, position
    private HashMap<Integer, Integer> playersPosition = new HashMap<>();
    private HashMap<Integer, Square> squareList = new HashMap<>();


    public Board(){
        nSquare = SQUARE_STANDARD_NUMBER;

        generateSquares();
    }

    public Board(int nSquare) {
        this.nSquare = nSquare;

        generateSquares();
    }

    /**
     * Generate squares: ad this time only one type square
     */
    private void generateSquares(){

        for (int i = 0; i < this.nSquare; i++) {
            Square square = new HouseSquare();
            squareList.put(i, square);
        }
    }

    /**
     * Return player's current position
     */
    public int getPlayerPosition(Player player){

        int playerIndex = player.getIndex();

        if (playersPosition.get(playerIndex) == null){

            return INITIAL_POSITION;
        }
        return playersPosition.get(playerIndex);

    }

    private void setPlayerPosition(Player player, int position){

        int playerIndex = player.getIndex();
        playersPosition.put(playerIndex, position);

    }

    /**
     * Return player's current position
     */
    public void movePlayer(Player player, int amount){
        int newPosition = (getPlayerPosition(player) + amount) % nSquare;

        setPlayerPosition(player, newPosition);
    }



}
