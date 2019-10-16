package com.cskaoyan.guns.rest.vo.filmVo;

import java.io.Serializable;
import java.util.List;

public class ActorsVo implements Serializable {
    private static final long serialVersionUID = -5401253652598664268L;
    private Director director;
    private List<Actor> actors;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
