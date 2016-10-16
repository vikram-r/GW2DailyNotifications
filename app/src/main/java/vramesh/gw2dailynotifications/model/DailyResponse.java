package vramesh.gw2dailynotifications.model;

import java.util.ArrayList;
import java.util.List;

public class DailyResponse {
    private List<Daily> pve;
    private List<Daily> pvp;
    private List<Daily> wvw;
    private List<Daily> special;

    public DailyResponse() {

    }

    public List<Daily> getPve() {
        return pve != null ? pve : new ArrayList<Daily>();
    }

    public List<Daily> getPvp() {
        return pvp != null ? pvp : new ArrayList<Daily>();
    }

    public List<Daily> getWvw() {
        return wvw != null ? wvw : new ArrayList<Daily>();
    }

    public List<Daily> getSpecial() {
        return special != null ? special : new ArrayList<Daily>();
    }
}
