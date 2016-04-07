package eu.lucianocauzzi.monopolygame.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import eu.lucianocauzzi.monopolygame.R;


/**
 * Created by lcauzzi on 20/04/15.
 */
public class AlertHelper {
    /*
        Provides a list of statics method for alerting user

    */

    private static final String TAG = AlertHelper.class.getSimpleName();

    public static AlertDialog genericOneButtonAlert(Context mContext, String title, String msg, String button){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setMessage(msg)
                .setCancelable(true)
                .setPositiveButton(button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        //finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        return  alertDialog;
    }


    public static AlertDialog genericOneButtonAlertWithListener(Context mContext, String title, String msg, String button, DialogInterface.OnClickListener listener){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setMessage(msg)
                .setCancelable(true)
                .setPositiveButton(button, listener);


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        return  alertDialog;
    }

    public static AlertDialog genericTwoButtonAlertWithListener(Context mContext, String title, String msg, String yesButton, String noButton,  DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(title);

        alertDialogBuilder.setMessage(msg)
                .setCancelable(true)
                .setNegativeButton(noButton, noListener)
                .setPositiveButton(yesButton, yesListener);


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        return  alertDialog;
    }

    public static void alertPlayerNumberError(Context mContext){

        if (mContext == null){
            return;
        }

        try{
            String title = mContext.getResources().getString(R.string.player_error_title);
            String msg = mContext.getResources().getString(R.string.player_error_msg);
            String button = mContext.getResources().getString(R.string.player_error_ok);

            genericOneButtonAlert(mContext, title, msg, button).show();
        }catch (Exception e){
            Log.e(TAG, "DIALOG NOT DISPLAYED");
        }
    }

    public static void alertGameFinished(Context mContext, DialogInterface.OnClickListener okListener){

        if (mContext == null){
            return;
        }

        try{
            String title = mContext.getResources().getString(R.string.player_error_title);
            String msg = mContext.getResources().getString(R.string.player_error_msg);
            String button = mContext.getResources().getString(R.string.player_error_ok);

            genericOneButtonAlertWithListener(mContext,  title, msg, button, okListener).show();
        }catch (Exception e){
            Log.e(TAG, "DIALOG NOT DISPLAYED");
        }
    }




}
