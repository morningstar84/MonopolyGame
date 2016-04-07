package eu.lucianocauzzi.monopolygame.model;

/**
 * Created by lucio on 02/04/16.
 */
public class Player {

    public static final int PLAYER_MIN = 2;
    private static final int PLAYER_MAX = 8;

    private static Player[] instances;
    private static int maxInstance;

    private int id;
    private String name;
    private int score = 0;
    private int playerIndex = -1;

    private Player(){

    }

    private Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void initWithNumberOfInstances(int number)
    {

        if (number < PLAYER_MIN || number > PLAYER_MAX){

            throw new IllegalArgumentException("Player: excedeed max instance number");

        }

        instances = new Player[number];
        maxInstance = number;
    }


    public static Player getInstanceAtIndex(int index) throws Exception
    {
        if (instances == null) {
            throw new Exception("Initialize number of instances first");
        }

        if (index < 0 || index >= maxInstance){

            throw new IllegalArgumentException("Excedeed max instance number");

        }

        if (instances[index] == null)
        {
            Player p = new Player();
            p.setName("Player " + index);
            p.setIndex(index);
            instances[index] = p;
        }

        return instances[index];
    }


    public static Player[] getAllInstances() throws Exception {

        for (int i = 0; i < maxInstance; i++){

            getInstanceAtIndex(i);
        }

        return instances;

    }

    public static void assignDefaultValues() throws Exception {
        for (int i = 0; i < maxInstance; i++){

            Player p = getInstanceAtIndex(i);
            p.setId(i);
            p.setName("Player " + i);

        }
    }

    public static int getPlayerNumber(){
        return maxInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIndex() {
        return playerIndex;
    }

    public void setIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }
}
