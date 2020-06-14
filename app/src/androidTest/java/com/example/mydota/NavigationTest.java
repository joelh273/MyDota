package com.example.mydota;

import android.view.Menu;
import android.view.MenuItem;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mydota.UI.Views.Activities.ProDotaActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class NavigationTest {

    @Rule
    public ActivityTestRule<ProDotaActivity> activityTestRule =
            new ActivityTestRule<>(ProDotaActivity.class);

    private static final int[] MENU_CONTENT_ITEMS_ID = {
            R.id.nav_players,R.id.nav_teams
    };

    private Map<Integer, String> menuContent;
    private BottomNavigationView bottomNavigationView;
    @Before
    public void setUp() {
        final ProDotaActivity activity = activityTestRule.getActivity();
        bottomNavigationView = (BottomNavigationView) activity.findViewById(R.id.bottom_navigation);
        menuContent = new HashMap<>(MENU_CONTENT_ITEMS_ID.length);
        menuContent.put(R.id.nav_teams, "heroes");
        menuContent.put(R.id.heroes, "teams");
    }
    @Test
    public void test_is_bottomnavigationview_visible() {
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()));
    }
    @Test
    public void test_bottomnavigationview() {
        final Menu menu = bottomNavigationView.getMenu();
        assertNotNull(menu);

        assertEquals(MENU_CONTENT_ITEMS_ID.length, menu.size());

        for (int i = 0; i < MENU_CONTENT_ITEMS_ID.length; i++) {
            final MenuItem currentItem = menu.getItem(i);
            assertEquals(i, MENU_CONTENT_ITEMS_ID[i], currentItem.getItemId());
        }
    }

}
