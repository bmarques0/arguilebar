package com.example.brunofelipe.arguilebar.Activity;

public class ModelItem {

    String title;
    String desc;
    int icon;

    //construtor
    public ModelItem (String title, String desc, int icon){
        this.title=title;
        this.desc=desc;
        this.icon=icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getIcon() {
        return icon;
    }
}
