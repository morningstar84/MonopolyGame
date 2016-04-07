package eu.lucianocauzzi.monopolygame.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eu.lucianocauzzi.monopolygame.R;
import eu.lucianocauzzi.monopolygame.model.Player;
import eu.lucianocauzzi.monopolygame.util.AlertHelper;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;

    private EditText mPlayerNumberEditext;
    private Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;

        mPlayerNumberEditext = (EditText) findViewById(R.id.player_edittext);
        mConfirmButton = (Button) findViewById(R.id.button_confirm);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAndConfirmPlayerNumber();
            }
        });

    }


    public void checkAndConfirmPlayerNumber(){


        int number;

        try {
            number = Integer.valueOf(mPlayerNumberEditext.getText().toString());
            Player.initWithNumberOfInstances(number);
            Player.assignDefaultValues();

        }catch (Exception e){

            Log.e(TAG, "Player number error");
            AlertHelper.alertPlayerNumberError(mContext);
            return;
        }



        Intent intent = new Intent(mContext, GameActivity.class);
        intent.putExtra(GameActivity.BUNDLE_PARAM, number);
        startActivity(intent);

    }




}
