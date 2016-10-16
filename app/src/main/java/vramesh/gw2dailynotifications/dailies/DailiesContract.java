package vramesh.gw2dailynotifications.dailies;

import java.util.List;

import vramesh.gw2dailynotifications.BasePresenter;
import vramesh.gw2dailynotifications.BaseView;

/**
 * Created by vikram on 10/9/16.
 */

public interface DailiesContract {

    interface View extends BaseView<DailiesPresenter> {

        void updateAllDailies(List<DailyListing> dailies);

    }

    interface Presenter extends BasePresenter {

    }
}
