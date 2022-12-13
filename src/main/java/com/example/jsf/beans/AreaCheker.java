package com.example.jsf;

import com.example.jsf.area_zone.Area;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Singleton;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SessionScoped
@Data
@NoArgsConstructor
@Singleton
public class AreaCheker implements Serializable {

    private Area area;

    public void addArea(Area area){
        if(this.area==null){
            setArea(area);
            return;
        }
        area.addArea(area);

    }
    public boolean cheakPoint(Point point){
        return area.cheakAreaDecorate(point);
    }


}
