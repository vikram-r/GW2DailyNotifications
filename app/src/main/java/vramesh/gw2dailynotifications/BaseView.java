package vramesh.gw2dailynotifications;

/**
 * Created by vikram on 10/9/16.
 */

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
