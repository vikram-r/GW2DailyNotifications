package vramesh.gw2dailynotifications.dailies;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vikram on 10/9/16.
 */

public class DailiesLoader extends AsyncTaskLoader<List<Daily>> {

    public DailiesLoader(Context context) {
        super(context);
    }

    @Override
    public void onStartLoading() {
//        if (takeContentChanged()) forceLoad();
    }

    @Override
    public List<Daily> loadInBackground() {
        //todo make an api call!
        List<Daily> testData = new ArrayList<>();
        testData.add(new Daily("First Daily"));
        testData.add(new Daily("Second Daily"));
        return testData;
    }
}
