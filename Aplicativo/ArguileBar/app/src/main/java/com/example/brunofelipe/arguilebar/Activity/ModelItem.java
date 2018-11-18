package com.example.brunofelipe.arguilebar.Activity;

public class ModelItem {

    String title;
    String desc;
    String imageUrl;

    //construtor
    public ModelItem (String title, String desc, String imageUrl){
        this.title=title;
        this.desc=desc;
        this.imageUrl=imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getIcon() {
        return imageUrl;
    }
}
