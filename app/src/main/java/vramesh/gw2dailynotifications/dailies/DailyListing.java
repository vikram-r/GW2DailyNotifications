package vramesh.gw2dailynotifications.dailies;


/**
 * Representation of a Daily
 */
public class DailyListing {

    private final String name;
    private final String description;

    public DailyListing(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
