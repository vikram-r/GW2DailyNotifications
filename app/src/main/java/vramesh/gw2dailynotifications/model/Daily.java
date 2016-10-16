package vramesh.gw2dailynotifications.model;

import java.util.List;

/**
 * Created by vikram on 10/15/16.
 */

public class Daily {
    private Integer id;
    private Level min;
    private Level max;
    private List<String> requiredAccess;

    public Daily() {

    }

    public Integer getId() {
        return id;
    }

    public Level getMin() {
        return min;
    }

    public Level getMax() {
        return max;
    }

    public List<String> getRequiredAccess() {
        return requiredAccess;
    }
}
