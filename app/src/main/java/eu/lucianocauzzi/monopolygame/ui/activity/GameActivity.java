package eu.lucianocauzzi.monopolygame.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import eu.lucianocauzzi.monopolygame.R;
import eu.lucianocauzzi.monopolygame.model.Game;
import eu.lucianocauzzi.monopolygame.model.Player;
import eu.lucianocauzzi.monopolygame.util.AlertHelper;

public class GameActivity extends AppCompatActivity implements DialogInterface.OnClickListener{

    public static final String BUNDLE_PARAM = "bundle_param";
    private static final String TAG = GameActivity.class.getSimpleName();

    private Context mContext;
    private Toolbar mToolbar;

    private int mPlayerNumber;

    private Game mGame;


    // -- GUI
    private TextView mPlayerNumberTv;
    private TextView mTurnTv;
    private TextView mLastRoundScores;

    private TextView mBoardTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mContext = GameActivity.this;
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();

        Intent intent = GameActivity.this.getIntent();

        if (intent.getExtras() != null) {

            mPlayerNumber = intent.getExtras().getInt(BUNDLE_PARAM, -1);


            if (mPlayerNumber == -1){

                Log.e(TAG, "Error in bundle parameter");
                GameActivity.this.finish();
                Toast.makeText(mContext, "Errore acquisizione dei player", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                mGame = Game.newInstance(mPlayerNumber);

            }catch (Exception e){

                Log.e(TAG, "Error while creating game");
                GameActivity.this.finish();
                Toast.makeText(mContext, "Errore creazione del gioco", Toast.LENGTH_SHORT).show();
                return;
            }

            init();



        }



    }

    private void setupToolbar(){

        if (mToolbar != null) {
            mToolbar.setTitle("Partita");

            setSupportActionBar(mToolbar);

            ActionBar actionBar = getSupportActionBar();

            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.action_play:

                Log.d(TAG, "action_play");
                play();

                break;

            case R.id.home:

                GameActivity.this.finish();
                break;
        }

        return true;
    }

    private void init(){

        mPlayerNumberTv = (TextView) findViewById(R.id.player_number);
        mTurnTv = (TextView) findViewById(R.id.turn_number);
        mLastRoundScores = (TextView) findViewById(R.id.last_round_scores);

        mBoardTv = (TextView) findViewById(R.id.board);


        displayPlayerNumber();
        displayRoundNumber(mGame.getCurrentRound());
        displayLastRoundScores(null);
        displayPlayerPosition();
    }

    private void play(){
        try {

            Integer[] scores = mGame.play();

            if (scores == null){
                AlertHelper.alertGameFinished(mContext, GameActivity.this);

                return;
            }

            int currentRound = mGame.getCurrentRound();
            displayRoundNumber(currentRound);

            displayLastRoundScores(scores);
            displayPlayerPosition();

        }catch (Exception e){
            Log.e(TAG, "Error plying game");
        }
    }


    private void displayPlayerNumber(){

        String str = "Numero di giocatori: " + mPlayerNumber;
        mPlayerNumberTv.setText(str);

    }

    private void displayRoundNumber(int roundNumber){

        String str = "Round: " + roundNumber;
        mTurnTv.setText(str);

    }

    /**
     * Display players' last round scores
     * @param scores score results array
     */
    public void displayLastRoundScores(Integer[] scores){

        if (scores == null){
            mLastRoundScores.setText("Lancio dadi ultimo round:\n" +
                    "\nNessun risultato presente.");
            return;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Lancio dadi ultimo round:\n\n");

        for (int i = 0; i < mPlayerNumber; i++){
            try {

                Player p = Player.getInstanceAtIndex(i);
                int playerScore = scores[i];

                Log.e(TAG, "[SCORE]: " + p.getName() + " = " + playerScore);

                String value = p.getName() + " -> " + playerScore + "\n";
                builder.append(value);

            }catch (Exception e){
                Log.e(TAG, "Unable to publish score for player");
            }
        }


        mLastRoundScores.setText(builder.toString());

    }

    /**
     * Represents player position on board
     */
    public void displayPlayerPosition(){

        StringBuilder builder = new StringBuilder();
        builder.append("Posizione giocatori:\n\n");

        try {

            for (Player p : Player.getAllInstances()) {

                String result = "- " +p.getName() + ": " + mGame.getPlayerPositionOnBoard(p) + "\n";
                builder.append(result);

            }


        }catch (Exception e){
            Log.e(TAG, "Error while updating player position on board");
        }

        mBoardTv.setText(builder.toString());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d(TAG, "Dialog clicked");

        Toast.makeText(GameActivity.this, "Gioco terminato", Toast.LENGTH_SHORT).show();
        GameActivity.this.finish();
    }

}
