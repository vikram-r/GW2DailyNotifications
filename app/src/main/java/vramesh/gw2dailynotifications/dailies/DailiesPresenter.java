package vramesh.gw2dailynotifications.dailies;


import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by vikram on 10/9/16.
 */

public class DailiesPresenter implements DailiesContract.Presenter, LoaderManager.LoaderCallbacks<List<DailyListing>>  {

    private final DailiesContract.View mDailiesView;
    private final DailiesLoader mLoader;
    private final LoaderManager mLoaderManager;

    public DailiesPresenter(DailiesContract.View dailiesView, DailiesLoader loader, LoaderManager loaderManager) {
        mDailiesView = dailiesView;
        mLoader = loader;
        mLoaderManager = loaderManager;

        mDailiesView.setPresenter(this);
    }

    @Override
    public Loader<List<DailyListing>> onCreateLoader(int id, Bundle args) {
        return mLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<DailyListing>> loader, List<DailyListing> data) {
        //todo apply any filtering based on settings here
        mDailiesView.updateAllDailies(data);
    }

    @Override
    public void onLoaderReset(Loader<List<DailyListing>> loader) {

    }

    @Override
    public void start() {
        mLoaderManager.initLoader(1, null, this).forceLoad();
        //todo stuff
    }

}
