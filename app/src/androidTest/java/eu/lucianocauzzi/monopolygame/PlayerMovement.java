package eu.lucianocauzzi.monopolygame;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.lucianocauzzi.monopolygame.model.Board;
import eu.lucianocauzzi.monopolygame.model.Player;

/**
 * Created by lucio on 02/04/16.
 */
@RunWith(AndroidJUnit4.class)
public class PlayerMovement {

    private Player player;

    @Before
    public void setup() throws Exception{
        Player.initWithNumberOfInstances(2);
        player = Player.getInstanceAtIndex(0);
    }

    @Test
    public void movePlayer() throws Exception{


        Board board = new Board();

        final int desiredPosition = 7;

        board.movePlayer(player, desiredPosition);

        int positionOnBoard = board.getPlayerPosition(player);

        Assert.assertEquals("Errore calcolo posizione giocatore", positionOnBoard, desiredPosition);

    }

    @Test
    public void makeTourAroundBoard(){
        Board board = new Board();

        final int desiredPosition = 39;

        board.movePlayer(player, desiredPosition);

        final int diceRoll = 6;

        board.movePlayer(player, diceRoll);

        int positionOnBoard = board.getPlayerPosition(player);

        final int expectedPosition = 5;

        Assert.assertEquals("Errore calcolo posizione giocatore", positionOnBoard, expectedPosition);
    }
}
