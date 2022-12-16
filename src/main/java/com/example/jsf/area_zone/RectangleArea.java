package com.example.jsf.area_zone;

import com.example.jsf.beans.Point;
import lombok.Data;

@Data
public class RectangleArea extends Area {

    public RectangleArea(Area area) {
        addArea(area);
    }

    @Override
    protected boolean cheakArea(Point point) {
        return (point.getX() > 0 && point.getX() < point.getR() && point.getY() < 0 && point.getY() > -point.getR());
    }
}
