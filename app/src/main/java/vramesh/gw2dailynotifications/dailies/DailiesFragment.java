package vramesh.gw2dailynotifications.dailies;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vramesh.gw2dailynotifications.R;

/**
 * Created by vikram on 10/8/16.
 */

public class DailiesFragment extends ListFragment implements DailiesContract.View {

    private DailiesContract.Presenter mPresenter;
    private DailiesAdapter mAdapter;

    public static DailiesFragment newInstance() {
        Bundle args = new Bundle();

        //...

        DailiesFragment fragment = new DailiesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DailiesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new DailiesAdapter(new ArrayList<DailyListing>());
        setListAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(DailiesPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void updateAllDailies(List<DailyListing> dailies) {
        mAdapter.updateAll(dailies);
    }

    private static class DailiesAdapter extends BaseAdapter {

        private List<DailyListing> mDailies;

        //todo add listener here if required
        public DailiesAdapter(List<DailyListing> dailies) {
            setDailies(dailies);
        }

        public void updateAll(List<DailyListing> dailies) {
            setDailies(dailies);
            notifyDataSetChanged();
        }

        private void setDailies(List<DailyListing> dailies) {
            mDailies = dailies != null ? dailies : new ArrayList<DailyListing>();
        }

        @Override
        public int getCount() {
            return mDailies.size();
        }

        @Override
        public DailyListing getItem(int i) {
            return mDailies.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            //todo render an awesome view!
            View dailyListingView = view;
            if (dailyListingView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                dailyListingView = inflater.inflate(R.layout.daily_listing, viewGroup, false);
            }
            final DailyListing dailyListing = getItem(i);

            TextView dailyNameView = (TextView) dailyListingView.findViewById(R.id.daily_listing_name);
            dailyNameView.setText(dailyListing.getGameMode() + ": " + dailyListing.getName());
            dailyListingView.setBackgroundColor(Color.RED);

            return dailyListingView;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }
    }

}
