package eu.lucianocauzzi.monopolygame;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import eu.lucianocauzzi.monopolygame.model.Game;
import eu.lucianocauzzi.monopolygame.model.Player;

/**
 * Created by lucio on 02/04/16.
 */
@RunWith(AndroidJUnit4.class)
public class GamePlayer {


    public static final int PLAYER_LOWER_BOUND = 1;
    public static final int PLAYER_UPPER_BOUND = 9;


    @Before
    public void setup() throws Exception{

        Player.initWithNumberOfInstances(2);
    }


    @Test
    public void verifyRandomExtraction() throws Exception{


        Player[] players = new Player[2];

        Player p = Player.getInstanceAtIndex(0);
        p.setIndex(0);
        p.setName("Horse");

        Player p1 = Player.getInstanceAtIndex(1);
        p1.setIndex(1);
        p1.setName("Car");

        players[0] =p;
        players[1] =p1;


        final int hundred = 100;

        boolean firstOrder = false;
        boolean secondOrder = false;

        for (int i = 0; i < hundred; i++){


            HashMap<Integer, Integer> randomOrder = Game.generatePlayerOrder(players);

            Assert.assertEquals("Order extraction failed", randomOrder.size(), 2);

            if (randomOrder.get(0) == 0 && randomOrder.get(1) == 1){
                firstOrder = true;
            }

            if (randomOrder.get(0) == 1 && randomOrder.get(1) == 0){
                secondOrder = true;
            }

        }

        boolean result = firstOrder && secondOrder;

        Assert.assertEquals("Failed to extract all orders", true, result);

        Log.d("tag", "tag");


    }

    @Test(expected = Exception.class)
    public void lowerPlayerNumber(){

        Player.initWithNumberOfInstances(PLAYER_LOWER_BOUND);
    }

    @Test(expected = Exception.class)
    public void upperPlayerNumber(){
        Player.initWithNumberOfInstances(PLAYER_UPPER_BOUND);
    }

}
