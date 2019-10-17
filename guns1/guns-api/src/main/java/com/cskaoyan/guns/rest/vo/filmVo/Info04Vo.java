package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;

public class Info04Vo implements Serializable {
    private static final long serialVersionUID = 1427394265829625834L;
    private String biography;
    private ActorsVo actors;

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ActorsVo getActors() {
        return actors;
    }

    public void setActors(ActorsVo actors) {
        this.actors = actors;
    }
}
