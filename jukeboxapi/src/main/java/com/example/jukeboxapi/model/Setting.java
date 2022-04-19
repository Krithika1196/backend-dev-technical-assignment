package com.example.jukeboxapi.model;

import java.util.List;

//setting entity
public class Setting {

    private String id;
    private List<String> requires;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }
}
