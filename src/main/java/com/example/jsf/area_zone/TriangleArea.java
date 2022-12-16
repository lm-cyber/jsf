package com.example.jsf.area_zone;

import com.example.jsf.beans.Point;
import lombok.Data;

@Data
public class TriangleArea extends Area{
    //y < -x/2+r/2

    public TriangleArea(Area area) {
        addArea(area);
    }

    @Override
    protected boolean cheakArea(Point point) {
        return point.getX()>0 && point.getY()>0 && (point.getY()<(-point.getX()/2+ point.getR()/2));
    }
}
