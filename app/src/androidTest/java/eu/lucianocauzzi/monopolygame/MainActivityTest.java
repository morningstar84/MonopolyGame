package eu.lucianocauzzi.monopolygame;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.lucianocauzzi.monopolygame.ui.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by lucio on 06/04/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =  new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityAppearance() throws Exception{
        final MainActivity mainActivity = mActivityRule.getActivity();


        onView(withId(R.id.player_edittext)).perform(replaceText("1"));
        onView(withId(R.id.button_confirm)).perform(click());

        Spoon.screenshot(mainActivity, "Mainactivity");
    }


}
