package com.example.jsf.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@RequestScoped
@Named
@Data
@NoArgsConstructor
public class Point implements Serializable {
    @Inject
    transient private AttemptsManager am;
    @Inject
    transient private AreaCheker areaCheker;
    private double x;
    private double y;
    private double r;

    private boolean[] xArray;


    @PostConstruct
    public void init(){
        this.xArray = new boolean[9];
    }
    public void setValue0(boolean xBool){
        xArray[0] =xBool;
    }
    public boolean getValue0(){
        return xArray[0];
    }


    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }



    public void submit() {
        final long start = System.nanoTime();
        final boolean res = areaCheker.cheakPoint(this);

        PointAttempt attempt = new PointAttempt();
        attempt.setPoint(this);
        attempt.setSuccess(res);
        attempt.setAttemptTime(System.currentTimeMillis());
        attempt.setProcessTime((System.nanoTime() - start) / 1000d);

        am.addAttempt(attempt);
    }
}
