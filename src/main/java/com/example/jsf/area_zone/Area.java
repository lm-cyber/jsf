package com.example.jsf.area_zone;

import com.example.jsf.beans.Point;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class Area implements Serializable {
    // можно более правильную модель создать, но тут не так много отменя требуется
    private Area nextDecorator;

    protected boolean cheakArea(Point point) {
        return false;
    }
    public boolean cheakAreaDecorate(Point point){
        Area decorate = nextDecorator;
        if(cheakArea(point)) return true;
        while (decorate != null){
            if(decorate.cheakArea(point)) return true;
            decorate = decorate.nextDecorator;
        }
        return false;
    }
    public void addArea(Area area) {
        if(nextDecorator == null){
             setNextDecorator(area);
            return;
        }
        Area decorate = nextDecorator;
        while (decorate.nextDecorator != null){
            decorate= decorate.nextDecorator;
        }
        decorate.nextDecorator = area;

    }

}
