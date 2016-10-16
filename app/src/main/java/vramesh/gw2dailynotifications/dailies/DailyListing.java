package vramesh.gw2dailynotifications.dailies;


/**
 * Representation of a Daily
 */
public class DailyListing {

    private final String name;
    private final String gameMode;

    public DailyListing(String name, String gameMode) {
        this.name = name;
        this.gameMode = gameMode;
    }

    public String getName() {
        return name;
    }

    public String getGameMode() {
        return gameMode;
    }
}
