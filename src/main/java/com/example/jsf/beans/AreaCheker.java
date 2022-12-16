package com.example.jsf.beans;

import com.example.jsf.area_zone.Area;
import com.example.jsf.area_zone.CircleArea;
import com.example.jsf.area_zone.RectangleArea;
import com.example.jsf.area_zone.TriangleArea;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import java.io.Serializable;

@Singleton
@Named
public class AreaCheker implements Serializable {

    private final static Area area = new RectangleArea(new TriangleArea(new CircleArea(null)));

//    public void addArea(Area area){
//        if(this.area==null){
//            setArea(area);
//            return;
//        }
//        area.addArea(area);
//
//    }
    public boolean cheakPoint(Point point){
        return area.cheakAreaDecorate(point);
    }


}
