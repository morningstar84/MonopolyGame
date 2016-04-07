package eu.lucianocauzzi.monopolygame.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by lcauzzi on 01/09/15.
 */
public abstract class BaseWidget extends LinearLayout{

    protected Context mContext;


    public BaseWidget(Context context) {
        super(context);

        mContext = context;
        inflate();
    }

    public BaseWidget(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        inflate();
    }

    public BaseWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        inflate();
    }

    protected void inflate(){

        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(getLayoutResource(), this, true);

        init();
    }


    protected abstract int getLayoutResource();
    protected abstract void init();
}
