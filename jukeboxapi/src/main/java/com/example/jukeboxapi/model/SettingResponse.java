package com.example.jukeboxapi.model;

import java.util.List;

//setting response entity
public class SettingResponse {

    private List<Setting> settings;

    public List<Setting> getSettings() {
        return settings;
    }

    public void setSettings(List<Setting> settings) {
        this.settings = settings;
    }
}
