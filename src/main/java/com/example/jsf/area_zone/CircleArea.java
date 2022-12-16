package com.example.jsf.area_zone;

import com.example.jsf.beans.Point;
import lombok.Data;

@Data
public class CircleArea extends Area {

    public CircleArea(Area area){
        addArea(area);
    }
    @Override
    protected boolean cheakArea(Point point) {
        return (point.getX() < 0 && point.getY() < 0) && ((Math.pow(point.getX(), 2) + Math.pow(point.getX(), 2)) < (Math.pow(point.getR() / 2, 2)));
    }
}
