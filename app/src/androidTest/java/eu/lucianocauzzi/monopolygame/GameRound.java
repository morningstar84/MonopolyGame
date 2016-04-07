package eu.lucianocauzzi.monopolygame;

import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.lucianocauzzi.monopolygame.model.Dice;
import eu.lucianocauzzi.monopolygame.model.Player;
import eu.lucianocauzzi.monopolygame.model.Round;

/**
 * Created by lucio on 03/04/16.
 */
@RunWith(AndroidJUnit4.class)
public class GameRound {

    public static final int MAX_ROUND_INSTANCE = 20;
    public static final int PLAYER_NUM = 5;
    public static final int EXCEDEED_ROUND_INSTANCE = 100;

    @Before
    public void setup() throws Exception{
        Round.initWithNumberOfInstances(MAX_ROUND_INSTANCE, PLAYER_NUM);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInstanceNumber() throws Exception{
        Round round = Round.getInstanceAtIndex(EXCEDEED_ROUND_INSTANCE);
    }

    @Test
    public void checkAllPlayerPlayedRound() throws Exception{

        Player.initWithNumberOfInstances(PLAYER_NUM);

        Round round = Round.getInstanceAtIndex(0);

        boolean isFinished = round.isRoundFinished();

        Assert.assertEquals("Error in checking if all has played", isFinished, false);

        for (int i = 0; i < PLAYER_NUM; i++){

            Player player = Player.getInstanceAtIndex(i);

            int dice = new Dice().roll();

            round.addPlayerScore(player.getIndex(), dice);
        }

        isFinished = round.isRoundFinished();

        Assert.assertEquals("Error in checking if all has played", isFinished, true);



    }
}
