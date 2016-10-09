package vramesh.gw2dailynotifications.dailies;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import vramesh.gw2dailynotifications.R;

public class DailiesActivity extends AppCompatActivity {

    final String TAG = "GW2DailyNotifications";

    private DailiesPresenter mDailiesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailies);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.action_bar_toolbar);
        setSupportActionBar(myToolbar);

        DailiesFragment dailiesFragment =
                (DailiesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (dailiesFragment == null) {
            dailiesFragment = DailiesFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, dailiesFragment)
                    .commit();
        }

        mDailiesPresenter = new DailiesPresenter(
                dailiesFragment,
                new DailiesLoader(getApplicationContext()),
                getSupportLoaderManager()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(TAG, "Settings Clicked!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
