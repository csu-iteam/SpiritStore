package com.SpiritStore.Domain;

/**
 * Created by Dell on 2017/9/20.
 */
public class Classify {
    private int id;
    private String name=null;
    private int parentId=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
