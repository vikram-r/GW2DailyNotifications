package vramesh.gw2dailynotifications.model;

import java.util.List;

/**
 * Representation of a single daily with all information loaded. More info at:
 * https://wiki.guildwars2.com/wiki/API:2/achievements
 */
public class DailyInfo {
    //todo model more of the data
    private Integer id;
    private String name;
    private String description;
    private String requirement;
    private String type;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getType() {
        return type;
    }
}
